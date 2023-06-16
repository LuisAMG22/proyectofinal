package com.example.settings;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.util.DisplayMetrics;
import java.util.Locale;

public class Ajustes extends AppCompatActivity {

    private SwitchCompat activarVibracionSwitch;
    private boolean vibracionActivada = false;
    private Vibrator vibrator;

    private TextView nombreUsuarioTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LanguageManager lang = new LanguageManager(this);
        String selectedLanguage = lang.getLang();
        lang.updateResource(selectedLanguage);

        setContentView(R.layout.activity_ajustes);

        activarVibracionSwitch = findViewById(R.id.activarVibracion);
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        activarVibracionSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                vibracionActivada = isChecked;

                if (vibracionActivada) {
                    activarVibracion();
                } else {
                    detenerVibracion();
                }
            }
        });

        nombreUsuarioTextView = findViewById(R.id.nombreUsuario);

    }

    // Método para activar la vibración
    private void activarVibracion() {
        if (vibrator.hasVibrator()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrator.vibrate(VibrationEffect.createOneShot(1000, VibrationEffect.DEFAULT_AMPLITUDE));
            } else {
                vibrator.vibrate(1000);
            }
        }
    }

    // Método para detener la vibración
    private void detenerVibracion() {
        vibrator.cancel();
    }

    public void mostrarVentanaSeguridadPrivacidad(View view) {
        // Crear un Dialog personalizado
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_seguridad_privacidad);

        // Obtener referencias a los elementos del diálogo
        TextView textoSeguridadPrivacidad = dialog.findViewById(R.id.textoSeguridadPrivacidad);
        Button cerrarButton = dialog.findViewById(R.id.cerrarButton);

        cerrarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        // Mostrar el diálogo
        dialog.show();
    }

    public void mostrarSeleccionIdioma(View view) {
        // Crear un Dialog personalizado
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.cambio_idiomas);
        LanguageManager lang = new LanguageManager(this);
        // Obtener referencias a los elementos del diálogo
        RadioGroup idiomaRadioGroup = dialog.findViewById(R.id.idiomaRadioGroup);
        Button seleccionarButton = dialog.findViewById(R.id.seleccionarButton);

        idiomaRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // Verifica qué RadioButton está seleccionado
                if (checkedId == R.id.espanolRadioButton) {

                    lang.updateResource("es");
                    recreate();

                } else if (checkedId == R.id.inglesRadioButton) {
                     lang.updateResource("en");
                     recreate();
                }
            }
        });

        // Configurar el botón de seleccionar para obtener el idioma seleccionado
        seleccionarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });

        // Mostrar el diálogo
        dialog.show();
    }



    public void mostrarDialogSobreAplicacion(View view) {
        // Crear un Dialog personalizado
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.sobre_la_aplicacion);

        // Obtener referencias a los elementos del diálogo
        TextView textoSobreAplicacion = dialog.findViewById(R.id.textoSobreAplicacion);
        Button cerrarButton = dialog.findViewById(R.id.cerrarButton);

        // Establecer el texto del diálogo
       // String texto = getResources().getString(R.string.texto_sobre_la_aplicacion);
        //textoSobreAplicacion.setText(texto);

        // Configurar el botón de cerrar para cerrar el diálogo
        cerrarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        // Mostrar el diálogo
        dialog.show();
    }



    public void mostrarDialogoEditarPerfil(View view) {
        // Crear un Dialog personalizado
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_editar_perfil);

        // Obtener referencia al TextView del nombre de usuario en el diálogo
        final TextView nombreUsuarioDialogTextView = dialog.findViewById(R.id.nombreUsuario);

        // Obtener el valor actual del nombre de usuario
        String nombreUsuarioActual = nombreUsuarioTextView.getText().toString();

        // Configurar el TextView del nombre de usuario en el diálogo con el valor actual
        nombreUsuarioDialogTextView.setText(nombreUsuarioActual);

        // Configurar el botón "Cerrar" para cerrar el diálogo sin guardar cambios
        Button botonCerrar = dialog.findViewById(R.id.botonCerrar);
        botonCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        // Configurar el botón "Guardar" para guardar los cambios
        Button botonGuardar = dialog.findViewById(R.id.botonGuardar);
        botonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener el nuevo valor del nombre de usuario
                EditText nuevoNombreUsuarioEditText = dialog.findViewById(R.id.nuevoNombreUsuario);
                String nuevoNombreUsuario = nuevoNombreUsuarioEditText.getText().toString();

                // Actualizar el TextView con el nuevo nombre de usuario
                nombreUsuarioTextView.setText(nuevoNombreUsuario);

                // Mostrar un Toast indicando que los cambios se han guardado correctamente
                Toast.makeText(Ajustes.this, "Cambios guardados", Toast.LENGTH_SHORT).show();

                // Cerrar el diálogo
                dialog.dismiss();
            }
        });

        // Mostrar el diálogo
        dialog.show();
    }

    public void cerrarDialogo(View view) {
        // Cerrar el diálogo sin guardar cambios
        Dialog dialog = (Dialog) view.getParent().getParent();
        dialog.dismiss();
    }

    public void guardarCambios(View view) {
        // Obtener el nuevo valor del nombre de usuario
        Dialog dialog = (Dialog) view.getParent().getParent().getParent();
        EditText nuevoNombreUsuarioEditText = dialog.findViewById(R.id.nuevoNombreUsuario);
        String nuevoNombreUsuario = nuevoNombreUsuarioEditText.getText().toString();

        // Actualizar el TextView con el nuevo nombre de usuario
        nombreUsuarioTextView.setText(nuevoNombreUsuario);

        // Mostrar un Toast indicando que los cambios se han guardado correctamente
        Toast.makeText(this, "Cambios guardados", Toast.LENGTH_SHORT).show();

        // Cerrar el diálogo
        dialog.dismiss();
    }




}

