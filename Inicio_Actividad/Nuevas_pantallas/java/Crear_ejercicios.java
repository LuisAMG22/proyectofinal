package com.example.crearejericios;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Crear_ejercicios extends AppCompatActivity {

    private String primerValor = "Selecciona la parte:";
    private Spinner elementoSpinner;
    private EditText nombreEjercicio, descripcionEjercicio;
    private TextView nuevaParteSeleccionada, nuevoNombreSeleccionado, nuevaDescipcionEjer;
    private ImageView imgVolver;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_ejercicios);

        elementoSpinner = findViewById(R.id.elementosSpinner);
        nombreEjercicio = findViewById(R.id.nombreEjercicio);
        nuevaParteSeleccionada = findViewById(R.id.nuevaParteSeleccionada);
        nuevoNombreSeleccionado = findViewById(R.id.nuevoNombreSeleccionado);
        descripcionEjercicio = findViewById(R.id.descripcionEjercicio);
        nuevaDescipcionEjer = findViewById(R.id.nuevaDescripcionSeleccionado);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.partes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        elementoSpinner.setAdapter(adapter);

        imgVolver = findViewById(R.id.imageViewBack);
        imgVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Crear_ejercicios.this);
                builder.setTitle("Confirmar");
                builder.setMessage("¿Desea volver sin guardar los cambios?");
                builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Acciones al presionar "Sí" (volver atrás sin guardar)
                        finish(); // Otra opción: onBackPressed();
                    }
                });
                builder.setNegativeButton("Cancelar", null); // No se realiza ninguna acción al presionar "Cancelar"
                builder.show();
            }
        });

        Button botonConfirmar = findViewById(R.id.botonConfirmar);
        botonConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String parteSeleccionada = elementoSpinner.getSelectedItem().toString();
                String nombreEjer = nombreEjercicio.getText().toString();
                String descripEjer = descripcionEjercicio.getText().toString();
                // Verificar si el campo del nombre del ejercicio está lleno y el valor seleccionado no es el primer elemento del array
                if (nombreEjer.isEmpty() || parteSeleccionada.equals(primerValor) || descripEjer.isEmpty() ) {
                    // Mostrar mensaje de error si el campo del nombre del ejercicio está vacío o el valor seleccionado es el primer elemento del array
                    Toast.makeText(Crear_ejercicios.this, "No es posible confirmar. Por favor, complete el campo del nombre del ejercicio y seleccione una opción válida.", Toast.LENGTH_SHORT).show();
                } else {
                    // Actualizar la parte seleccionada y el nombre del ejercicio
                    nuevaParteSeleccionada.setText(parteSeleccionada);
                    nuevoNombreSeleccionado.setText(nombreEjer);
                    nuevaDescipcionEjer.setText(descripEjer);

                    // Mostrar mensaje de éxito al guardar
                    Toast.makeText(Crear_ejercicios.this, "Se ha guardado el ejercicio correctamente", Toast.LENGTH_SHORT).show();
                }

            }
        });

        Button botonBorrar = findViewById(R.id.botonBorrar);
        botonBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Crear_ejercicios.this);
                builder.setTitle("Confirmar");
                builder.setMessage("¿Desea borrar todos los campos?");
                builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Acciones al presionar "Sí" (borrar todos los campos)
                        Spinner spinnerPart = findViewById(R.id.elementosSpinner);
                        EditText editTextExerciseName = findViewById(R.id.nombreEjercicio);
                        LinearLayout linearLayout = findViewById(R.id.ejerciciosnuevos);


                        spinnerPart.setSelection(0); // Restablecer la selección del spinner al primer valor
                        editTextExerciseName.setText(""); // Borrar el texto del EditText
                        descripcionEjercicio.setText("");
                        nuevaParteSeleccionada.setText(""); // Borrar el texto de la parte seleccionada
                        nuevoNombreSeleccionado.setText(""); // Borrar el texto del nombre del ejercicio
                        nuevaDescipcionEjer.setText("");

                    }
                });
                builder.setNegativeButton("No", null); // No se realiza ninguna acción al presionar "No"
                builder.show();
            }
        });

        Button botonGuardar = findViewById(R.id.botonGuardar);
        botonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Crear_ejercicios.this);
                builder.setTitle("Guardar");
                builder.setMessage("¿Desea guardar el ejercicio nuevo?");
                builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if(nuevaParteSeleccionada.getText().length()>0 && nuevoNombreSeleccionado.getText().length()>0 && nuevaDescipcionEjer.getText().length() > 0) {



                            //Volver a la pantalla anterior y guardar el ejercicio en la BBDD





                        }else{
                            Toast.makeText(Crear_ejercicios.this, "Debe crear un ejercicio antes de guardar", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("No", null); // No se realiza ninguna acción al presionar "No"
                builder.show();
            }
        });
    }
}


