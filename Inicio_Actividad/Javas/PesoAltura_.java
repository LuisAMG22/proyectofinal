package com.example.pruebasproyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

public class PesoAltura_ extends AppCompatActivity {

    private TextView tvHeight, tvWeight;
    private SeekBar seekBarHeight, seekBarWeight;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_inicio_actividad);

        tvHeight = findViewById(R.id.tvHeight);
        tvWeight = findViewById(R.id.tvWeight);
        seekBarHeight = findViewById(R.id.seekBarHeight);
        seekBarWeight = findViewById(R.id.seekBarWeight);

        seekBarHeight.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int height = progress;
                String heightText = "Altura: " + height + " cm";
                tvHeight.setText(heightText);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // No es necesario realizar ninguna acción aquí
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // No es necesario realizar ninguna acción aquí
            }
        });

        seekBarWeight.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int weight = progress;
                String weightText = "Peso: " + weight + " kg";
                tvWeight.setText(weightText);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // No es necesario realizar ninguna acción aquí
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // No es necesario realizar ninguna acción aquí
            }
        });
    }
}