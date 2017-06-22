package com.ibericoders.ibericoders.acts.model;

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;


public class DataManager {
    private String ruta = "acts.txt";
    private Context ctx;

    public DataManager(Context ctx) {
        this.ctx = ctx;
    }




    //---------------------------------Metodo save---------------------------------------------------
    public void saveData(Act ac) {

        //Guarda los ficheros en un formato determinado.
        try {
            FileOutputStream fileoutputstream = ctx.openFileOutput(ruta, Context.MODE_APPEND);
            PrintStream printstream = new PrintStream(fileoutputstream);
            printstream.println(ac.getTitle() + "|" + ac.getDate() + "|" + ac.getHour() + "|" +
                    ac.getAssitants() + "|" + ac.getReliefs() + "|" + ac.getMemory() + "|" + ac.getPoint()
                    + "|" + ac.getConclusion() + "|" + ac.getNext() + "|" + ac.getCompromise() + "|" +
                    "|" + ac.getProposals() + "|" + ac.getEvaluation() + "|" + ac.getNextMeeting());


            printstream.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
//--------------------------------------------------------------------------------------------------

    //-------------------------------Metodo recuperar acta----------------------------------------------
    public ArrayList<Act> getPreviousAct() {
        ArrayList<Act> acts = new ArrayList<>();
        String s;
        try {
            FileInputStream fis = ctx.openFileInput(ruta);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fis));
            while ((s = bufferedReader.readLine()) != null) {
                String[] datos = s.split("[|]");
                Act ac = new Act(datos[0], datos[1], datos[2], datos[3],
                        datos[4], datos[5], datos[6], datos[7], datos[8],
                        datos[9], datos[10], datos[11], datos[12]);
                acts.add(ac);
            }
            bufferedReader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return acts;
    }
//--------------------------------------------------------------------------------------------------

//------------------------------Recuperar un solo acta----------------------------------------------

    public Act GetUniqueAct(){
        ArrayList<Act> acts = new ArrayList<>();
        String s;
        try {
            FileInputStream fis = ctx.openFileInput(ruta);
            BufferedReader bf = new BufferedReader(new InputStreamReader(fis));
            while ((s = bf.readLine()) != null) {
                String[] datos = s.split("[|]");
                Act ac = new Act(datos[0], datos[1], datos[2], datos[3],
                        datos[4], datos[5], datos[6], datos[7], datos[8],
                        datos[9], datos[10], datos[11], datos[12]);
                acts.add(ac);
            }
            bf.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return acts.get(acts.size ()-1);
    }

//--------------------------------------------------------------------------------------------------

//-------------------------------Método comprobación------------------------------------------------

    public boolean checkData(String title){
        boolean res = false;

        for(int i = 0; i< getPreviousAct().size() ; i++){
            //obtenemos el titulo de cada acta y lo
            //comparamos con el valor recibido
            if(getPreviousAct().equals(title)){
                //System.out.println("Repetido");
                res = true;
            }
        }
        return res;
    }
//--------------------------------------------------------------------------------------------------
}