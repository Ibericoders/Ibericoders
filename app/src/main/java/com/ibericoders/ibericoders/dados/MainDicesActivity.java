package com.ibericoders.ibericoders.dados;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.answers.ContentViewEvent;
import com.ibericoders.ibericoders.R;
import com.ibericoders.ibericoders.acts.activities.MainActsActivity;
import com.ibericoders.ibericoders.controlgastos.activities.MainExpensesActivity;
import com.ibericoders.ibericoders.votaciones.activities.ConfigVotingActivity;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainDicesActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ImageView imageViewDice;    //ImageView en el que se va a posicionar el dado.
    private TextView textViewRandom;    //TextView sobre el que se vuelca el resultado aleatorio
    private TextView textViewMaximum;   //TextView en el que el usuario puede ver el numero máximo que marca.
    private Random random;              //Genera numeros al azar.
    private Handler handler;            //Manejador para el TimerTask
    private Timer timer;                //Usado para darle feedback al usuario.
    private boolean rolling;            //Está el dado rodando?
    private int maxNumberEstablished; //Número máximo establecido para el aleatorio.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dices);

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

        //Instanciamos los elementos de la UI y seteamos algunos de sus valores.

        Button buttonArbitrary = (Button) findViewById(R.id.buttonArbitrary);
        imageViewDice = (ImageView) findViewById(R.id.imageViewDice);
        textViewRandom = (TextView) findViewById(R.id.textViewArbitraryNumber);
        textViewMaximum = (TextView) findViewById(R.id.textViewMaximum); //TextView que marca el maximo establecido
        SeekBar seekBarMaximum = (SeekBar) findViewById(R.id.seekBarMaximum);
        int maxSeekBar = 20;
        seekBarMaximum.setMax(maxSeekBar); //El máximo numero aleatorio es 20.
        textViewMaximum.setText(maxNumberEstablished + "");
        random = new Random();
        rolling = false;
        timer = new Timer();


        imageViewDice.setOnClickListener(new HandleClick());
        //ENLACE DEL HANDLER Y EL CALLBACK.
        handler = new Handler(callback);

        //A CONTINUACION, PROGRAMAMOS EL COMPORTAMIENTO DEL SEEKBAR, QUE SE VA A OCUPAR DE
        //MARCAR EL NUMERO MÁXIMO PARA EL ALEATORIO.

        seekBarMaximum.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int currentMaximum, boolean fromUser) {
                //SEGÚN SE MODIFICA LA BARRA DE PROGRESO, EL NUMERO MARCADO SE VUELCA EN EL TEXTVIEW.
                maxNumberEstablished = currentMaximum;
                textViewMaximum.setText(maxNumberEstablished + "");

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //AQUI NO HACEMOS NADA.
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                maxNumberEstablished = seekBar.getProgress();
                textViewMaximum.setText(maxNumberEstablished + "");
            }
        });

        //CREAMOS UN LISTENER PARA EL BOTON DE ALEATORIO
        buttonArbitrary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //GENERAMOS UN NUMERO DE 1 A 10 ALEATORIO.
                int randomNumber = (int) (Math.random() * maxNumberEstablished + 1);
                //VOLCAMOS ESE NUMERO EN EL TEXTVIEW.
                textViewRandom.setText(randomNumber + "");
            }
        });
    }

    //EL USUARIO HA PULSADO EL BOTON Y EL DADO RUEDA
    private class HandleClick implements View.OnClickListener {
        public void onClick(View v) {
            //SI NO ESTÁ RODANDO YA, CLARO.
            if (!rolling) {
                rolling = true;
                //ENSEÑAMOS EL DADO RODANDO.
                imageViewDice.setImageResource(R.drawable.dice3droll);
                //PAUSA PARA QUE LA IMAGEN SE ACTUALICE.
                //(VER LA CLASE ROLL).
                timer.schedule(new Roll(), 400);
            }
        }
    }

    //CUANDO ACABA ENVIA UN MENSAJE DE CALLBACK.
    private class Roll extends TimerTask {
        public void run() {
            handler.sendEmptyMessage(0);
        }
    }

    //RECIBE UN MENSAJE DEL TIMER DE QUE SE PUEDE INICIAR.
    Handler.Callback callback = new Handler.Callback() {
        public boolean handleMessage(Message msg) {
            //OBTENEMOS EL RESULTADO DEL DADO
            //NEXTINT VA DE 0 A 5, POR TANTO, +1.
            switch (random.nextInt(6) + 1) {
                case 1:
                    //Mostramos la imagen correspondiente en cada uno de los 6
                    // casos posibles

                    imageViewDice.setImageResource(R.drawable.one);
                    break;
                case 2:
                    imageViewDice.setImageResource(R.drawable.two);
                    break;
                case 3:
                    imageViewDice.setImageResource(R.drawable.three);
                    break;
                case 4:
                    imageViewDice.setImageResource(R.drawable.four);
                    break;
                case 5:
                    imageViewDice.setImageResource(R.drawable.five);
                    break;
                case 6:
                    imageViewDice.setImageResource(R.drawable.six);
                    break;
                default:
            }
            rolling = false;  //EL USUARIO PUEDE VOLVER A TIRAR PORQUE HA ACABADO LA TIRADA.
            return true;
        }
    };

    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
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

        // Maneja los clicks en los items del Navigation View.
        int id = item.getItemId();

        if (id == R.id.dices) {

            Answers.getInstance().logContentView(new ContentViewEvent()
                    .putContentName("Dados")
                    .putContentType("Package access from menu"));

            Intent intent_dices=new Intent(this, MainDicesActivity.class);
            this.startActivity(intent_dices);

        } else if (id == R.id.voting) {

            Answers.getInstance().logContentView(new ContentViewEvent()
                    .putContentName("Votaciones")
                    .putContentType("Package access from menu"));

            Intent intent_voting=new Intent(this, ConfigVotingActivity.class);
            this.startActivity(intent_voting);

        } else if (id == R.id.acts) {

            Answers.getInstance().logContentView(new ContentViewEvent()
                    .putContentName("Actas")
                    .putContentType("Package access from menu"));

            Intent intent_acts=new Intent(this, MainActsActivity.class);
            this.startActivity(intent_acts);

        } else if (id == R.id.expenses) {

            Answers.getInstance().logContentView(new ContentViewEvent()
                    .putContentName("Gastos")
                    .putContentType("Package access from menu"));

            Intent intent_expenses=new Intent(this, MainExpensesActivity.class);
            this.startActivity(intent_expenses);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
