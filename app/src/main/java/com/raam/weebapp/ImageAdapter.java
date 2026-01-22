package com.raam.weebapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {
    private final Context context;
    private final List<ImageModel> data;

    public ImageAdapter(Context context, List<ImageModel> data) {
        this.context = context;
        this.data = data;
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

        Glide.with(context)
                .load(model.image)
                .thumbnail(0.25f)
                .centerCrop()
                .into(holder.imageView);

        holder.imageView.setOnClickListener(v -> {
           Intent intent = new Intent(context, ImageDetailActivity.class);
           intent.putExtra("image", model.image);
           intent.putExtra("title", model.title);
           intent.putExtra("desc", model.description);
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
