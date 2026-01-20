package com.raam.weebapp;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.app.WallpaperManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.Toast;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;

public class ImageDetailActivity extends AppCompatActivity {

    ImageView imageView;
    TextView titleView, descView;
    String imageUrl;
//    int imageRes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);

        imageView = findViewById(R.id.detailImage);
        titleView = findViewById(R.id.detailTitle);
        descView = findViewById(R.id.detailDesc);
        ImageButton btnBack = findViewById(R.id.btnBack);
        View btnSetWallpaper = findViewById(R.id.setWallpaperBtn);

        btnBack.setOnClickListener(v -> onBackPressed());

        imageUrl = getIntent().getStringExtra("image");
        titleView.setText(getIntent().getStringExtra("title"));
        descView.setText(getIntent().getStringExtra("desc"));

        Glide.with(this).load(imageUrl).into(imageView);

        findViewById(R.id.setWallpaperBtn).setOnClickListener(v -> setWallpaper());

//        imageRes = getIntent().getIntExtra("imageRes", 0);
//        String title = getIntent().getStringExtra("imageTitle");
//        String desc = getIntent().getStringExtra("imageDesc");
//
//        if (imageRes != 0) {
//            imageView.setImageResource(imageRes);
//        }
//        titleView.setText(title != null ? title : "No title");
//        descView.setText(desc != null ? desc : "No description");
//
//        btnSetWallpaper.setOnClickListener(v -> setWallpaper());
    }

    private void setWallpaper() {
        Glide.with(this).asBitmap().load(imageUrl).into(new CustomTarget<Bitmap>() {
           @Override
            public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
               try {
                   WallpaperManager.getInstance(getApplicationContext()).setBitmap(resource);
                   Toast.makeText(ImageDetailActivity.this, "Wallpaper berhasil diterapkan", Toast.LENGTH_SHORT).show();
               } catch (Exception e) {
                   Toast.makeText(ImageDetailActivity.this, "Gagal Set Wallpaper", Toast.LENGTH_SHORT).show();
               }
           }

           @Override
            public void onLoadCleared(Drawable placeholder) {}
        });
//        try {
//            WallpaperManager wallpaperManager = WallpaperManager.getInstance(this);
//            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), imageRes);
//
//            wallpaperManager.setBitmap(bitmap);
//
//            Toast.makeText(this, "Wallpaper berhasil diterapkan", Toast.LENGTH_SHORT).show();
//        } catch (IOException e) {
//            e.printStackTrace();
//            Toast.makeText(this, "Gagal menerapkan wallpaper", Toast.LENGTH_SHORT).show();
//        }
    }
}