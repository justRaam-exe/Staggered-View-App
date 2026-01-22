package com.raam.weebapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.app.WallpaperManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.Toast;
import android.view.View;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;

import java.io.FileOutputStream;
import java.io.File;
import android.net.Uri;

public class ImageDetailActivity extends AppCompatActivity {

    ImageView imageView;
    TextView titleView, descView;
    String imageUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);

        imageView = findViewById(R.id.detailImage);
        titleView = findViewById(R.id.detailTitle);
        descView = findViewById(R.id.detailDesc);
        ImageButton btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(v -> onBackPressed());

        // Ambil data dari Intent
        imageUrl = getIntent().getStringExtra("image");
        String title = getIntent().getStringExtra("title");
        String desc = getIntent().getStringExtra("desc"); // Pastikan pakai "desc" sesuai Adapter

        // Set ke View
        titleView.setText(title);
        descView.setText(desc);

        // Cek di Logcat apakah URL-nya muncul atau null
        Log.d("WALLPAPER_DEBUG", "URL Gambar: " + imageUrl);

        Glide.with(this).load(imageUrl).into(imageView);

        findViewById(R.id.setWallpaperBtn).setOnClickListener(v -> {
            if (imageUrl != null && !imageUrl.isEmpty()) {
                showWallpaperDialog();
            } else {
                Toast.makeText(this, "URL Gambar tidak ditemukan!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showWallpaperDialog() {
        String[] options = {
                "Home Screen",
                "Lock Screen",
                "Home & Lock Screen"
        };

        new androidx.appcompat.app.AlertDialog.Builder(this)
                .setTitle("Set Wallpaper")
                .setItems(options, (dialog, which) -> {
                    switch (which) {
                        case 0:
                            applyWallpaper(WallpaperManager.FLAG_SYSTEM);
                            break;
                        case 1:
                            applyWallpaper(WallpaperManager.FLAG_LOCK);
                            break;
                        case 2:
                            applyWallpaper(WallpaperManager.FLAG_SYSTEM | WallpaperManager.FLAG_LOCK);
                            break;
                    }
                }).show();
    }

    private void applyWallpaper(int flag) {
        // Beri tanda kalau proses dimulai
        Toast.makeText(this, "Sedang mengunduh gambar...", Toast.LENGTH_SHORT).show();

        Glide.with(getApplicationContext()) // Gunakan applicationContext agar lebih aman
                .asBitmap()
                .load(imageUrl)
                .override(1080, 1920)
                .into(new CustomTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        // Bagian dalam onResourceReady
                        try {
                            // Gunakan getApplicationContext() agar tidak memory leak
                            WallpaperManager wm = WallpaperManager.getInstance(getApplicationContext());

                            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                                // Gunakan bitmapp yang sudah di-resize (resource)
                                wm.setBitmap(resource, null, true, flag);
                            } else {
                                wm.setBitmap(resource);
                            }

                            // Pakai runOnUiThread supaya Toast pasti muncul
                            runOnUiThread(() -> Toast.makeText(ImageDetailActivity.this, "Berhasil Terpasang!", Toast.LENGTH_SHORT).show());

                        } catch (Exception e) {
                            Log.e("WALLPAPER_ERROR", "Detail Error: ", e);
                            runOnUiThread(() -> Toast.makeText(ImageDetailActivity.this, "Gagal: " + e.getMessage(), Toast.LENGTH_LONG).show());
                        }
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) { }

                    @Override
                    public void onLoadFailed(@Nullable Drawable errorDrawable) {
                        super.onLoadFailed(errorDrawable);
                        Log.e("WALLPAPER_ERROR", "Glide gagal download dari: " + imageUrl);
                        Toast.makeText(ImageDetailActivity.this, "Gagal mengunduh gambar, cek koneksi", Toast.LENGTH_SHORT).show();
                    }
                });
    }

}