package com.example.proyecto;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class ExerciseListAdapter extends ArrayAdapter<String> {

    private Context context;
    private String[] exerciseNames;
    private int[] exerciseImages;
    private ArrayList<String> filteredExercises; // Lista filtrada para almacenar los ejercicios coincidentes

    public ExerciseListAdapter(Context context, String[] exerciseNames, int[] exerciseImages) {
        super(context, R.layout.ejercicios_lista_item, exerciseNames);
        this.context = context;
        this.exerciseNames = exerciseNames;
        this.exerciseImages = exerciseImages;
        this.filteredExercises = new ArrayList<>(Arrays.asList(exerciseNames)); // Inicializar la lista filtrada con todos los ejercicios
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.ejercicios_lista_item, parent, false);

        ImageView exerciseImageView = view.findViewById(R.id.exerciseImageView);
        TextView exerciseTitleTextView = view.findViewById(R.id.exerciseTitleTextView);

        // Establecer los datos del ejercicio en el diseño del elemento de la lista
        exerciseImageView.setImageResource(exerciseImages[position]);
        exerciseTitleTextView.setText(exerciseNames[position]);

        return view;
    }
    public void filter(String searchText) {
        filteredExercises.clear(); // Limpiar la lista filtrada actual

        if (searchText.isEmpty()) {
            filteredExercises.addAll(Arrays.asList(exerciseNames)); // Agregar todos los ejercicios si no hay texto de búsqueda
        } else {
            // Recorrer todos los ejercicios y agregar aquellos que coincidan con el texto de búsqueda
            for (String exercise : exerciseNames) {
                if (exercise.toLowerCase().contains(searchText.toLowerCase())) {
                    filteredExercises.add(exercise);
                }
            }
        }

        notifyDataSetChanged(); // Notificar al adaptador sobre los cambios en la lista filtrada
    }

    @Override
    public int getCount() {
        return filteredExercises.size(); // Devolver el tamaño de la lista filtrada
    }

    @Override
    public String getItem(int position) {
        return filteredExercises.get(position); // Devolver el ejercicio de la posición en la lista filtrada
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                List<String> filteredList = new ArrayList<>();

                if (constraint == null || constraint.length() == 0) {
                    // No hay texto de búsqueda, mostrar todos los ejercicios
                    filteredList.addAll(Arrays.asList(exerciseNames));
                } else {
                    String filterPattern = constraint.toString().toLowerCase(Locale.getDefault()).trim();
                    // Filtrar los ejercicios según el texto de búsqueda
                    for (String exercise : exerciseNames) {
                        if (exercise.toLowerCase(Locale.getDefault()).contains(filterPattern)) {
                            filteredList.add(exercise);
                        }
                    }
                }

                filterResults.values = filteredList;
                filterResults.count = filteredList.size();
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filteredExercises.clear();
                filteredExercises.addAll((List<String>) results.values);
                notifyDataSetChanged();
            }
        };
    }
}

