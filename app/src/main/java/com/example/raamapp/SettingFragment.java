package com.example.raamapp;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

public class SettingFragment extends Fragment {

    CardView menuAboutApp, menuAboutDev, menuTheme, menuFeedback, menuPrivacy, menuVersion;

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

        menuAboutApp.setOnClickListener(v -> showDialog(
            "About App",
            "RaamApp adalah Aplikasi Anime Wallpaper"
        ));

        menuAboutDev.setOnClickListener(v -> showDialog(
            "About Developer",
            "Developer: Yohanes Bramanta Adita Saputra" +
                    "\nEmail: sayapinterbuatakun@gmail.com" + "\nInstagram: buramanta_" + "\nGithub: justRaam-exe"
        ));
        
        menuTheme.setOnClickListener(v -> showDialog(
            "Theme Setting",
            "Untuk Fitur ubah latar belakang masih di tahap pengerjaan"
        ));
        
        menuFeedback.setOnClickListener(v -> {
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
            emailIntent.setData(Uri.parse("mailto:sayapinterbuatakun@gmail.com"));
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Feedback Aplikasi");
            startActivity(emailIntent);
        });

        menuPrivacy.setOnClickListener(v -> showDialog(
            "Privacy and Policy",
            "Aplikasi ini ..."
        ));

        menuVersion.setOnClickListener(v -> showDialog(
            "Version Info",
                "Versi Aplikasi: 1.0.0" + "\nTanggal Rilis: 2025"

        ));
    }

    private void showDialog(String title, String message) {
        new AlertDialog.Builder(getContext()).setTitle(title).setMessage(message).setPositiveButton("OK", null).show();
    }
}