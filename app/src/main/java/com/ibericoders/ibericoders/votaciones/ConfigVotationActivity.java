package com.ibericoders.ibericoders.votaciones;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ibericoders.ibericoders.R;
import com.ibericoders.ibericoders.actas.activities.MainActasActivity;
import com.ibericoders.ibericoders.controlgastos.activities.MainGastosActivity;
import com.ibericoders.ibericoders.dados.MainDicesActivity;

public class ConfigVotationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private EditText editTextTopic;
    private SeekBar seekBarParticipants;
    private TextView textViewParticipants;
    private int totalParticipants;
    private String topic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config_votation);

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
        editTextTopic = (EditText) findViewById(R.id.editTextTopic);
        seekBarParticipants = (SeekBar) findViewById(R.id.seekBarParticipants);
        textViewParticipants = (TextView) findViewById(R.id.textViewNumberParticipants);
        ImageButton imageBotton = (ImageButton) findViewById(R.id.buttonToVotes);
        totalParticipants = 0;
        topic = null;

        //programamos el comportamiento del seekbar
        seekBarParticipants.setMax(20);
        seekBarParticipants.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int currentParticipants, boolean fromUser) {
                //El progreso de la barra muestra el total de participantes de la votacion.
                totalParticipants = currentParticipants;
                textViewParticipants.setText(totalParticipants + "");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                totalParticipants = seekBarParticipants.getProgress();
                textViewParticipants.setText(totalParticipants + "");

            }
        });

        imageBotton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //el topic se enviará a la activity de resultados.
                topic = editTextTopic.getText().toString();
                //si el topic es distinto de null, se inicia la siguiente actividad
                //y se pasan los datos a traves del intent.
                if (topic != "") {
                    sendConfig();
                } else {
                    Toast.makeText(ConfigVotationActivity.this, "Introduce el tema de la votación primero", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void sendConfig() {

        if (totalParticipants > 0) {
            //si el numero total de participantes, es distinto de 0
            //iniciamos la siguiente activity.
            //cuando se pulsa el botón, se abre una nueva actividad y se manda a traves
            //del Intent el resultado de las votaciones.
            Intent intent = new Intent(ConfigVotationActivity.this, VoteActivity.class);
            intent.putExtra("totalParticipants", totalParticipants);
            intent.putExtra("topic", topic);
            startActivity(intent);
        } else {
            Toast.makeText(ConfigVotationActivity.this, "Introduce el número de participantes primero", Toast.LENGTH_SHORT).show();
        }
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
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.dados) {
            Intent intent_dados=new Intent(this, MainDicesActivity.class);
            this.startActivity(intent_dados);
        } else if (id == R.id.votaciones) {
            Intent intent_votaciones=new Intent(this, ConfigVotationActivity.class);
            this.startActivity(intent_votaciones);
        } else if (id == R.id.actas) {
            Intent intent_actas=new Intent(this, MainActasActivity.class);
            this.startActivity(intent_actas);
        } else if (id == R.id.gastos) {
            Intent intent_gastos=new Intent(this, MainGastosActivity.class);
            this.startActivity(intent_gastos);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
