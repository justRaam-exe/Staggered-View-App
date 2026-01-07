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
        return view;
    }
}