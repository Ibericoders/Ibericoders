package com.ibericoders.ibericoders;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.crashlytics.android.Crashlytics;
import com.ibericoders.ibericoders.acts.activities.MainActsActivity;
import com.ibericoders.ibericoders.controlgastos.activities.MainExpensesActivity;
import com.ibericoders.ibericoders.dados.MainDicesActivity;
import com.ibericoders.ibericoders.votaciones.activities.ConfigVotingActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.fabric.sdk.android.Fabric;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    @BindView(R.id.imageButton3)
    ImageButton vote;

    @BindView(R.id.imageButton4)
    ImageButton dice;

    @BindView(R.id.imageButton5)
    ImageButton expenses;

    @BindView(R.id.imageButton6)
    ImageButton acts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        /*
         * Listeners de los botones
         */
        vote.setOnClickListener(this);
        dice.setOnClickListener(this);
        expenses.setOnClickListener(this);
        acts.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.imageButton3:

                Intent intent_voting=new Intent(this, ConfigVotingActivity.class);
                this.startActivity(intent_voting);
                break;

            case R.id.imageButton4:

                Intent intent_dices=new Intent(this, MainDicesActivity.class);
                this.startActivity(intent_dices);
                break;

            case R.id.imageButton5:

                Intent intent_expenses=new Intent(this, MainExpensesActivity.class);
                this.startActivity(intent_expenses);
                break;

            case R.id.imageButton6:

                Intent intent_act=new Intent(this, MainActsActivity.class);
                this.startActivity(intent_act);
                break;
        }
    }
}
