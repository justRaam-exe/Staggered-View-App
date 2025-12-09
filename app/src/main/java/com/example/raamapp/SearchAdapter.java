package com.example.raamapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONObject;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    private final Context context;
    private List<JSONObject> dataList;

    public SearchAdapter(Context context, List<JSONObject> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    public void updateData(List<JSONObject> newList) {
        this.dataList = newList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_image, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.ViewHolder holder, int position) {
        try {
            JSONObject obj = dataList.get(position);
            String imageName = obj.getString("image");
            String title = obj.getString("title");
            String desc = obj.getString("description");

            int resId = context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());
            holder.imageView.setImageResource(resId);

            holder.imageView.setOnClickListener(v -> {
                Intent intent = new Intent(context, ImageDetailActivity.class);
                intent.putExtra("imageRes", resId);
                intent.putExtra("imageTitle", title);
                intent.putExtra("imageDesc", desc);
                context.startActivity(intent);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
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
