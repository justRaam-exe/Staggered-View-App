package com.raam.weebapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AlertDialog;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

public class SettingFragment extends Fragment {

    CardView menuAboutApp, menuAboutDev, menuTheme, menuFeedback, menuPrivacy, menuVersion;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,@Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);

        View menuAboutApp = view.findViewById(R.id.menuAboutApp);
        View menuAboutDev = view.findViewById(R.id.menuAboutDev);
        View menuTheme = view.findViewById(R.id.menuTheme);
        View menuFeedback = view.findViewById(R.id.menuFeedback);
        View menuPrivacy = view.findViewById(R.id.menuPrivacy);
        View menuVersion = view.findViewById(R.id.menuVersion);

        menuAboutApp.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), AboutAppActivity.class);
            startActivity(intent);
        });

        menuAboutDev.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), AboutDevActivity.class);
            startActivity(intent);
        });

        menuTheme.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), ThemeSetting.class);
            startActivity(intent);
        });

        menuPrivacy.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), AboutPrivacyPolicy.class);
            startActivity(intent);
        });

        menuFeedback.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(android.net.Uri.parse("mailto:sayapinterbuatakun@gmail.com"));
            intent.putExtra(Intent.EXTRA_SUBJECT, "Feedback WeebsApp");
            intent.putExtra(Intent.EXTRA_TEXT, "Hai Atmint, \nSaya ingin memberikan feedback:\n");

            startActivity(Intent.createChooser(intent, "Kirim feedback via"));
        });

        menuVersion.setOnClickListener(v -> {
            new AlertDialog.Builder(requireContext())
                    .setTitle("Version Info")
                    .setMessage(
                                    "App Name : WeebsApp\n" +
                                    "Version  : 1.0\n" +
                                    "Build    : 1"
                    )
                    .setPositiveButton("Ok", (dialog, which) -> dialog.dismiss())
                    .show();
        });
        return view;
    }
}