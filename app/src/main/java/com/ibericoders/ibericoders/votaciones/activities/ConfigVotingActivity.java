package com.ibericoders.ibericoders.votaciones.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ibericoders.ibericoders.R;
import com.ibericoders.ibericoders.acts.activities.MainActsActivity;
import com.ibericoders.ibericoders.controlgastos.activities.MainExpensesActivity;
import com.ibericoders.ibericoders.controlgastos.model.Expense;
import com.ibericoders.ibericoders.dados.MainDicesActivity;

public class ConfigVotingActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private EditText topicName, editDescription, editDate;
    private SeekBar seekBarParticipants;
    private TextView textViewParticipants;
    private int totalParticipants;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config_voting);

        //Creación del menu lateral
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        drawer.setScrimColor(Color.TRANSPARENT);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Procedemos a instanciar los objetos de la UI y a iniciar variables.
        topicName = (EditText) findViewById(R.id.edt_config_topic);
        editDescription = (EditText) findViewById(R.id.edt_votingdescription);
        editDate = (EditText) findViewById(R.id.edt_votingdate);
        seekBarParticipants = (SeekBar) findViewById(R.id.seekBarParticipants);
        textViewParticipants = (TextView) findViewById(R.id.numberOfParticipantsTv);
        //ImageButton imageBotton = (ImageButton) findViewById(R.id.buttonToVotes);
        totalParticipants = 0;

        //programamos el comportamiento del seekbar
        seekBarParticipants.setMax(20);
        setParticipants();
        seekBarParticipants.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int currentParticipants, boolean fromUser) {
                //El progreso de la barra muestra el total de participantes de la votacion.
                totalParticipants = currentParticipants;
                setParticipants();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                totalParticipants = seekBarParticipants.getProgress();
                setParticipants();

            }
        });

    }

    public void cancel(View v){
        this.finish();
    }
    public void vote(View v){
        if(topicName.getText().length()>0 && editDescription.getText().length()>0 && editDate.getText().length()>0 && totalParticipants>0){
           sendConfig();
        }else{
            Toast.makeText(this, "Es necesario completar todos los campos", Toast.LENGTH_LONG).show();
        }

    }


    public void sendConfig() {
            //se abre una nueva actividad y se manda a traves
            //del Intent el resultado de las voting.
            Intent intent = new Intent(ConfigVotingActivity.this, VoteActivity.class);
            intent.putExtra("totalParticipants", totalParticipants);
            intent.putExtra("topic", topicName.getText());
            intent.putExtra("description", editDescription.getText());
            intent.putExtra("date", editDate.getText());
            startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.dices) {
            Intent intent_dices = new Intent(this, MainDicesActivity.class);
            this.startActivity(intent_dices);
        } else if (id == R.id.voting) {
            Intent intent_voting = new Intent(this, ConfigVotingActivity.class);
            this.startActivity(intent_voting);
        } else if (id == R.id.acts) {
            Intent intent_acts = new Intent(this, MainActsActivity.class);
            this.startActivity(intent_acts);
        } else if (id == R.id.expenses) {
            Intent intent_expenses = new Intent(this, MainExpensesActivity.class);
            this.startActivity(intent_expenses);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }

    public void setParticipants () {
        textViewParticipants.setText(totalParticipants + "");
    }
}


