package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class Activity_objetivo extends AppCompatActivity {

    private ArrayList<String> valores_cuerpo;

    private Button botonsiguiente;
    private ImageButton selectedButton;
    private String objetivo;
    private String sexo;
    private TextView textView;


    private String[] textArray = {"¿Cuál es tu objetivo?"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_objetivo);

        Intent intent = getIntent();
        if (intent != null) {
            valores_cuerpo = intent.getStringArrayListExtra("selectedValues");
            sexo = intent.getStringExtra("sexo");
            if (valores_cuerpo == null) {
                valores_cuerpo = new ArrayList<>(); // Crear un nuevo ArrayList vacío si el valor pasado es nulo
            }
        }

        ImageButton button1 = findViewById(R.id.button1);
        ImageButton button2 = findViewById(R.id.button2);
        ImageButton button3 = findViewById(R.id.button3);
        ImageButton button4 = findViewById(R.id.button4);
        textView = findViewById(R.id.textTitulo);
        textView.setText(textArray[0]);

        botonsiguiente =  findViewById(R.id.siguiente_objetivo);

        botonsiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loadingIntent = new Intent(Activity_objetivo.this, Activity_Loading.class);
                loadingIntent.putStringArrayListExtra("selectedValues", valores_cuerpo);
                loadingIntent.putExtra("sexo",sexo);
                loadingIntent.putExtra("selectedValue", objetivo);
                startActivity(loadingIntent);
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedButton != null) {
                    selectedButton.setSelected(false);
                }
                button1.setSelected(true);
                selectedButton = button1;
                objetivo = "musculo";
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedButton != null) {
                    selectedButton.setSelected(false);
                }
                button2.setSelected(true);
                selectedButton = button2;
                objetivo = "resistencia";
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedButton != null) {
                    selectedButton.setSelected(false);
                }
                button3.setSelected(true);
                selectedButton = button3;
                objetivo = "fuerza";
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedButton != null) {
                    selectedButton.setSelected(false);
                }
                button4.setSelected(true);
                selectedButton = button4;
                objetivo = "tonificar";
            }
        });

    }
}