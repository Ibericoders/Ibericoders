package com.ibericoders.ibericoders.controlgastos.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class Helper extends SQLiteOpenHelper {

    public Helper(Context ctx, String name, int version){
        //Recibimos los datos y los envía a SQLiteOpenHelper para empezar el proceso de creación de la BD
        super(ctx,name,null,version);
    }

    public Helper(Context ctx, String name){
        //Recibimos los datos y los envía a SQLiteOpenHelper para empezar el proceso de creación de la BD
        super(ctx,name,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Se le llama para construir las tablas de la base de datos.
        //Instruccion SQL de creacion de la tabla de expenses.
        String sqlCrearTabla1="create table expensesTable (_id integer primary key autoincrement,";
        sqlCrearTabla1+="name text,description text,amount double,date text,category text)";
        //Ejecutar la instrucción 1.
        db.execSQL(sqlCrearTabla1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Instrucciones sobre qué hacer cuando se actualice la version de la base de datos.

    }
}