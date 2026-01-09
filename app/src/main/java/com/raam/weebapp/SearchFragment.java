package com.raam.weebapp;

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

public class SearchFragment extends Fragment {

    private RecyclerView recyclerSearch;
    private EditText searchInput;
    private SearchAdapter adapter;
    private DBHelper dbHelper;

    public SearchFragment() {}

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        recyclerSearch = view.findViewById(R.id.recycleSearch);
        searchInput = view.findViewById(R.id.searchInput);

        recyclerSearch.setLayoutManager(
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        );

        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.recycler_spacing);
        recyclerSearch.addItemDecoration(new GridSpacingItemDecoration(2, spacingInPixels, true));

        dbHelper = new DBHelper(requireContext());
        List<ImageModel> imageList = dbHelper.getAllImages();

        adapter = new SearchAdapter(requireContext(), imageList);
        recyclerSearch.setAdapter(adapter);

        setupSearchBar();
        return view;
    }

    private void setupSearchBar() {
        searchInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {}
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterImages(s.toString());
            }
        });
    }

    private void filterImages(String query) {
        List<ImageModel> filteredList;
        if(query.isEmpty()) {
            filteredList = dbHelper.getAllImages();
        } else {
            filteredList = dbHelper.searchImages(query);
        }

        adapter.updateData(filteredList);
    }
}
