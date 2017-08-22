package com.ibericoders.ibericoders;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.crashlytics.android.Crashlytics;
import com.ibericoders.ibericoders.acts.activities.MainActsActivity;
import com.ibericoders.ibericoders.controlgastos.activities.MainExpensesActivity;
import com.ibericoders.ibericoders.dados.MainDicesActivity;
import com.ibericoders.ibericoders.votaciones.activities.ConfigVotingActivity;
import io.fabric.sdk.android.Fabric;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_main);

    }

    public void dices(View v){
        Intent intent_dices=new Intent(this, MainDicesActivity.class);
        this.startActivity(intent_dices);
    }

    public void voting(View v){
        Intent intent_voting=new Intent(this, ConfigVotingActivity.class);
        this.startActivity(intent_voting);
    }

    public void acts(View v){
        Intent intent_act=new Intent(this, MainActsActivity.class);
        this.startActivity(intent_act);
    }

    public void expenses(View v){
        Intent intent_expenses=new Intent(this, MainExpensesActivity.class);
        this.startActivity(intent_expenses);
    }

}
