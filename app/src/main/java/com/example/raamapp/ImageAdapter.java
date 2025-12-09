package com.example.raamapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {
    private final Context context;
    private JSONArray imageData;

    public ImageAdapter(Context context) {
        this.context = context;
        loadJsonData();
    }

    // Fungsi untuk memuat data JSON dari assets/image_data.json
    private void loadJsonData() {
        try {
            InputStream is = context.getAssets().open("image_data.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String jsonString = new String(buffer, StandardCharsets.UTF_8);
            imageData = new JSONArray(jsonString);
        } catch (Exception e) {
            e.printStackTrace();
            imageData = new JSONArray();
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_image, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        try {
            JSONObject obj = imageData.getJSONObject(position);

            String imageName = obj.getString("image");
            int resId = context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());

            holder.imageView.setImageResource(resId);

            holder.imageView.setOnClickListener(v -> {
                try {
                    String title = obj.getString("title");
                    String desc = obj.getString("description");

                    Intent intent = new Intent(context, ImageDetailActivity.class);
                    intent.putExtra("imageRes", resId);
                    intent.putExtra("imageTitle", title);
                    intent.putExtra("imageDesc", desc);
                    context.startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return imageData.length();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
