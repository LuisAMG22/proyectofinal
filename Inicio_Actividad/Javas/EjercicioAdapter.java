package com.example.pruebasproyecto;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class EjercicioAdapter extends ArrayAdapter<Ejercicio> {

    private OnEliminarClickListener eliminarClickListener;

    public EjercicioAdapter(Context context, List<Ejercicio> ejercicios) {
        super(context, 0, ejercicios);
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Ejercicio ejercicio = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_ejercicio, parent, false);
        }

        EjercicioViewHolder viewHolder = new EjercicioViewHolder(convertView);
        viewHolder.bindData(ejercicio);

        ImageButton botonEliminar = convertView.findViewById(R.id.botonEliminar);
        botonEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (eliminarClickListener != null) {
                    eliminarClickListener.onEliminarClick(position);
                }
            }
        });

        return convertView;
    }

    public void setOnEliminarClickListener(OnEliminarClickListener listener) {
        eliminarClickListener = listener;
    }

    public interface OnEliminarClickListener {
        void onEliminarClick(int position);
    }
}
