package com.example.tlaitinen.ohutlevymuokkaaja;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Button button;
    TextView tvOutput;
    EditText etPaksuus;
    EditText etSäde;
    EditText etKulma;
    KerroinLaskija laskija=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        tvOutput = findViewById(R.id.tvOutput);
        etKulma = findViewById(R.id.etKulma);
        etSäde = findViewById(R.id.etSäde);
        etPaksuus=findViewById(R.id.etPaksuus);

        button.setOnClickListener(view -> {
            if(laskija==null ){ laskija = new KerroinLaskija(Double.parseDouble(etPaksuus.getText().toString())); }
            // zero check
            if(Double.parseDouble(etPaksuus.getText().toString())==0) {
                Snackbar.make(view, "Levyn paksuus tulee olla > 0 mm", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
            else {
                laskeJaNäytäTulos();
            }
        });

        etSäde.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(etPaksuus.getText().length()!=0 )
                {
                    laskeJaNäytäTulos();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) { }
        });


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());
    }

    private void laskeJaNäytäTulos() {
        double k = laskija.getKerroinK(//laskija.getKorjaavaTekijä(
                //Double.parseDouble(etKulma.getText().toString()),
                Double.parseDouble(etSäde.getText().toString())
                );
        String s = String.format(Locale.getDefault(),"k = %.2f\nY = %.2f",k,laskija.getKerroinY(k));
        tvOutput.setText(s);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
