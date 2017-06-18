package com.ibericoders.ibericoders;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.ibericoders.ibericoders.actas.activities.MainActasActivity;
import com.ibericoders.ibericoders.controlgastos.activities.MainGastosActivity;
import com.ibericoders.ibericoders.dados.MainDicesActivity;
import com.ibericoders.ibericoders.votaciones.activities.ConfigVotationActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void dados (View v){
        Intent intent_dados=new Intent(this, MainDicesActivity.class);
        this.startActivity(intent_dados);
    }

    public void votaciones(View v){
        Intent intent_votaciones=new Intent(this, ConfigVotationActivity.class);
        this.startActivity(intent_votaciones);
    }

    public void actas(View v){
        Intent intent_actas=new Intent(this, MainActasActivity.class);
        this.startActivity(intent_actas);
    }

    public void gastos(View v){
        Intent intent_gastos=new Intent(this, MainGastosActivity.class);
        this.startActivity(intent_gastos);
    }

}
