package com.example.raamapp;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {
    ImageButton homeBtn, searchBtn, settingBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        homeBtn = findViewById(R.id.btn_home);
        searchBtn = findViewById(R.id.btn_search);
        settingBtn = findViewById(R.id.btn_setting);

        replaceFragment(new HomeFragment());

        homeBtn.setOnClickListener(v -> replaceFragment(new HomeFragment()));
        searchBtn.setOnClickListener(v -> replaceFragment(new SearchFragment()));
        settingBtn.setOnClickListener(v -> replaceFragment(new SettingFragment()));
    }
    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
    }
}
//
//public class MainActivity extends AppCompatActivity {
//
//    ImageView home, search, profile;
//    RecyclerView recyclerView;
//    int[] images = {
//            R.drawable.img_1,
//            R.drawable.img_2,
//            R.drawable.img_3,
//            R.drawable.img_4,
//            R.drawable.img_5,
//            R.drawable.img_6,
//            R.drawable.img_7,
//            R.drawable.img_8,
//            R.drawable.img_9
//    };
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_main);
//
//        home = findViewById(R.id.home);
//        search = findViewById(R.id.search);
//        profile = findViewById(R.id.profile);
//
//        replaceFragment(new HomeFragment());
//
//        home.setOnClickListener(v -> {
//            replaceFragment(new HomeFragment());
//            setActiveIcon(home);
//        });
//
//        search.setOnClickListener(v -> {
//            replaceFragment(new SearchFragment());
//            setActiveIcon(search);
//        });
//
//        profile.setOnClickListener(v -> {
//           replaceFragment(new ProfileFragment());
//        });
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
//        recyclerView = findViewById(R.id.recycleView);
//        StaggeredGridLayoutManager layoutManager =
//                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
//        recyclerView.setLayoutManager(layoutManager);
//
//        ImageAdapter adapter = new ImageAdapter(this, images);
//        recyclerView.setAdapter(adapter);
//
//        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.recycler_spacing);
//        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, spacingInPixels, true));
//
//        ImageButton home = findViewById(R.id.btn_home);
//        ImageButton search = findViewById(R.id.btn_search);
//        ImageButton profile = findViewById(R.id.btn_profile);
//
//        home.setOnClickListener(v -> {
//            Toast.makeText(this, "Home Clicked", Toast.LENGTH_SHORT).show();
//            resetIconTint(home, search, profile);
//            home.setColorFilter(getColor(R.color.white));
//        });
//
//        search.setOnClickListener(v -> {
//            Toast.makeText(this, "Search Clicked", Toast.LENGTH_SHORT).show();
//            resetIconTint(home, search, profile);
//            search.setColorFilter(getColor(R.color.white));
//        });
//
//        profile.setOnClickListener(v -> {
//            Toast.makeText(this, "Profile Clicked", Toast.LENGTH_SHORT).show();
//            resetIconTint(home, search, profile);
//            profile.setColorFilter(getColor(R.color.white));
//        });
//    }
//
//    private void replaceFragment(Fragment fragment) {
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction transaction = fragmentManager.beginTransaction();
//        transaction.replace(R.id.fragment_container, fragment);
//        transaction.commit();
//    }
//
//    private void setActiveIcon(ImageView active) {
//        home.setColorFilter(getColor(R.color.gray));
//        search.setColorFilter(getColor(R.color.gray));
//        profile.setColorFilter(getColor(R.color.gray));
//    }
//    private void resetIconTint(ImageButton... buttons) {
//        for (ImageButton btn : buttons) {
//            btn.setColorFilter(getColor(android.R.color.white));
//        }
//    }
//}
