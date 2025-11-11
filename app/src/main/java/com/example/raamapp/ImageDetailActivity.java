package com.example.raamapp;

import android.content.Intent;
import android.os.Bundle;
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

        Intent intent = getIntent();
        int imageRes = intent.getIntExtra("imageRes", 0);
        String title = intent.getStringExtra("title");
        String desc = intent.getStringExtra("desc");

        imageView.setImageResource(imageRes);
        titleView.setText(title);
        descView.setText(desc);
    }
}
