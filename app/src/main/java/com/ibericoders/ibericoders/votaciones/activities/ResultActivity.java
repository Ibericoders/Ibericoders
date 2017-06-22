package com.ibericoders.ibericoders.votaciones.activities;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.ibericoders.ibericoders.R;
import com.ibericoders.ibericoders.votaciones.model.MyValueFormatter;

import java.util.ArrayList;
import java.util.List;

public class ResultActivity extends AppCompatActivity {

    private float votesInA;
    private float votesInB;
    private float votesInC;
    private float votesInD;
    private String topic;
    private PieChart pieChart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Button buttonShare = (Button) findViewById(R.id.buttonToShare);
        pieChart = (PieChart) findViewById(R.id.pieChart);

        //Llamamos a través de la clase Bundle a los datos de la votación.
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            //int totalParticipants = bundle.getInt("totalParticipants");
            votesInA = bundle.getInt("votesA");
            votesInB = bundle.getInt("votesB");
            votesInC = bundle.getInt("votesC");
            votesInD = bundle.getInt("votesD");
            topic = bundle.getString("topic");
        }

        //creamos un array con los resultados para poder utilizarlo en la tabla.
        float[] resultVotes = new float[4];
        resultVotes[0] = votesInA;
        resultVotes[1] = votesInB;
        resultVotes[2] = votesInC;
        resultVotes[3] = votesInD;

        List<PieEntry> entries = new ArrayList<>();

        // transformamos nuestros datos en PieEntry.
        entries.add(new PieEntry(votesInA, "Opción A / SÍ"));
        entries.add(new PieEntry(votesInB, "Opción B/ NO"));
        entries.add(new PieEntry(votesInC, "Opción C"));
        entries.add(new PieEntry(votesInD, "Opción D"));

        //seteamos los datos en la tabla y sus características.
        PieDataSet set = new PieDataSet(entries, "SetPieChart");
        PieData data = new PieData(set);
        pieChart.setData(data);
        pieChart.setCenterText(topic);
        set.setSliceSpace(7f);
        set.setSelectionShift(7f);
        pieChart.invalidate(); // refresh


        // seteamos los colores del grafico y el color del texto, entre otros parámetros.
        set.setColors(new int[]{R.color.colorPrimaryLight, R.color.colorButtonNo, R.color.colorButtonC, R.color.colorButtonD}, this);
        data.setValueFormatter(new MyValueFormatter());
        data.setValueTextSize(15f);
        data.setValueTextColor(Color.BLACK);
        pieChart.setEntryLabelColor(Color.BLACK);
        buttonShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pieChart.saveToGallery(topic, 100);
                sendEmail();
            }
        });


    }

   /* @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 10: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {


                } else {

                    Toast.makeText(this,"El acceso al almacenamiento externo es necesario para usar esta función.",Toast.LENGTH_LONG).show();
                }
            }
        }
    }*/

    //método para envíar el email al pulsar el botón de resultado
    //en principio, al ser una App interna, la dirección será predefinida
    //se plantea hacer un menú de opciones para poder configurar las preferencias.

    protected void sendEmail() {
        Log.i("Send email", "");

        String[] TO = {"ibericoders@gmail.com"};
        String[] CC = {""};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.setType("application/image");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Resultado de la votación '" + topic + "'");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "El resultado de la votación sobre el asunto '" + topic + "' se encuentra en su galería de imágenes, adjúntelo si lo desea.");
        //emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("file:///mnt/sdcard/Result" + topic + ".jpeg"));

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
            Log.i("Finished sending email", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(ResultActivity.this,
                    "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }
}
