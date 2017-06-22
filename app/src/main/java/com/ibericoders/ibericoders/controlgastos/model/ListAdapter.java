package com.ibericoders.ibericoders.controlgastos.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ibericoders.ibericoders.R;

import java.util.ArrayList;


public class ListAdapter extends BaseAdapter {

    private ArrayList<Expense> data;
    private LayoutInflater lf;

    public ListAdapter(Context ctx, ArrayList<Expense> data){
        this.data = data;
        lf=LayoutInflater.from(ctx);
    }
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        //Generamos un objeto View a partir de la plantilla creada para la fila.
        convertView=lf.inflate(R.layout.tarjeta_gasto,null);

        //Nombre Expense
        TextView tvName=(TextView)convertView.findViewById(R.id.tv_nombreGasto);
        tvName.setText(data.get(position).getName());

        //Descripcion Expense
        TextView tvDescripcion=(TextView)convertView.findViewById(R.id.tv_descripcionGasto);
        tvDescripcion.setText(data.get(position).getDescription());

        //Cantidad Expense
        TextView tvCantidad=(TextView)convertView.findViewById(R.id.tv_cantidadGasto);
        tvCantidad.setText(String.valueOf(data.get(position).getAmount()));

        //Fecha Expense
        TextView tvFecha=(TextView)convertView.findViewById(R.id.tv_fechaGasto);
        tvFecha.setText(data.get(position).getDate());

        //Categoría Expense
        TextView tvCat=(TextView)convertView.findViewById(R.id.tv_categoriaGasto);
        tvCat.setText(data.get(position).getCategory());

        //Devolver el view de la fila
        return convertView;
    }
}
