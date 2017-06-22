package com.ibericoders.ibericoders.acts.activities;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.ibericoders.ibericoders.R;
import com.ibericoders.ibericoders.acts.model.Act;
import com.ibericoders.ibericoders.acts.model.DataManager;
import com.ibericoders.ibericoders.controlgastos.activities.MainExpensesActivity;
import com.ibericoders.ibericoders.dados.MainDicesActivity;
import com.ibericoders.ibericoders.votaciones.activities.ConfigVotingActivity;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

public class MainActsActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private TextView date, tvHour, nextMeeting, assistants;
    private CheckBox alberto, dei, adrian, sergio, silvia, jorge, david;
    private DataManager dataManager;
    private EditText title, relief, memory, fPoints, conclusions, next,
            compromise, proposals, evaluation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_actas);

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

        //definimos editText
        title =(EditText)this.findViewById(R.id.edtTitulo);
        relief =(EditText)this.findViewById(R.id.edtRelevo);
        memory =(EditText)this.findViewById(R.id.edtMemoria);
        fPoints =(EditText)this.findViewById(R.id.edtPuntosF);
        conclusions =(EditText)this.findViewById(R.id.edtConclusion);
        next =(EditText)this.findViewById(R.id.edtSiguiente);
        compromise =(EditText)this.findViewById(R.id.edtCompromiso);
        proposals =(EditText)this.findViewById(R.id.edtPropuesta);
        evaluation =(EditText)this.findViewById(R.id.edtEvaluacion);
        //definimos TextView
        date =(TextView) this.findViewById(R.id.tvFecha);
        tvHour =(TextView)this.findViewById(R.id.tvHora);
        nextMeeting =(TextView)this.findViewById(R.id. tvProxima);
        assistants =(TextView)this.findViewById ( R.id.tvAsistentes );
        //definimos checbox
        alberto = (CheckBox) findViewById ( R.id.Alberto);
        dei = (CheckBox) findViewById ( R.id.Dei );
        adrian = (CheckBox) findViewById ( R.id.Adrian );
        sergio= (CheckBox) findViewById ( R.id.sergio);
        silvia = (CheckBox) findViewById ( R.id.silvia );
        jorge= (CheckBox) findViewById ( R.id.jorge );
        david = (CheckBox) findViewById ( R.id.david);
        //definimos DataManager
        dataManager =new DataManager(this);
        //creamos intent para el método onClick de listado en Gestión Datos
        Intent intent=this.getIntent ();
        if(intent.getSerializableExtra ( "Act" )!=null){
            fillFields( (Act) intent.getSerializableExtra ( "Act" ) );
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

        if (id == R.id.dices) {
            Intent intent_dices=new Intent(this, MainDicesActivity.class);
            this.startActivity(intent_dices);
        } else if (id == R.id.voting) {
            Intent intent_voting=new Intent(this, ConfigVotingActivity.class);
            this.startActivity(intent_voting);
        } else if (id == R.id.acts) {
            Intent intent_acts=new Intent(this, MainActsActivity.class);
            this.startActivity(intent_acts);
        } else if (id == R.id.expenses) {
            Intent intent_expenses=new Intent(this, MainExpensesActivity.class);
            this.startActivity(intent_expenses);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    //----------------------------Reconoce todos los checked--------------------------------------------
    public void logCheckbox(View v) {
        String s="Estado: " + (alberto.isChecked () ? "Alberto" : "Alberto No Asiste") + "-" + (dei.isChecked () ? "Dei" : " Dei No Asiste")
                + "-" + (adrian.isChecked () ? "Adrian" : "Adrian No Asiste") + "-" + (sergio.isChecked () ? "Sergio" : "Sergio No Asiste")
                + "-" + (silvia.isChecked () ? "Silvia" : "Silvia No Asiste") + "-" + (jorge.isChecked () ? "Jorge" : "Jorge No Asiste")
                + "-" + (david.isChecked () ? "David" : "David No Asiste");
        assistants.setText ( s );

    }


    //-------------------------Metodo para mostrar el calendario----------------------------------------
    public void date(View v) {
        //cuando seleccionamos date
        if ( date.equals ( v ) ) {
            date.setOnClickListener (new View.OnClickListener () {
                @Override
                @TargetApi(24)
                public void onClick(View v) {

                    Calendar cal = Calendar.getInstance ();
                    //Generar cuadro de dialogo de date
                    DatePickerDialog dgDate = new DatePickerDialog ( MainActsActivity.this, new DatePickerDialog.OnDateSetListener () {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            //Cada vez que se seleccione una date se genera una cadena con los datos de la feccha seleccionada.
                            String fechaselec = view.getDayOfMonth () + "/" + (view.getMonth () + 1) + "/" + view.getYear ();
                            //Volcamos la cadena de date en el TextView
                            date.setText ( fechaselec );
                        }
                    }, cal.get ( Calendar.YEAR ), cal.get ( Calendar.MONTH ), cal.get ( Calendar.DAY_OF_MONTH ) );

                    dgDate.show ();
                }
            } );
        }
        //Cuando seleccionamos Proxima, introducimos su date
        if ( nextMeeting.equals ( v ) ) {
            nextMeeting.setOnClickListener (new View.OnClickListener () {
                @Override
                @TargetApi(24)
                public void onClick(View v) {

                    Calendar cal = Calendar.getInstance ();
                    //Generar cuadro de dialogo de date
                    DatePickerDialog dgfecha = new DatePickerDialog ( MainActsActivity.this, new DatePickerDialog.OnDateSetListener () {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            //Cada vez que se seleccione una date se genera una cadena con los datos de la feccha seleccionada.
                            String fechaselec = view.getDayOfMonth () + "/" + (view.getMonth () + 1) + "/" + view.getYear ();
                            //Volcamos la cadena de date en el TextView
                            nextMeeting.setText ( fechaselec );
                        }
                    }, cal.get ( Calendar.YEAR ), cal.get ( Calendar.MONTH ), cal.get ( Calendar.DAY_OF_MONTH ) );

                    dgfecha.show ();
                }
            } );
        }
    }

//--------------------------------------------------------------------------------------------------

    //-----------------------------Metodo para la Hora--------------------------------------------------
    public void hora(View v){
        Calendar cal= Calendar.getInstance();

        TimePickerDialog tgHora=new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String hora=hourOfDay+":"+minute;
                tvHour.setText(hora);
            }
        },cal.get(Calendar.HOUR),cal.get(Calendar.MINUTE),true);
        tgHora.show();

    }
//--------------------------------------------------------------------------------------------------

    //------------------------------Metodo que llama a la funcion de save----------------------------
    public void guardar(View v){
        //creamos objeto donde está guardado el método para save
        dataManager =new DataManager( this );
        //y definimos lo que debe tener
        if(title.getText().length()>0 && date.getText().length()>0 && tvHour.getText().length()>0
                && assistants.getText().length()>0 && relief.getText().length()>0 && memory.getText().length()>0
                && fPoints.getText().length()>0 && conclusions.getText().length()>0 && next.getText().length()>0
                && compromise.getText().length()>0 && proposals.getText().length()>0 && evaluation.getText().length()>0
                && nextMeeting.getText().length()>0 ){
            Act ac=new Act(title.getText().toString(), date.getText().toString(),
                    tvHour.getText().toString(), assistants.getText().toString(),
                    relief.getText().toString(), memory.getText().toString(),
                    fPoints.getText().toString(), conclusions.getText().toString(),
                    next.getText().toString(), compromise.getText().toString(),
                    proposals.getText().toString(), evaluation.getText().toString(),
                    nextMeeting.getText().toString());


            //comprueba si está repetida el acta
            if(!dataManager.checkData(ac.getTitle())){
                dataManager.saveData(ac);
                Toast.makeText(this, "Act guardada correctamente", Toast.LENGTH_LONG).show();

            }else{
                Toast.makeText(this, "Act ya introducida", Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(this, "Es necesario completar todos los campos", Toast.LENGTH_LONG).show();
        }
    }
    //-----------------------------------Enviar mensaje-------------------------------------------------
    public void enviar(View v) {
        Export exp=new Export();
        exp.execute ( "" );
    }


//--------------------------------------------------------------------------------------------------

    //---------------------------------Metodo que llama a la actividad listado--------------------------
    public void listado(View v){
        //mostramos la actividad del listado
        Intent intent =new Intent(this,ListActsActivity.class);
        this.startActivity(intent);
    }
//--------------------------------------------------------------------------------------------------

//------------------------------Boton Salir---------------------------------------------------------

    public void salir(View v){
        this.finish ();
    }

//--------------------------------------------------------------------------------------------------

    //------------------------Asyntask enviar-----------------------------------------------------------
    public class Export extends AsyncTask<String,Void,String> {
        @Override
        protected String doInBackground(String... params) {

            if( Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
                //Obtener los datos a save en el archivo y el nombre del archivo.
                Act act = dataManager.GetUniqueAct();

                String filename= act.getTitle()+".txt";

                //Crear la carpeta
                File folder = new File(Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_DOWNLOADS), "Ibericoders");
                if (!folder.mkdirs()) {
                    folder.mkdirs();
                }
                //Crear el archivo
                File file = new File(folder, filename);
                //Escribir datos en el archivo
                try {
                    FileWriter writer = new FileWriter(file);

                    writer.append(act.toString()+"\n");
                    writer.flush();
                    writer.close();
                }catch(IOException ex){
                    ex.printStackTrace();
                }
                //Enviar el archivo por email

                //Obtener localizacion
                File filelocation = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)+"/Ibericoders/", filename);
                Uri path = Uri.fromFile(filelocation);
                // Indicar que vas a mandar un email
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent .setType("vnd.android.cursor.dir/email");
                //Recipientes del email
                String to[] = {"ibericoders@gmail.com",params[0]};
                emailIntent .putExtra(Intent.EXTRA_EMAIL, to);
                // Añadir el adjunto
                emailIntent .putExtra(Intent.EXTRA_STREAM, path);
                // Asunto del email
                emailIntent .putExtra(Intent.EXTRA_SUBJECT, "Act Exportada");
                emailIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                //Enviar email
                startActivityForResult(Intent.createChooser(emailIntent , "Enviar email..."),10);
            }else{
                Toast.makeText(MainActsActivity.this,"La carpeta DOWNLOADS no es accesible.",Toast.LENGTH_LONG).show();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(MainActsActivity.this, "Base de datos exportada correctamente.", Toast.LENGTH_LONG).show();
        }
    }
//--------------------------------------------------------------------------------------------------

//--------------------------------Asynctask rellenado de Campos--------------------------------------

    public void fillFields(Act ac){
        title.setText(ac.getTitle());
        date.setText ( ac.getDate());
        tvHour.setText(ac.getHour());
        assistants.setText ( ac.getAssitants() );
        relief.setText(ac.getReliefs());
        memory.setText(ac.getMemory());
        fPoints.setText(ac.getPoint());
        conclusions.setText(ac.getConclusion ());
        next.setText(ac.getNext());
        compromise.setText(ac.getCompromise());
        proposals.setText(ac.getProposals());
        evaluation.setText(ac.getEvaluation());

    }
//--------------------------------------------------------------------------------------------------
}
