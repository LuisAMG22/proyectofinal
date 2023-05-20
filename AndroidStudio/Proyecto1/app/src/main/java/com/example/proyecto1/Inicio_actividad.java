package com.example.proyecto1;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import java.util.ArrayList;
import java.util.List;
public class Inicio_actividad extends AppCompatActivity {

    private List<Ejercicio> ejercicios;
    private EjercicioAdapter ejercicioAdapter;
    private EditText kilosEditText;
    private EditText repeticionesEditText;
    private ImageButton addRepeticionButton;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_actividad);

        ejercicios = new ArrayList<>();
        ejercicioAdapter = new EjercicioAdapter(this, ejercicios);
        listView = findViewById(R.id.listView);
        listView.setAdapter(ejercicioAdapter);

        kilosEditText = findViewById(R.id.Kilos);
        repeticionesEditText = findViewById(R.id.Repeticion);
        addRepeticionButton = findViewById(R.id.addRepeticionButton);
        addRepeticionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agregarEjercicio();
            }
        });
    }

    private void agregarEjercicio() {
        String kilos = kilosEditText.getText().toString();
        String repeticiones = repeticionesEditText.getText().toString();

        if (!kilos.isEmpty() && !repeticiones.isEmpty()) {
            int numero = ejercicios.size() + 1;
            int imagen = R.drawable.tu_imagen; // Reemplaza "tu_imagen" con el identificador de la imagen que deseas mostrar

            Ejercicio ejercicio = new Ejercicio(numero, imagen, kilos, repeticiones);
            ejercicios.add(ejercicio);
            ejercicioAdapter.notifyDataSetChanged();

            // Restablecer campos de texto
            kilosEditText.setText("");
            repeticionesEditText.setText("");
        } else {
            Toast.makeText(this, "Debes completar los campos Kilos y Repeticiones", Toast.LENGTH_SHORT).show();
        }
    }
}





