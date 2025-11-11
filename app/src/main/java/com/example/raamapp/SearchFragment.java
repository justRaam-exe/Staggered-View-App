package com.example.raamapp;

import android.media.Image;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment{

    private RecyclerView recyclerSearch;
    private EditText searchInput;
    private ImageAdapter adapter;

    int[] images = {
            R.drawable.img_1,
            R.drawable.img_2,
            R.drawable.img_3,
            R.drawable.img_4,
            R.drawable.img_5,
            R.drawable.img_6,
            R.drawable.img_7,
            R.drawable.img_8,
            R.drawable.img_9,
            R.drawable.img_10,
            R.drawable.img_11,
            R.drawable.img_12,
            R.drawable.img_13,
            R.drawable.img_14,
            R.drawable.img_15,
            R.drawable.img_16,
            R.drawable.img_17,
            R.drawable.img_18,
            R.drawable.img_19,
            R.drawable.img_20,
            R.drawable.img_21,
            R.drawable.img_22,
            R.drawable.img_23,
            R.drawable.img_24,
            R.drawable.img_25,
            R.drawable.img_26,
    };

    private List<Integer> allImages;
    private List<Integer> filteredImages;

    public SearchFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        recyclerSearch = view.findViewById(R.id.recycleSearch);
        searchInput = view.findViewById(R.id.searchInput);

        allImages = new ArrayList<>();
        for (int img : images) {
            allImages.add(img);
        }
        filteredImages = new ArrayList<>(allImages);

        recyclerSearch.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        adapter = new ImageAdapter(requireContext(), filteredImages.stream().mapToInt(Integer::intValue).toArray());
        recyclerSearch.setAdapter(adapter);

        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.recycler_spacing);
        recyclerSearch.addItemDecoration(new GridSpacingItemDecoration(2, spacingInPixels, true));

        setupSearchBar();

        return view;
    }

    private void setupSearchBar() {
        searchInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) { }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterImages(s.toString());
            }
        });
    }

    private void filterImages(String query){
        filteredImages.clear();
        if (query.isEmpty()) {
            filteredImages.addAll(allImages);
        } else {
            for (int i = 0; i < allImages.size(); i++){
                if (i % 2 == 0) {
                    filteredImages.add(allImages.get(i));
                }
            }
        }
        adapter = new ImageAdapter(requireContext(), filteredImages.stream().mapToInt(Integer::intValue).toArray());
        recyclerSearch.setAdapter(adapter);
    }
}
