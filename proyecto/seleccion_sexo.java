package com.example.proyecto;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class seleccion_sexo extends AppCompatActivity {

    private int progress = 0;
    private ImageView image1, image2;
    private TextView textView;
    private ProgressBar progressBar;
    private Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seleccion_sexo);

        progressBar = findViewById(R.id.progressBar);
        textView = findViewById(R.id.textView);
        image1 = findViewById(R.id.hombre);
        image2 = findViewById(R.id.mujer);
        nextButton = findViewById(R.id.nextButton);

        nextButton.setEnabled(false); // Deshabilitar el botón por defecto

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(seleccion_sexo.this, areas_cuerpo.class);
                intent.putExtra("progress", progressBar.getProgress());
                startActivity(intent);


            }
        });

        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Acción cuando se hace clic en la imagen 1
                Toast.makeText(getApplicationContext(), "Seleccion Hombre", Toast.LENGTH_SHORT).show();
                image1.setScaleX(1.2f); // Aumentar la escala de la imagen 1
                image1.setScaleY(1.2f);
                image2.setScaleX(0.8f); // Restablecer la escala de la imagen 2
                image2.setScaleY(0.8f);

                nextButton.setEnabled(true); // Habilitar el botón

            }
        });

        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Acción cuando se hace clic en la imagen 2
                Toast.makeText(getApplicationContext(), "Seleccion Mujer", Toast.LENGTH_SHORT).show();
                // Aumentar escala de la imagen 2 (mujer)
                image2.setScaleX(1.2f);
                image2.setScaleY(1.2f);

                // Reducir escala de la imagen 1 (hombre)
                image1.setScaleX(0.8f);
                image1.setScaleY(0.8f);
                nextButton.setEnabled(true); // Habilitar el botón

            }
        });
    }
}

