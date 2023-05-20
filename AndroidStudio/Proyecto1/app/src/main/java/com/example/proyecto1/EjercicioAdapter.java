package com.example.proyecto1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

public class EjercicioAdapter extends ArrayAdapter<Ejercicio> {

    public EjercicioAdapter(Context context, List<Ejercicio> ejercicios) {
        super(context, 0, ejercicios);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Ejercicio ejercicio = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_ejercicio, parent, false);
        }

        EjercicioViewHolder viewHolder = new EjercicioViewHolder(convertView);
        viewHolder.bindData(ejercicio);

        return convertView;
    }
}
