package com.ibericoders.ibericoders.acts.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.ibericoders.ibericoders.R;
import com.ibericoders.ibericoders.acts.model.Act;
import com.ibericoders.ibericoders.acts.model.DataManager;

import java.util.ArrayList;

public class ListActsActivity extends AppCompatActivity {

    private Context ctx;
    private ArrayList<Act> acts;
    private LayoutInflater lf;

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_acts);

        DataManager data=new DataManager(this);
        ListView lista=(ListView)this.findViewById(R.id.lvActas);
        //creamos adaptador del arraylist
        ArrayAdapter<Act> adapter=new ArrayAdapter<Act>(this,
                android.R.layout.simple_list_item_1,
                data.getPreviousAct());
        lista.setAdapter(adapter);


//----------------------------Metodo clicklistener--------------------------------------------------
        AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Act ac = acts.get(position);
                //abre una nueva ventana recuperando el acta en cuestión
                Intent intent =new Intent(ListActsActivity.this,MainActsActivity.class);
                intent.putExtra("Act",ac);
                ListActsActivity.this.startActivity(intent);

            }
        };
        lista.setOnItemClickListener(listener);
    }

    //--------------------------------Boton Salir-------------------------------------------------------
    public void exit(View v) {
        this.finish();
    }
//--------------------------------------------------------------------------------------------------
}
