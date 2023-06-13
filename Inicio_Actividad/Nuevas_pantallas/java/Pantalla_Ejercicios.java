package com.example.pantallaejercicios;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class Pantalla_Ejercicios extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_ejercicios);

        LinearLayout ejer_hombro = findViewById(R.id.ejer_hombro);
        ejer_hombro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Pantalla_Ejercicios.this, Descripcion_hombro.class);
                startActivity(intent);
            }
        });

        LinearLayout ejer_brazos = findViewById(R.id.ejer_brazos);
        ejer_brazos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Pantalla_Ejercicios.this, Descripcion_brazos.class);
                startActivity(intent);
            }
        });

        LinearLayout ejer_pecho = findViewById(R.id.ejer_pecho);
        ejer_pecho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Pantalla_Ejercicios.this, Descripcion_pecho.class);
                startActivity(intent);
            }
        });

        LinearLayout ejer_piernas = findViewById(R.id.ejer_piernas);
        ejer_piernas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Pantalla_Ejercicios.this, Descripcion_piernas.class);
                startActivity(intent);
            }
        });

    }
}