package com.example.proyecto;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class Activity_areas_cuerpo extends AppCompatActivity {

    private ImageView bodyImage;
    private Button hombrosButton;
    private Button brazosButton;
    private Button piernasButton;
    private Button pechoButton;
    private Button botonsiguiente;

    private boolean hombrosSelected = false;
    private boolean brazosSelected = false;
    private boolean piernasSelected = false;
    private boolean pechoSelected = false;

    private ArrayList<String> selectedValues = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_areas_cuerpo);

        Intent intent = getIntent();
        String sexo = intent.getStringExtra("sexo");


        bodyImage = findViewById(R.id.bodyImage);
        hombrosButton = findViewById(R.id.hombrosButton);
        brazosButton = findViewById(R.id.brazosButton);
        piernasButton = findViewById(R.id.piernasButton);
        pechoButton = findViewById(R.id.pechoButton);
        botonsiguiente = findViewById(R.id.botonsiguiente);

        botonsiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_areas_cuerpo.this, Activity_objetivo.class);
                if (selectedValues.isEmpty()) {
                    intent.putExtra("selectedValues", (ArrayList<String>) null);
                    intent.putExtra("sexo",sexo);
                } else {
                    intent.putStringArrayListExtra("selectedValues", selectedValues);
                    intent.putExtra("sexo",sexo);
                }
                startActivity(intent);
            }
        });


    }

    public void onPartButtonClick(View view) {
        Button clickedButton = (Button) view;

        String value = "";

        switch (view.getId()) {
            case R.id.hombrosButton:
                hombrosSelected = !hombrosSelected;
                value = "Hombros";
                break;
            case R.id.brazosButton:
                brazosSelected = !brazosSelected;
                value = "Brazos";
                break;
            case R.id.piernasButton:
                piernasSelected = !piernasSelected;
                value = "Piernas";
                break;
            case R.id.pechoButton:
                pechoSelected = !pechoSelected;
                value = "Pecho";
                break;
        }

        updateButtonColor(clickedButton);
        updateBodyImage();
        updateSelectedValues(value);
    }

    private void updateSelectedValues(String value) {
        if (selectedValues.contains(value)) {
            selectedValues.remove(value);
        } else {
            selectedValues.add(value);
        }
    }

    private void updateButtonColor(Button button) {
        if (button.isSelected()) {
            button.setSelected(false);
            button.setBackgroundColor(Color.parseColor("#FFFFF0")); // Cambia el color de fondo a blanco
        } else {
            button.setSelected(true);
            button.setBackgroundColor(Color.parseColor("#15D63E")); // Cambia el color de fondo a rojo
        }
    }

    private void updateBodyImage() {
        if (hombrosSelected && brazosSelected && piernasSelected && pechoSelected) {
            bodyImage.setImageResource(R.drawable.cuerpo_hombros_brazos_piernas_pecho);
        } else if (hombrosSelected && brazosSelected && piernasSelected) {
            bodyImage.setImageResource(R.drawable.cuerpo_hombros_brazos_piernas);
        } else if (hombrosSelected && brazosSelected && pechoSelected) {
            bodyImage.setImageResource(R.drawable.cuerpo_hombros_brazos_pecho);
        } else if (brazosSelected && piernasSelected && pechoSelected) {
            bodyImage.setImageResource(R.drawable.cuerpo_brazos_piernas_pecho);
        } else if (hombrosSelected && brazosSelected) {
            bodyImage.setImageResource(R.drawable.cuerpo_hombros_brazos);
        } else if (hombrosSelected && piernasSelected) {
            bodyImage.setImageResource(R.drawable.cuerpo_hombros_piernas);
        } else if (hombrosSelected && pechoSelected) {
            bodyImage.setImageResource(R.drawable.cuerpo_hombros_pecho);
        } else if (brazosSelected && piernasSelected) {
            bodyImage.setImageResource(R.drawable.cuerpo_brazos_piernas);
        } else if (brazosSelected && pechoSelected) {
            bodyImage.setImageResource(R.drawable.cuerpo_brazos_pecho);
        } else if (piernasSelected && pechoSelected) {
            bodyImage.setImageResource(R.drawable.cuerpo_piernas_pecho);
        } else if (hombrosSelected) {
            bodyImage.setImageResource(R.drawable.cuerpo_hombros);
        } else if (brazosSelected) {
            bodyImage.setImageResource(R.drawable.cuerpo_brazos);
        } else if (piernasSelected) {
            bodyImage.setImageResource(R.drawable.cuerpo_piernas);
        } else if (pechoSelected) {
            bodyImage.setImageResource(R.drawable.cuerpo_pecho);
        } else {
            bodyImage.setImageResource(R.drawable.cuerpo_normal);
        }
    }
}

