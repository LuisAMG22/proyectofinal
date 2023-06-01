package com.example.pruebasreglaseekbar;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    private SeekBar seekBar;
    private TextView tvWeight;
    private SeekBar seekBar2;
    private TextView textView;
    private TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekBar = findViewById(R.id.seekBar);
        tvWeight = findViewById(R.id.tvWeight);
        seekBar2 = findViewById(R.id.seekBar2);
        textView = findViewById(R.id.textView4);
        textView2 = findViewById(R.id.textView2);

        seekBar.setMax(140 - 30);
        seekBar2.setMax(200 - 100);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int actualProgress = progress + 30;
                updateWeightText(actualProgress);
                calculateAndDisplayIMC();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // No implementation required
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // No implementation required
            }
        });

        seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int actualProgress = progress + 100;
                updateHeightText(actualProgress);
                calculateAndDisplayIMC();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // No implementation required
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // No implementation required
            }
        });
    }

    private void updateWeightText(int progress) {
        String weightText = "Peso: " + progress + " kg";
        tvWeight.setText(weightText);
    }

    private void updateHeightText(int progress) {
        String heightText = "Altura: " + progress + " cm";
        textView.setText(heightText);
    }

    private void calculateAndDisplayIMC() {
        int weight = seekBar.getProgress() + 30;
        int height = seekBar2.getProgress() + 100;
        double heightInMeters = height / 100.0;
        double bmi = weight / (heightInMeters * heightInMeters);
        double roundedBMI = Math.round(bmi * 10.0) / 10.0;

        String imcText = "IMC: " + roundedBMI;

        // Set the IMC text
        textView2.setText(imcText);

        // Set text color based on thresholds
        if (roundedBMI >= 18.5 && roundedBMI <= 24.9) {
            textView2.setTextColor(ContextCompat.getColor(this, R.color.green));
        } else if (roundedBMI < 18.5 || roundedBMI > 24.9 && roundedBMI < 30) {
            textView2.setTextColor(ContextCompat.getColor(this, R.color.yellow));
        } else if (roundedBMI > 30) {
            textView2.setTextColor(ContextCompat.getColor(this, R.color.red));
        }
    }

}

