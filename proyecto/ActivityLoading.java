package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ActivityLoading extends AppCompatActivity {

    private ImageView imageView;
    private TextView textView;
    private ProgressBar progressBar;

    private String[] textArray = {"Texto 1", "Texto 2", "Texto 3", "Texto 4", "Texto 5"};

    private int progressStatus = 0;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        imageView = findViewById(R.id.imageView);
        textView = findViewById(R.id.textView);
        progressBar = findViewById(R.id.progressBar);

        imageView.setImageResource(R.drawable.icono_carga);
        textView.setText(textArray[0]);

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
                            }
                            if (progressStatus == 100) {
                                // Cambiar a otra actividad
                                Intent intent = new Intent(ActivityLoading.this, Activity_principal.class);
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