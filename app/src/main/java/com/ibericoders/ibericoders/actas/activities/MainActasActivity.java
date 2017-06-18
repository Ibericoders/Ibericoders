package com.ibericoders.ibericoders.actas.activities;

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
import com.ibericoders.ibericoders.actas.model.Acta;
import com.ibericoders.ibericoders.actas.model.GestionDatos;
import com.ibericoders.ibericoders.controlgastos.activities.MainGastosActivity;
import com.ibericoders.ibericoders.dados.MainDicesActivity;
import com.ibericoders.ibericoders.votaciones.activities.ConfigVotationActivity;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

public class MainActasActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView fecha, tvHora, proxima,asistentes;
    CheckBox alberto, dei, adrian, sergio, silvia, jorge, david;
    GestionDatos gdatos;
    EditText titulo, relevo, memoria, puntosF, conclusion, siguiente,
            compromiso, propuesta, evaluacion;

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
        titulo=(EditText)this.findViewById(R.id.edtTitulo);
        relevo=(EditText)this.findViewById(R.id.edtRelevo);
        memoria=(EditText)this.findViewById(R.id.edtMemoria);
        puntosF=(EditText)this.findViewById(R.id.edtPuntosF);
        conclusion=(EditText)this.findViewById(R.id.edtConclusion);
        siguiente=(EditText)this.findViewById(R.id.edtSiguiente);
        compromiso=(EditText)this.findViewById(R.id.edtCompromiso);
        propuesta=(EditText)this.findViewById(R.id.edtPropuesta);
        evaluacion=(EditText)this.findViewById(R.id.edtEvaluacion);
        //definimos TextView
        fecha=(TextView) this.findViewById(R.id.tvFecha);
        tvHora=(TextView)this.findViewById(R.id.tvHora);
        proxima=(TextView)this.findViewById(R.id. tvProxima);
        asistentes=(TextView)this.findViewById ( R.id.tvAsistentes );
        //definimos checbox
        alberto = (CheckBox) findViewById ( R.id.Alberto);
        dei = (CheckBox) findViewById ( R.id.Dei );
        adrian = (CheckBox) findViewById ( R.id.Adrian );
        sergio= (CheckBox) findViewById ( R.id.sergio);
        silvia = (CheckBox) findViewById ( R.id.silvia );
        jorge= (CheckBox) findViewById ( R.id.jorge );
        david = (CheckBox) findViewById ( R.id.david);
        //definimos GestionDatos
        gdatos=new GestionDatos (this);
        //creamos intent para el método onClick de listado en Gestión Datos
        Intent intent=this.getIntent ();
        if(intent.getSerializableExtra ( "Acta" )!=null){
            rellenarCampos( (Acta) intent.getSerializableExtra ( "Acta" ) );
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
    //----------------------------Reconoce todos los checked--------------------------------------------
    public void loguearCheckbox(View v) {
        String s="Estado: " + (alberto.isChecked () ? "Alberto" : "Alberto No Asiste") + "-" + (dei.isChecked () ? "Dei" : " Dei No Asiste")
                + "-" + (adrian.isChecked () ? "Adrian" : "Adrian No Asiste") + "-" + (sergio.isChecked () ? "Sergio" : "Sergio No Asiste")
                + "-" + (silvia.isChecked () ? "Silvia" : "Silvia No Asiste") + "-" + (jorge.isChecked () ? "Jorge" : "Jorge No Asiste")
                + "-" + (david.isChecked () ? "David" : "David No Asiste");
        asistentes.setText ( s );

    }


    //-------------------------Metodo para mostrar el calendario----------------------------------------
    public void fecha(View v) {
        //cuando seleccionamos fecha
        if ( fecha.equals ( v ) ) {
            fecha.setOnClickListener ( new View.OnClickListener () {
                @Override
                @TargetApi(24)
                public void onClick(View v) {

                    Calendar cal = Calendar.getInstance ();
                    //Generar cuadro de dialogo de fecha
                    DatePickerDialog dgfecha = new DatePickerDialog ( MainActasActivity.this, new DatePickerDialog.OnDateSetListener () {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            //Cada vez que se seleccione una fecha se genera una cadena con los datos de la feccha seleccionada.
                            String fechaselec = view.getDayOfMonth () + "/" + (view.getMonth () + 1) + "/" + view.getYear ();
                            //Volcamos la cadena de fecha en el TextView
                            fecha.setText ( fechaselec );
                        }
                    }, cal.get ( Calendar.YEAR ), cal.get ( Calendar.MONTH ), cal.get ( Calendar.DAY_OF_MONTH ) );

                    dgfecha.show ();
                }
            } );
        }
        //Cuando seleccionamos Proxima, introducimos su fecha
        if ( proxima.equals ( v ) ) {
            proxima.setOnClickListener ( new View.OnClickListener () {
                @Override
                @TargetApi(24)
                public void onClick(View v) {

                    Calendar cal = Calendar.getInstance ();
                    //Generar cuadro de dialogo de fecha
                    DatePickerDialog dgfecha = new DatePickerDialog ( MainActasActivity.this, new DatePickerDialog.OnDateSetListener () {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            //Cada vez que se seleccione una fecha se genera una cadena con los datos de la feccha seleccionada.
                            String fechaselec = view.getDayOfMonth () + "/" + (view.getMonth () + 1) + "/" + view.getYear ();
                            //Volcamos la cadena de fecha en el TextView
                            proxima.setText ( fechaselec );
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
                tvHora.setText(hora);
            }
        },cal.get(Calendar.HOUR),cal.get(Calendar.MINUTE),true);
        tgHora.show();

    }
//--------------------------------------------------------------------------------------------------

    //------------------------------Metodo que llama a la funcion de guardar----------------------------
    public void guardar(View v){
        //creamos objeto donde está guardado el método para guardar
        gdatos=new GestionDatos ( this );
        //y definimos lo que debe tener
        if(titulo.getText().length()>0 && fecha.getText().length()>0 && tvHora.getText().length()>0
                && asistentes.getText().length()>0 && relevo.getText().length()>0 && memoria.getText().length()>0
                && puntosF.getText().length()>0 && conclusion.getText().length()>0 && siguiente.getText().length()>0
                && compromiso.getText().length()>0 && propuesta.getText().length()>0 && evaluacion.getText().length()>0
                && proxima.getText().length()>0 ){
            Acta ac=new Acta(titulo.getText().toString(), fecha.getText().toString(),
                    tvHora.getText().toString(), asistentes.getText().toString(),
                    relevo.getText().toString(),memoria.getText().toString(),
                    puntosF.getText().toString(),conclusion.getText().toString(),
                    siguiente.getText().toString(),compromiso.getText().toString(),
                    propuesta.getText().toString(),evaluacion.getText().toString(),
                    proxima.getText().toString());


            //comprueba si está repetida el acta
            if(!gdatos.comprobarDatos(ac.getTitulo())){
                gdatos.guardarActa(ac);
                Toast.makeText(this, "Acta guardada correctamente", Toast.LENGTH_LONG).show();

            }else{
                Toast.makeText(this, "Acta ya introducida", Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(this, "Es necesario completar todos los campos", Toast.LENGTH_LONG).show();
        }
    }
    //-----------------------------------Enviar mensaje-------------------------------------------------
    public void enviar(View v) {
        Exportar exp=new Exportar ();
        exp.execute ( "" );
    }


//--------------------------------------------------------------------------------------------------

    //---------------------------------Metodo que llama a la actividad listado--------------------------
    public void listado(View v){
        //mostramos la actividad del listado
        Intent intent =new Intent(this,ListadoActasActivity.class);
        this.startActivity(intent);
    }
//--------------------------------------------------------------------------------------------------

//------------------------------Boton Salir---------------------------------------------------------

    public void salir(View v){
        this.finish ();
    }

//--------------------------------------------------------------------------------------------------

    //------------------------Asyntask enviar-----------------------------------------------------------
    public class Exportar extends AsyncTask<String,Void,String> {
        @Override
        protected String doInBackground(String... params) {

            if( Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
                //Obtener los datos a guardar en el archivo y el nombre del archivo.
                Acta acta=gdatos.recuperarUnica ();

                String filename=acta.getTitulo ()+".txt";

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

                    writer.append(acta.toString()+"\n");
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
                emailIntent .putExtra(Intent.EXTRA_SUBJECT, "Acta Exportada");
                emailIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                //Enviar email
                startActivityForResult(Intent.createChooser(emailIntent , "Enviar email..."),10);
            }else{
                Toast.makeText(MainActasActivity.this,"La carpeta DOWNLOADS no es accesible.",Toast.LENGTH_LONG).show();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(MainActasActivity.this, "Base de datos exportada correctamente.", Toast.LENGTH_LONG).show();
        }
    }
//--------------------------------------------------------------------------------------------------

//--------------------------------Asyntask rellenado de Campos--------------------------------------

    public void rellenarCampos(Acta ac){
        titulo.setText(ac.getTitulo ());
        fecha.setText ( ac.getFecha ());
        tvHora.setText(ac.getHora ());
        asistentes.setText ( ac.getAsistentes () );
        relevo.setText(ac.getRelevos ());
        memoria.setText(ac.getMemoria ());
        puntosF.setText(ac.getPuntos ());
        conclusion.setText(ac.getConclusion ());
        siguiente.setText(ac.getSiguiente ());
        compromiso.setText(ac.getCompromisos ());
        propuesta.setText(ac.getPropuestas ());
        evaluacion.setText(ac.getEvaluacion ());

    }
//--------------------------------------------------------------------------------------------------
}
