package com.example.proyectopruebas40;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class EjercicioViewHolder {
    private ImageView imagenImageView;
    private TextView numeroTextView;
    private TextView kilosTextView;
    private TextView repeticionesTextView;
    private ImageButton botonEliminar;

    public EjercicioViewHolder(View itemView) {
        imagenImageView = itemView.findViewById(R.id.imagenImageView);
        numeroTextView = itemView.findViewById(R.id.numeroTextView);
        kilosTextView = itemView.findViewById(R.id.kilosTextView);
        repeticionesTextView = itemView.findViewById(R.id.repeticionesTextView);
        botonEliminar = itemView.findViewById(R.id.botonEliminar);
    }

    public void bindData(Ejercicio ejercicio) {
        imagenImageView.setImageResource(ejercicio.getImagen());
        numeroTextView.setText(String.valueOf(ejercicio.getNumero()));
        kilosTextView.setText(String.valueOf(ejercicio.getKilos()));
        repeticionesTextView.setText(String.valueOf(ejercicio.getRepeticiones()));
    }
}
