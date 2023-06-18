package com.example.proyecto;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class FirstFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private ExerciseListAdapter adapter;
    private ExerciseListAdapter adapter2;

    private String mParam1;
    private String mParam2;
    private TextView titleTextView;
    private ListView exerciseListView;
    private ListView exerciseListView2;
    private FloatingActionButton floatingActionButton;

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
        exerciseListView = view.findViewById(R.id.exerciseListView);
        exerciseListView2 = view.findViewById(R.id.exerciseListView2);
        floatingActionButton = view.findViewById(R.id.floatingActionButton);

        // Configurar el ListView con datos SEGUN TU PLAN
        String[] exerciseNames = {"Ejercicio 1", "Ejercicio 2", "Ejercicio 3", "Ejercicio 4", "Ejercicio 5", "Ejercicio 6"};
        int[] exerciseImages = {R.drawable.cuerpo_brazos, R.drawable.cuerpo_brazos_pecho, R.drawable.cuerpo_hombros, R.drawable.cuerpo_hombros, R.drawable.cuerpo_hombros, R.drawable.cuerpo_hombros};
        String[] exerciseSubNames = {"sub1", "sub2", "sub3", "sub4", "sub5", "sub6"};

        // Configurar el adaptador del primer ListView
        final ExerciseListAdapter adapter = new ExerciseListAdapter(getActivity(), exerciseNames, exerciseImages, exerciseSubNames);
        exerciseListView.setAdapter(adapter);

        // Configurar el adaptador del segundo ListView
        String[] exerciseNames2 = {"Ejercicio A", "Ejercicio B", "Ejercicio C"};
        String[] exerciseSubNames2 = {"Subtítulo A", "Subtítulo B", "Subtítulo C"};
        final ExerciseListAdapter adapter2 = new ExerciseListAdapter(getActivity(), exerciseNames2, exerciseSubNames2);
        exerciseListView2.setAdapter(adapter2);

        // Configurar el evento de clic en el elemento del ListView
        exerciseListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Obtener el ejercicio seleccionado
                String exerciseName = exerciseNames[position];
                //AQUI VA LO DE MIKEL PARA REALIZAR EL EJERCICIO el string es el ejercicio
                // que yo pulse hay que usarlo
                showBottomDialog();
            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //AQUI GUARDAS EL NUMERO DE EJERCICIOS TOTALES QUE HA HECHO EL USUARIO
            }
        });

        return view;
    }


    private void showBottomDialog() {

        final Dialog dialog = new Dialog(this.getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.bottomsheetlayout);

        ImageView cancelButton = dialog.findViewById(R.id.cancelButton);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);

    }

}
