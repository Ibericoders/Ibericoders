package com.ibericoders.ibericoders.actas.model;

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;

/**
 * Created by Jorge on 15/06/2017.
 */

public class GestionDatos {
    String ruta = "actas.txt";
    Context ctx;

    public GestionDatos(Context ctx) {
        this.ctx = ctx;
    }




    //---------------------------------Metodo guardar---------------------------------------------------
    public void guardarActa(Acta ac) {
        try {
            FileOutputStream fos = ctx.openFileOutput(ruta, Context.MODE_APPEND);
            PrintStream salida = new PrintStream(fos);
            salida.println(ac.getTitulo() + "|" + ac.getFecha() + "|" + ac.getHora() + "|" +
                    ac.getAsistentes() + "|" + ac.getRelevos() + "|" + ac.getMemoria() + "|" + ac.getPuntos()
                    + "|" + ac.getConclusion() + "|" + ac.getSiguiente() + "|" + ac.getCompromisos() + "|" +
                    "|" + ac.getPropuestas() + "|" + ac.getEvaluacion() + "|" + ac.getProxima());


            salida.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
//--------------------------------------------------------------------------------------------------

    //-------------------------------Metodo recuperar acta----------------------------------------------
    public ArrayList<Acta> recuperarActa() {
        ArrayList<Acta> actas = new ArrayList<>();
        String s;
        try {
            FileInputStream fis = ctx.openFileInput(ruta);
            BufferedReader bf = new BufferedReader(new InputStreamReader(fis));
            while ((s = bf.readLine()) != null) {
                String[] datos = s.split("[|]");
                Acta ac = new Acta(datos[0], datos[1], datos[2], datos[3],
                        datos[4], datos[5], datos[6], datos[7], datos[8],
                        datos[9], datos[10], datos[11], datos[12]);
                actas.add(ac);
            }
            bf.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return actas;
    }
//--------------------------------------------------------------------------------------------------

//------------------------------Recuperar un solo acta----------------------------------------------

    public Acta recuperarUnica(){
        ArrayList<Acta> actas = new ArrayList<>();
        String s;
        try {
            FileInputStream fis = ctx.openFileInput(ruta);
            BufferedReader bf = new BufferedReader(new InputStreamReader(fis));
            while ((s = bf.readLine()) != null) {
                String[] datos = s.split("[|]");
                Acta ac = new Acta(datos[0], datos[1], datos[2], datos[3],
                        datos[4], datos[5], datos[6], datos[7], datos[8],
                        datos[9], datos[10], datos[11], datos[12]);
                actas.add(ac);
            }
            bf.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return actas.get(actas.size ()-1);
    }

//--------------------------------------------------------------------------------------------------

//-------------------------------Método comprobación------------------------------------------------

    public boolean comprobarDatos(String titulo){
        boolean res = false;

        for(int i = 0;i<recuperarActa().size() ;i++){
            //obtenemos el titulo de cada acta y lo
            //comparamos con el valor recibido
            if(recuperarActa().equals(titulo)){
                //System.out.println("Repetido");
                res = true;
            }
        }
        return res;
    }
//--------------------------------------------------------------------------------------------------
}