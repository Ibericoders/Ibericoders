package com.ibericoders.ibericoders.votaciones.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ibericoders.ibericoders.R;

public class VoteActivity extends AppCompatActivity {

    private TextView textViewTopic;
    //creamos variables numericas para contar cuántos votos tiene cada opcion
    private int votesInOptionA;
    private int votesInOptionB;
    private int votesInOptionC;
    private int votesInOptionD;
    private int totalParticipants;
    private int totalVotesInOptions;
    private String topic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote);

        //Procedemos a instanciar los objetos de la UI y a iniciar variables.
        textViewTopic = (TextView) findViewById(R.id.textViewTopic);
        Button buttonA = (Button) findViewById(R.id.buttonA);
        Button buttonB = (Button) findViewById(R.id.buttonB);
        Button buttonC = (Button) findViewById(R.id.buttonC);
        Button buttonD = (Button) findViewById(R.id.buttonD);

        //iniciamos las variables numericas en 0;
        votesInOptionA = 0;
        votesInOptionB = 0;
        votesInOptionC = 0;
        votesInOptionD = 0;
        totalParticipants = 0;
        //necesitamos esta variable para saber el total de votos que se llevan.
        totalVotesInOptions = 0;

        //Recogemos los datos de la configuracion.
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            totalParticipants = bundle.getInt("totalParticipants");
            topic = bundle.getString("topic");
        }
        //seteamos el texto del topic en el título.
        textViewTopic.setText(topic);

        //programamos el comportamiento de los botones
        buttonA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                votesInOptionA++;
                totalVotesInOptions++;
                sendResults();
                Toast.makeText(VoteActivity.this, "Has votado correctamente.", Toast.LENGTH_SHORT).show();
                //cada click, añade un voto a la variable correspondiente, pero sólo si
                // el numero de participantes se ha definido antes y es mayor que 0.
            }
        });

        buttonB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                votesInOptionB++;
                totalVotesInOptions++;
                sendResults();
                Toast.makeText(VoteActivity.this, "Has votado correctamente.", Toast.LENGTH_SHORT).show();
            }
        });

        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                votesInOptionC++;
                totalVotesInOptions++;
                sendResults();
                Toast.makeText(VoteActivity.this, "Has votado correctamente.", Toast.LENGTH_SHORT).show();
            }
        });

        buttonD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                votesInOptionD++;
                totalVotesInOptions++;
                sendResults();
                Toast.makeText(VoteActivity.this, "Has votado correctamente.", Toast.LENGTH_SHORT).show();
            }
        });

    }


    public void sendResults() {
        //le metemos un retardo de 0.5 segundos.
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (totalVotesInOptions == totalParticipants && totalVotesInOptions != 0) {
            //si el numero total de votos es igual al de participantes, y distinto de 0
            //iniciamos la siguiente activity.
            //cuando se pulsa el botón, se abre una nueva actividad y se manda a traves
            //del Intent el resultado de las voting.
            Intent intent = new Intent(VoteActivity.this, ResultActivity.class);
            intent.putExtra("votesA", votesInOptionA);
            intent.putExtra("votesB", votesInOptionB);
            intent.putExtra("votesC", votesInOptionC);
            intent.putExtra("votesD", votesInOptionD);
            intent.putExtra("totalParticipants", totalParticipants);
            intent.putExtra("topic", topic);
            startActivity(intent);

        }
    }
}
