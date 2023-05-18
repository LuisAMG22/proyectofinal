package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageAdapter extends BaseAdapter {
    private Context context;
    private int[] imageIds;
    private String[] imageTexts;
    private Class<?>[] activityClasses;

    public ImageAdapter(Context context, int[] imageIds, String[] imageTexts, Class<?>[] activityClasses) {
        this.context = context;
        this.imageIds = imageIds;
        this.imageTexts = imageTexts;
        this.activityClasses = activityClasses;
    }

    @Override
    public int getCount() {  //devuelve el numero de elemetos que hay en la lista de imagenes
        return imageIds.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = null;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            view = inflater.inflate(R.layout.grid_item, null);
        }else if (imageTexts[position].equals("Imagen 2")) {
                Intent intent = new Intent(context, VideoActivity.class);
                intent.putExtra("videoResourceId", R.raw.video); // Reemplaza "nombre_del_video" con el nombre real de tu archivo de video
                context.startActivity(intent);
        } else {
            view = convertView;
        }

        ImageView imageView = view.findViewById(R.id.imageView);
        TextView textView = view.findViewById(R.id.textView);

        imageView.setImageResource(imageIds[position]);
        textView.setText(imageTexts[position]);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, activityClasses[position]);
                context.startActivity(intent);
            }
        });

        return view;
    }
}


