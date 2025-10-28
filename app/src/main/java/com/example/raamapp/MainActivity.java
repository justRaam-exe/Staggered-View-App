package com.example.raamapp;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    int[] images = {
            R.drawable.img_1,
            R.drawable.img_2,
            R.drawable.img_3,
            R.drawable.img_4,
            R.drawable.img_5,
            R.drawable.img_6,
            R.drawable.img_7,
            R.drawable.img_8,
            R.drawable.img_9
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        recyclerView = findViewById(R.id.recycleView);
        StaggeredGridLayoutManager layoutManager =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        ImageAdapter adapter = new ImageAdapter(this, images);
        recyclerView.setAdapter(adapter);

        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.recycler_spacing);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, spacingInPixels, true));

        ImageButton home = findViewById(R.id.btn_home);
        ImageButton search = findViewById(R.id.btn_search);
        ImageButton profile = findViewById(R.id.btn_profile);

        home.setOnClickListener(v -> {
            Toast.makeText(this, "Home Clicked", Toast.LENGTH_SHORT).show();
            resetIconTint(home, search, profile);
            home.setColorFilter(getColor(R.color.white));
        });

        search.setOnClickListener(v -> {
            Toast.makeText(this, "Search Clicked", Toast.LENGTH_SHORT).show();
            resetIconTint(home, search, profile);
            search.setColorFilter(getColor(R.color.white));
        });

        profile.setOnClickListener(v -> {
            Toast.makeText(this, "Profile Clicked", Toast.LENGTH_SHORT).show();
            resetIconTint(home, search, profile);
            profile.setColorFilter(getColor(R.color.white));
        });
    }

    private void resetIconTint(ImageButton... buttons) {
        for (ImageButton btn : buttons) {
            btn.setColorFilter(getColor(android.R.color.white));
        }
    }
}