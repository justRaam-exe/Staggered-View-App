package com.raam.weebapp;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

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