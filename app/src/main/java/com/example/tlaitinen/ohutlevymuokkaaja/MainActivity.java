package com.example.tlaitinen.ohutlevymuokkaaja;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.Editable;
import android.text.Html;
import android.text.Spanned;
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
    //KerroinLaskija laskija=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        tvOutput = findViewById(R.id.tvOutput);
        etKulma = findViewById(R.id.etKulma);
        etSäde = findViewById(R.id.etSäde);
        etPaksuus=findViewById(R.id.etPaksuus);
        etPaksuus.requestFocus();

        button.setOnClickListener(view -> {
            //if(laskija==null ){ laskija = new KerroinLaskija(Double.parseDouble(etPaksuus.getText().toString())); }
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
                if(etPaksuus.getText().length()!=0 && Double.parseDouble(etPaksuus.getText().toString())!=0)
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
        fab.setOnClickListener(view -> {
            Intent i = new Intent(MainActivity.this, Main2Activity.class);
            i.putExtra("paksuus",Float.parseFloat(etPaksuus.getText().toString()));
            i.putExtra("säde",Float.parseFloat(etSäde.getText().toString()));
            if(etPaksuus.length()>0 && Float.parseFloat( etPaksuus.getText().toString())>0 &&
                    etSäde.length()>0 && Float.parseFloat( etSäde.getText().toString())>0)
            {
                startActivity(i);
            }
        }

                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show()
        );
    }

    private void laskeJaNäytäTulos() {
        double k = KerroinLaskija.getKerroinK(
                Double.parseDouble(etSäde.getText().toString()),
                Double.parseDouble(etPaksuus.getText().toString())
                );
        /*  <a href="...">
            <b>    <big>    <blockquote>    <br>    <cite>    <dfn>    <div align="...">    <em>    <font size="..." color="..." face="...">
            <h1>    <h2>    <h3>    <h4>    <h5>    <h6>
            <i>    <img src="...">    <p>    <small>    <strike>    <strong>    <sub>    <sup>    <tt> <u>
         */
        String s = String.format(Locale.getDefault(),"<small>Korjauskerroin</small><br>k = %.2f<br><small>Y-factor</small><br>Y = %.2f",k,KerroinLaskija.getKerroinY(k));
        Spanned myStringSpanned = Html.fromHtml(s, null, null);
        tvOutput.setText(myStringSpanned, TextView.BufferType.SPANNABLE);

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
