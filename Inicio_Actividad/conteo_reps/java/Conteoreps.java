package com.example.proyectopruebas40;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Conteoreps extends AppCompatActivity {
    private EjercicioAdapter adapter;
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
    private long pausaTimer;

    private Button guardarButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conteoreps);



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
                    crono.setBase(SystemClock.elapsedRealtime() - pausaTimer);
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
                    pausaTimer = SystemClock.elapsedRealtime() - crono.getBase();
                    running = false;
                }
            }
        });

        reinicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crono.setBase(SystemClock.elapsedRealtime());
                pausaTimer = 0;
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

        guardarButton = findViewById(R.id.button);
        guardarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarDialogoGuardar();
            }
        });

        ImageView imageViewVolver = findViewById(R.id.imageViewVolver);
        imageViewVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarDialogo();
            }
        });

    }

    private void agregarEjercicio() {
        String kilostring = kilosEditText.getText().toString();
        String repeticionestring = repeticionesEditText.getText().toString();
        int kilos = 0;
        int repeticiones = 0;

        if (!kilostring.isEmpty() && !repeticionestring.isEmpty()) {
            kilos = Integer.parseInt(kilostring);
            repeticiones = Integer.parseInt(repeticionestring);
        }

        if (!kilostring.isEmpty() && !repeticionestring.isEmpty()) {
            int numero = ejercicios.size() + 1;
            int imagen = R.drawable.tu_imagen;

            Ejercicio ejercicio = new Ejercicio(numero, imagen, kilos, repeticiones);
            ejercicio.setValorkg(kilos);
            ejercicio.setValorreps(repeticiones);
            ejercicio.setTiempocrono(SystemClock.elapsedRealtime() - crono.getBase());

            ejercicios.add(ejercicio);
            ejercicioAdapter.notifyDataSetChanged();

            // Restablecer campos de texto
            kilosEditText.setText("");
            repeticionesEditText.setText("");

            // Reiniciar el cronómetro
            crono.setBase(SystemClock.elapsedRealtime());
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

    private void mostrarDialogoGuardar() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("¿Desea terminar el ejercicio?")
                .setMessage("Guardar los valores y tiempo del ejercicio.")
                .setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        guardarEjercicio();
                    }
                })
                .setNegativeButton("Cancelar", null)
                .show();
    }

    private void mostrarDialogo() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmación");
        builder.setMessage("¿Quieres salir? Se perderá el progreso no guardado.");
        builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // SE VUELVE A LA PANTALLA ANTERIOR DEL LISTVIEW

            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //
                // NO SE HACE NADA
            }
        });
        builder.show();
    }


    private void guardarEjercicio() {
        // Sumar los valores de los elementos en el ListView
        int sumaValores = 0;
        int kgtotales=0;
        int reptotales=0;
        for (Ejercicio ejercicio : ejercicios) {
            kgtotales += ejercicio.getKilos();
            reptotales += ejercicio.getRepeticiones();
        }


        int tiempoTranscurrido = (int) ((SystemClock.elapsedRealtime() - crono.getBase()) / 1000);

       int series = ejercicios.size();

        Log.d("TAG", "El valor es num series: " + series);
        Log.d("TAG", "El mensaje es kgtotales: " + kgtotales);
        Log.d("TAG", "El mensaje es repstotales: " + reptotales);
        Log.d("TAG", "El mensaje es tiempocrono: " + tiempoTranscurrido);

        Toast.makeText(this, "Ejercicio guardado", Toast.LENGTH_SHORT).show();

        // Restablecer los campos y el cronómetro
        kilosEditText.setText("");
        repeticionesEditText.setText("");
        crono.setBase(SystemClock.elapsedRealtime());
        pausaTimer = 0;
        crono.stop();
        running = false;
        ejercicios.clear();
        ejercicioAdapter.notifyDataSetChanged();
    }

}