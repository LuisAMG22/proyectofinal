package com.example.proyecto;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ExerciseListAdapter extends ArrayAdapter<String> {

    private Context context;
    private String[] exerciseNames;
    private String[] exerciseSubNames;
    private int[] exerciseImages;

    private String[] exerciseNames2;
    private String[] exerciseSubNames2;

    public ExerciseListAdapter(Context context, String[] exerciseNames, int[] exerciseImages, String[] exerciseSubNames) {
        super(context, R.layout.ejercicios_lista_item, exerciseNames);
        this.context = context;
        this.exerciseNames = exerciseNames;
        this.exerciseImages = exerciseImages;
        this.exerciseSubNames = exerciseSubNames;
    }

    public ExerciseListAdapter(Context context, String[] exerciseNames2, String[] exerciseSubNames2) {
        super(context, R.layout.ejercicios_lista_personalizar, exerciseNames2);
        this.context = context;
        this.exerciseNames2 = exerciseNames2;
        this.exerciseSubNames2 = exerciseSubNames2;
    }


    public void updateData(String[] exerciseNames, String[] exerciseSubNames) {
        this.exerciseNames2 = exerciseNames;
        this.exerciseSubNames2 = exerciseSubNames;
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);

        if (exerciseImages != null && exerciseNames != null && exerciseSubNames != null) {
            // Caso 1: Mostrar datos del primer ListView
            View view = inflater.inflate(R.layout.ejercicios_lista_item, parent, false);
            ImageView exerciseImageView = view.findViewById(R.id.exerciseImageView);
            TextView exerciseTitleTextView = view.findViewById(R.id.exerciseTitleTextView);
            TextView exerciseSubtitleTextView = view.findViewById(R.id.exerciseSubtitleTextView);

            exerciseImageView.setImageResource(exerciseImages[position]);
            exerciseTitleTextView.setText(exerciseNames[position]);
            exerciseSubtitleTextView.setText(exerciseSubNames[position]);

            return view;
        } else if (exerciseNames2 != null && exerciseSubNames2 != null) {
            // Caso 2: Mostrar datos del segundo ListView
            View view = inflater.inflate(R.layout.ejercicios_lista_personalizar, parent, false);
            TextView exerciseTitleTextView2 = view.findViewById(R.id.exerciseTitleTextView1);
            TextView exerciseSubtitleTextView2 = view.findViewById(R.id.exerciseSubtitleTextView2);

            exerciseTitleTextView2.setText(exerciseNames2[position]);
            exerciseSubtitleTextView2.setText(exerciseSubNames2[position]);

            return view;
        }

        return convertView;
    }

}
