package com.example.tlaitinen.ohutlevymuokkaaja;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;

public class Main2Activity extends AppCompatActivity implements TextWatcher {

    double paksuus=1,säde=0;

    EditText etKulma;
    EditText etPituusA, etPituusB;
    TextView tvOutput;
    //KerroinLaskija laskija;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent i = getIntent();
        paksuus = i.getFloatExtra("paksuus",1);
        säde = i.getFloatExtra("säde",0);

        //laskija = new KerroinLaskija(paksuus);

        etKulma = findViewById(R.id.etKulma);
        etPituusA = findViewById(R.id.etPituusA);
        etPituusB = findViewById(R.id.etPituusB);
        tvOutput = findViewById(R.id.tvOutput);

        etKulma.addTextChangedListener(this);
        etPituusA.addTextChangedListener(this);
        etPituusA.addTextChangedListener(this);
    }

    private void laskeJaNäytäTulos() {
        double v = KerroinLaskija.getKorjaavaTekijä(Double.parseDouble(etKulma.getText().toString()),säde,paksuus);
        double a = (etPituusA.length()==0)?0:Double.parseDouble(etPituusA.getText().toString());
        double b = (etPituusB.length()==0)?0:Double.parseDouble(etPituusB.getText().toString());
        String s;
        s= String.format(Locale.getDefault(),"Korjaava tekijä\nv = %.2f mm\nOikaistu pituus\na+b+v= %d mm",v, (int)Math.round(a+b+v));
        tvOutput.setText(s);
    }


    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        laskeJaNäytäTulos();
    }

    @Override
    public void afterTextChanged(Editable editable) { }
}