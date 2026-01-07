package com.raam.weebapp;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ImageDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);

        ImageView imageView = findViewById(R.id.detailImage);
        TextView titleView = findViewById(R.id.detailTitle);
        TextView descView = findViewById(R.id.detailDesc);
        ImageButton btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(v -> onBackPressed());

        int imageRes = getIntent().getIntExtra("imageRes", 0);
        String title = getIntent().getStringExtra("imageTitle");
        String desc = getIntent().getStringExtra("imageDesc");

        if (imageRes != 0) {
            imageView.setImageResource(imageRes);
        }
        titleView.setText(title != null ? title : "No title");
        descView.setText(desc != null ? desc : "No description");
    }
}