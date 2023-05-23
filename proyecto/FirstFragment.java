package com.example.proyecto;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class FirstFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private TextView titleTextView;
    private EditText searchEditText;
    private ListView exerciseListView;

    public FirstFragment() {
        // Required empty public constructor
    }

    public static FirstFragment newInstance(String param1, String param2) {
        FirstFragment fragment = new FirstFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);

        titleTextView = view.findViewById(R.id.titleTextView);
        searchEditText = view.findViewById(R.id.searchEditText);
        exerciseListView = view.findViewById(R.id.exerciseListView);
        ImageView searchIcon = view.findViewById(R.id.searchIcon);

        // Configurar el ListView con datos de ejemplo
        String[] exerciseNames = {"Ejercicio 1", "Ejercicio 2", "Ejercicio 3", "Ejercicio 4", "Ejercicio 5", "Ejercicio 6", "Ejercicio 7"};
        int[] exerciseImages = {R.drawable.cuerpo_brazos, R.drawable.cuerpo_brazos_pecho, R.drawable.cuerpo_hombros, R.drawable.cuerpo_hombros, R.drawable.cuerpo_hombros, R.drawable.cuerpo_hombros, R.drawable.cuerpo_hombros};

        final ExerciseListAdapter adapter = new ExerciseListAdapter(getActivity(), exerciseNames, exerciseImages);
        exerciseListView.setAdapter(adapter);

        // Configurar el evento de clic en el elemento del ListView
        exerciseListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Obtener el ejercicio seleccionado
                String exerciseName = exerciseNames[position];

                // Abrir una nueva actividad o diálogo para mostrar la información del ejercicio
                // según tu requerimiento
            }
        });

        searchIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (searchEditText.getVisibility() == View.VISIBLE) {
                    searchEditText.setVisibility(View.GONE); // Oculta el EditText de búsqueda si ya está visible
                } else {
                    searchEditText.setVisibility(View.VISIBLE); // Muestra el EditText de búsqueda si está oculto
                }
            }
        });

        // Agregar el evento de texto cambiado para el EditText de búsqueda
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // No se requiere implementación
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Filtrar los elementos del ListView según la consulta de búsqueda
                adapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {
                // No se requiere implementación
            }
        });


        return view;
    }
}
