package com.example.pruebasproyecto;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;


public class Principal_inicio_actividad extends AppCompatActivity {

    private List<Ejercicio> ejercicios;
    private EjercicioAdapter ejercicioAdapter;
    private EditText kilosEditText;
    private EditText repeticionesEditText;
    private ImageButton addRepeticionButton;
    private ListView listView;
    private Chronometer crono;
    private boolean running;
    private ImageButton inicio;
    private ImageButton pausa;
    private ImageButton reinicio;
    private long pauseOffset;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_inicio_actividad);

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
                Log.d("ImageButton", "PULSADO");
                agregarEjercicio();
            }
        });

        crono = findViewById(R.id.cronometro);
        inicio = findViewById(R.id.inicioboton);
        pausa = findViewById(R.id.pausaboton);
        reinicio = findViewById(R.id.restartboton);

        inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!running) {
                    crono.setBase(SystemClock.elapsedRealtime() - pauseOffset);
                    crono.start();
                    running = true;
                }
            }
        });

        pausa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (running) {
                    crono.stop();
                    pauseOffset = SystemClock.elapsedRealtime() - crono.getBase();
                    running = false;
                }
            }
        });

        reinicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crono.setBase(SystemClock.elapsedRealtime());
                pauseOffset = 0;
                crono.stop();
                running = false;
            }
        });

        ejercicioAdapter.setOnEliminarClickListener(new EjercicioAdapter.OnEliminarClickListener() {
            @Override
            public void onEliminarClick(int position) {
                mostrarDialogoBorrar(position);
            }
        });
    }

    private void agregarEjercicio() {
        String kilos = kilosEditText.getText().toString();
        String repeticiones = repeticionesEditText.getText().toString();

        if (!kilos.isEmpty() && !repeticiones.isEmpty()) {
            int numero = ejercicios.size() + 1;
            int imagen = R.drawable.tu_imagen;

            Ejercicio ejercicio = new Ejercicio(numero, imagen, kilos, repeticiones);
            ejercicios.add(ejercicio);
            ejercicioAdapter.notifyDataSetChanged();

            // Restablecer campos de texto
            kilosEditText.setText("");
            repeticionesEditText.setText("");

            // Reiniciar el cronómetro
            crono.setBase(SystemClock.elapsedRealtime());
            pauseOffset = 0;
            crono.stop();
            running = false;
        } else {
            Toast.makeText(this, "Debes completar los campos Kilos y Repeticiones", Toast.LENGTH_SHORT).show();
        }
    }

    private void mostrarDialogoBorrar(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Eliminar ejercicio")
                .setMessage("¿Deseas eliminar este ejercicio?")
                .setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        eliminarEjercicio(position);
                    }
                })
                .setNegativeButton("Cancelar", null)
                .show();
    }
    private void eliminarEjercicio(int position) {
        ejercicios.remove(position);
        for (int i = position; i < ejercicios.size(); i++) {
            Ejercicio ejercicio = ejercicios.get(i);
            ejercicio.setNumero(i + 1);
        }
        ejercicioAdapter.notifyDataSetChanged();
    }

}
