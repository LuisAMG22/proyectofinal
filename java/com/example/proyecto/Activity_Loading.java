package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

public class Activity_Loading extends AppCompatActivity {

    private ArrayList<String> selectedValues;

    private ImageView imageView;
    private TextView textView, textView2;
    private ProgressBar progressBar;

    private String[] textArray = {"GUARDANDO SELECCIÓN DE SEXO", "GUARDANDO ÁREAS CORPORALES", "GUARDANDO ALTURA", "GUARDANDO PESO", "GUARDANDO OBJETIVO"};
    private String[] textArray2 = {"20%", "40%", "60%", "80%", "99%"};

    private int progressStatus = 0;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        Intent intent = getIntent();
        ArrayList<String> selectedValues = intent.getStringArrayListExtra("selectedValues"); //datos de las seleccion cuerpo
        String sexo = intent.getStringExtra("sexo"); //datos del sexo
        String selectedValue = intent.getStringExtra("selectedValue"); //datos del objetivo

        //System.out.println(selectedValues); con estos datos se rellena la bbdd
        //System.out.println(sexo);
        //System.out.println(selectedValue);


        imageView = findViewById(R.id.imageView);
        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView6);
        progressBar = findViewById(R.id.progressBar);

        imageView.setImageResource(R.drawable.icono_personalizar);
        textView.setText(textArray[0]);
        textView2.setText(textArray2[0]);

        // Iniciar la carga gradual del ProgressBar
        new Thread(new Runnable() {
            public void run() {
                while (progressStatus < 100) {
                    progressStatus++;
                    handler.post(new Runnable() {
                        public void run() {
                            progressBar.setProgress(progressStatus);
                            // Cambiar el texto cada 20% de progreso
                            if (progressStatus % 20 == 0 && progressStatus!=100) {
                                int index = progressStatus / 20;
                                textView.setText(textArray[index]);
                                textView2.setText(textArray2[index]);
                            }
                            if (progressStatus == 100) {
                                // Cambiar a otra actividad
                                Intent intent = new Intent(Activity_Loading.this, Activity_principal.class);
                                startActivity(intent);
                                finish(); // Finalizar esta actividad
                            }
                        }
                    });
                    try {
                        // Ajustar el retraso para controlar la velocidad de carga
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}