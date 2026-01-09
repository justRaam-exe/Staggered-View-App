package com.raam.weebapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {
    private final Context context;
    private final List<ImageModel> data;

    public ImageAdapter(Context context) {
        this.context = context;
        DBHelper db = new DBHelper(context);
        data = db.getAllImages();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_image, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ImageModel model = data.get(position);

        int resId = context.getResources().getIdentifier(model.imageName, "drawable", context.getPackageName());

        holder.imageView.setImageResource(resId);

        holder.imageView.setOnClickListener(v -> {
           Intent intent = new Intent(context, ImageDetailActivity.class);
           intent.putExtra("imageRes", resId);
           intent.putExtra("imageTitle", model.title);
           intent.putExtra("imageDesc", model.description);
           context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        ViewHolder(View view) {
            super(view);
            imageView = view.findViewById(R.id.imageView);
        }
    }
}
