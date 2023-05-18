package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        int[] imageIds = {R.drawable.cuerpiiis, R.drawable.cuerpiiis, R.drawable.cuerpiiis, R.drawable.cuerpiiis, R.drawable.cuerpiiis, R.drawable.cuerpiiis,R.drawable.cuerpiiis, R.drawable.cuerpiiis};
        String[] imageTexts = {"Imagen 1", "Imagen 2", "Imagen 3", "Imagen 4", "Imagen 5", "Imagen 6", "Imagen 7", "Imagen 8"};
        Class<?>[] activityClasses = {MainActivity2.class, VideoActivity.class, MainActivity2.class, MainActivity2.class, MainActivity2.class, MainActivity2.class, MainActivity2.class, MainActivity2.class};

        GridView gridView = findViewById(R.id.cuadros);
        ImageAdapter adapter = new ImageAdapter(this, imageIds, imageTexts, activityClasses);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity3.this, VideoActivity.class);
                intent.putExtra("videoResourceId", R.raw.video); // Aquí debes especificar el recurso de video que quieres mostrar
                startActivity(intent);
            }
        });


    }


}
