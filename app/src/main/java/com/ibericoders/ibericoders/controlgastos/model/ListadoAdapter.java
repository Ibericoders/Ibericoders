package com.ibericoders.ibericoders.controlgastos.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ibericoders.ibericoders.R;

import java.util.ArrayList;

/**
 * Created by Jorge on 15/06/2017.
 */

public class ListadoAdapter extends BaseAdapter {

    private ArrayList<Gasto> datos;
    private LayoutInflater lf;

    public ListadoAdapter(Context ctx, ArrayList<Gasto> datos){
        Context ctx1 = ctx;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        //Generamos un objeto View a partir de la plantilla creada para la fila.
        convertView=lf.inflate(R.layout.tarjeta_gasto,null);

        //Nombre Gasto
        TextView tvNombre=(TextView)convertView.findViewById(R.id.tv_nombreGasto);
        tvNombre.setText(datos.get(position).getNombre());

        //Descripcion Gasto
        TextView tvDescripcion=(TextView)convertView.findViewById(R.id.tv_descripcionGasto);
        tvDescripcion.setText(datos.get(position).getDescripcion());

        //Cantidad Gasto
        TextView tvCantidad=(TextView)convertView.findViewById(R.id.tv_cantidadGasto);
        tvCantidad.setText(String.valueOf(datos.get(position).getCantidad()));

        //Fecha Gasto
        TextView tvFecha=(TextView)convertView.findViewById(R.id.tv_fechaGasto);
        tvFecha.setText(datos.get(position).getFecha());

        //Categoría Gasto
        TextView tvCat=(TextView)convertView.findViewById(R.id.tv_categoriaGasto);
        tvCat.setText(datos.get(position).getCategoria());

        //Devolver el view de la fila
        return convertView;
    }
}
