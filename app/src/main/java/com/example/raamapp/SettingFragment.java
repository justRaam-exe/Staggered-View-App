package com.example.raamapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SettingFragment extends Fragment {

    LinearLayout menuAboutApp, menuAboutDev, menuTheme, menuFeedback, menuPrivacy, menuVersion;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,@Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);

        menuAboutApp = view.findViewById(R.id.menuAboutApp);
        menuAboutDev = view.findViewById(R.id.menuAboutDev);
        menuTheme = view.findViewById(R.id.menuTheme);
        menuFeedback = view.findViewById(R.id.menuFeedback);
        menuPrivacy = view.findViewById(R.id.menuPrivacy);
        menuVersion = view.findViewById(R.id.menuVersion);

        setupClicks();

        return view;
    }

    private void setupClicks() {

        menuAboutApp.setOnClickListener(v -> {
            Toast.makeText(getContext(), "About App Clicked", Toast.LENGTH_SHORT).show();
        });

        menuAboutDev.setOnClickListener(v -> {
            Toast.makeText(getContext(), "About Dev Clicked", Toast.LENGTH_SHORT).show();
        });
        
        menuTheme.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Theme Setting Clicked", Toast.LENGTH_SHORT).show();
        });
        
        menuFeedback.setOnClickListener(v -> {
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
            emailIntent.setData(Uri.parse("mailto : sayapinterbuatakun@gmail.com"));
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Feedback Aplikasi");
        });

        menuPrivacy.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Opening Privacy Policy", Toast.LENGTH_SHORT).show();
        });

        menuVersion.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Version 1.0.0", Toast.LENGTH_SHORT).show();
        });
    }
}