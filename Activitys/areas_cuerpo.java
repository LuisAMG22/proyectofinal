package com.example.proyecto;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;


public class areas_cuerpo extends AppCompatActivity {

    private ImageView bodyImage;
    private Button hombrosButton;
    private Button brazosButton;
    private Button piernasButton;
    private Button pechoButton;

    private boolean hombrosSelected = false;
    private boolean brazosSelected = false;
    private boolean piernasSelected = false;
    private boolean pechoSelected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.areas_cuerpo);

        bodyImage = findViewById(R.id.bodyImage);
        hombrosButton = findViewById(R.id.hombrosButton);
        brazosButton = findViewById(R.id.brazosButton);
        piernasButton = findViewById(R.id.piernasButton);
        pechoButton = findViewById(R.id.pechoButton);
    }

    public void onPartButtonClick(View view) {
        switch (view.getId()) {
            case R.id.hombrosButton:
                hombrosSelected = !hombrosSelected;
                break;
            case R.id.brazosButton:
                brazosSelected = !brazosSelected;
                break;
            case R.id.piernasButton:
                piernasSelected = !piernasSelected;
                break;
            case R.id.pechoButton:
                pechoSelected = !pechoSelected;
                break;
        }

        updateBodyImage();
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

