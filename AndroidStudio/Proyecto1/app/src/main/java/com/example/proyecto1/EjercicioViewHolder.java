package com.example.proyecto1;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class EjercicioViewHolder {
    private ImageView imagenImageView;
    private TextView numeroTextView;
    private TextView kilosTextView;
    private TextView repeticionesTextView;
    private Button botonEliminar;

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
        kilosTextView.setText(ejercicio.getKilos());
        repeticionesTextView.setText(ejercicio.getRepeticiones());
    }
}
