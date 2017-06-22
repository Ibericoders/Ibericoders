package com.ibericoders.ibericoders.acts.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ibericoders.ibericoders.R;

import java.util.ArrayList;


public class AdapterList extends BaseAdapter {
    private Context ctx;
    private ArrayList<Act> datos;
    private LayoutInflater lf;
    public AdapterList(Context ctx, ArrayList<Act> datos){
        this.ctx=ctx;
        this.datos=datos;
        lf=LayoutInflater.from(ctx);
    }

    @Override
    public int getCount() {
        return datos.size();
    }

    @Override
    public Object getItem(int position) {
        return datos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView=lf.inflate(R.layout.fila_actas,null);
        TextView tvTitulo=(TextView)convertView.findViewById(R.id.tvFilaTitulo);
        tvTitulo.setText(datos.get(position).getTitle());
        TextView tvFecha=(TextView)convertView.findViewById(R.id.tvFilaFecha);
        tvFecha.setText(datos.get(position).getDate());
        //dejo la posibilidad de añadir mas filas

        return convertView;
    }
}