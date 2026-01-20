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

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    private final Context context;
    private List<ImageModel> dataList;

    public SearchAdapter(Context context, List<ImageModel> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

//    public void updateData(List<ImageModel> newList) {
//        this.dataList = newList;
//        notifyDataSetChanged();
//    }

    @NonNull
    @Override
    public SearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_image, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.ViewHolder holder, int position) {
        ImageModel model = dataList.get(position);

        Glide.with(context)
                .load(model.image)
                .into(holder.imageView);

//        int resId = context.getResources().getIdentifier(model.imageName, "drawable", context.getPackageName());
//
//        holder.imageView.setImageResource(resId);

        holder.imageView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ImageDetailActivity.class);
            intent.putExtra("title", model.title);
            intent.putExtra("desc", model.description);
            intent.putExtra("image", model.image);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
