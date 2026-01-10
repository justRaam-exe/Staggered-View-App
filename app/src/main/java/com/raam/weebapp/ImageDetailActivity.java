package com.raam.weebapp;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.app.WallpaperManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class ImageDetailActivity extends AppCompatActivity {

    int imageRes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_image_detail);

        ImageView imageView = findViewById(R.id.detailImage);
        TextView titleView = findViewById(R.id.detailTitle);
        TextView descView = findViewById(R.id.detailDesc);
        ImageButton btnBack = findViewById(R.id.btnBack);
        View btnSetWallpaper = findViewById(R.id.setWallpaperBtn);

        btnBack.setOnClickListener(v -> onBackPressed());

        imageRes = getIntent().getIntExtra("imageRes", 0);
        String title = getIntent().getStringExtra("imageTitle");
        String desc = getIntent().getStringExtra("imageDesc");

        if (imageRes != 0) {
            imageView.setImageResource(imageRes);
        }
        titleView.setText(title != null ? title : "No title");
        descView.setText(desc != null ? desc : "No description");

        btnSetWallpaper.setOnClickListener(v -> setWallpaper());
    }

    private void setWallpaper() {
        try {
            WallpaperManager wallpaperManager = WallpaperManager.getInstance(this);
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), imageRes);

            wallpaperManager.setBitmap(bitmap);

            Toast.makeText(this, "Wallpaper berhasil diterapkan", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Gagal menerapkan wallpaper", Toast.LENGTH_SHORT).show();
        }
    }
}