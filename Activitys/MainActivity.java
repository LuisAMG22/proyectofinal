package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Path;
import android.graphics.Region;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
        Button btnCambiarPantalla = findViewById(R.id.button);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtiene la posición del clic
                int x = (int) v.getX();
                int y = (int) v.getY();

                System.out.println(x+""+y);

                // Verifica si la posición del clic se encuentra dentro de un área definida
                if (isInArea(x, y,R.array.body_areas)) {
                    // Muestra la información correspondiente a esa área
                    Toast.makeText(MainActivity.this, "Clic en la zona sensible", Toast.LENGTH_SHORT).show();
                }
                else{
                    resetCoordinates();
                }
            }
        });

        btnCambiarPantalla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity3.class);
                startActivity(intent);
            }
        });

    }

    private void resetCoordinates() {
        int x1,x2,x3,x4,y1,y2,y3,y4= 0;
    }


    private boolean isInArea(int x, int y, int areasArrayId) {
        String[] areas = getResources().getStringArray(areasArrayId);
        for (String area : areas) {
            String[] coords = area.split(",");
            int x1 = Integer.parseInt(coords[0]);
            int y1 = Integer.parseInt(coords[1]);
            int x2 = Integer.parseInt(coords[2]);
            int y2 = Integer.parseInt(coords[3]);
            int x3 = Integer.parseInt(coords[4]);
            int y3 = Integer.parseInt(coords[5]);
            int x4 = Integer.parseInt(coords[6]);
            int y4 = Integer.parseInt(coords[7]);

            System.out.println(x1+" "+x2+" "+x3+" "+x4);
            Path path = new Path();
            path.moveTo(x1, y1);
            path.lineTo(x2, y2);
            path.lineTo(x3, y3);
            path.lineTo(x4, y4);
            path.close();

            Region region = new Region();
            Region clip = new Region(0, 0, imageView.getWidth(), imageView.getHeight());
            region.setPath(path, clip);

            return region.contains(x, y);
        }
        return false;
    }
}