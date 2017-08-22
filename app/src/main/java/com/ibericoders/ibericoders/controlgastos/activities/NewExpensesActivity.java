package com.ibericoders.ibericoders.controlgastos.activities;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.ibericoders.ibericoders.R;
import com.ibericoders.ibericoders.controlgastos.model.Expense;
import com.ibericoders.ibericoders.controlgastos.model.ExpensesData;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewExpensesActivity extends AppCompatActivity {

    /*
     * Atributos de UI
     */
    @BindView(R.id.edt_nombreGasto) EditText name;

    @BindView(R.id.edt_descripcionGasto) EditText description;

    @BindView(R.id.edt_cantidadGasto) EditText amount;

    @BindView(R.id.edt_fechaGasto) EditText date;

    @BindView(R.id.sp_categoriaGasto) Spinner sp;

    /*
     * Atributos de negocio
     */
    private ExpensesData expensesData;
    private String cat=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_gasto);
        ButterKnife.bind(this);

        expensesData =new ExpensesData(this);


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, new String[]{"Seleccione categoría...", "Cat1", "Cat2", "Cat3", "Cat4", "Cat5"});
        sp.setAdapter(adapter);

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cat=parent.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            @TargetApi(24)
            public void onClick(View v) {

                Calendar cal=Calendar.getInstance();
                //Generar cuadro de dialogo de date
                DatePickerDialog dgDate=new DatePickerDialog(NewExpensesActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        //Cada vez que se seleccione una date se genera una cadena con los datos de la feccha seleccionada.
                        String fechaselec=view.getDayOfMonth()+"/"+(view.getMonth()+1)+"/"+view.getYear();
                        //Volcamos la cadena de date en el TextView
                        date.setText(fechaselec);
                    }
                }, cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH));

                dgDate.show();
            }
        });
    }

    public void cancel(View v){
        this.finish();
    }
    public void save(View v){
        if(name.getText().length()>0 && description.getText().length()>0 && amount.getText().length()>0 && date.getText().length()>0 && cat!=null && !cat.equals("Seleccione categoría...")){
            Expense g=new Expense(name.getText().toString(), description.getText().toString(),Double.parseDouble(amount.getText().toString()), date.getText().toString(),cat);
            if(!expensesData.CheckExpense(g.getName())){
                expensesData.SaveNewExpense(g);
                Toast.makeText(this, "Gasto introducido correctamente", Toast.LENGTH_LONG).show();

                SharedPreferences prefs=getSharedPreferences("bote", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=prefs.edit();
                if(prefs.getString("bote","null").equals("null")){
                    editor.remove("bote");
                    editor.putString("bote","-"+String.valueOf(g.getAmount()));
                }else{
                    double valorAnterior=Double.parseDouble(prefs.getString("bote",null));
                    String res=String.valueOf(valorAnterior-g.getAmount());
                    editor.remove("bote");
                    editor.putString("bote",res);
                }
                editor.apply();
                this.finish();
            }else{
                Toast.makeText(this, "Gasto ya introducido", Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(this, "Es necesario completar todos los campos", Toast.LENGTH_LONG).show();
        }

    }
}
