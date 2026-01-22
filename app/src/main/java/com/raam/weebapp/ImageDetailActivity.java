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
        Toast.makeText(this, "Menyiapkan gambar...", Toast.LENGTH_SHORT).show();

        Glide.with(this)
                .asBitmap()
                .load(imageUrl)
                .into(new CustomTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        try {
                            // Simpan di cache internal aplikasi (lebih aman)
                            File cachePath = new File(getCacheDir(), "images");
                            cachePath.mkdirs(); // buat folder jika belum ada
                            File file = new File(cachePath, "temp_wallpaper.jpg");

                            FileOutputStream stream = new FileOutputStream(file);
                            resource.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                            stream.close();

                            // Ambil URI via FileProvider
                            Uri contentUri = FileProvider.getUriForFile(ImageDetailActivity.this,
                                    getPackageName() + ".provider", file);

                            if (contentUri != null) {
                                Intent intent = new Intent(WallpaperManager.ACTION_CROP_AND_SET_WALLPAPER);
                                intent.setDataAndType(contentUri, "image/*");
                                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

                                // Penting: tambahkan baris ini untuk beberapa HP tertentu
                                intent.putExtra("mimeType", "image/*");

                                startActivity(Intent.createChooser(intent, "Set Wallpaper:"));
                            }

                        } catch (Exception e) {
                            Log.e("WALLPAPER_ERROR", "Gagal: " + e.getMessage());
                            Toast.makeText(ImageDetailActivity.this, "Gagal: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {}

                    @Override
                    public void onLoadFailed(@Nullable Drawable errorDrawable) {
                        Toast.makeText(ImageDetailActivity.this, "Gagal download gambar", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}