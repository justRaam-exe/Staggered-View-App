package com.example.raamapp;

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

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {

    private RecyclerView recyclerSearch;
    private EditText searchInput;
    private SearchAdapter adapter;
    private List<JSONObject> imageDataList = new ArrayList<>();

    public SearchFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        recyclerSearch = view.findViewById(R.id.recycleSearch);
        searchInput = view.findViewById(R.id.searchInput);

        recyclerSearch.setLayoutManager(
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        );

        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.recycler_spacing);
        recyclerSearch.addItemDecoration(new GridSpacingItemDecoration(2, spacingInPixels, true));

        loadJsonData();
        adapter = new SearchAdapter(requireContext(), imageDataList);
        recyclerSearch.setAdapter(adapter);

        setupSearchBar();
        return view;
    }

    private void loadJsonData() {
        try {
            InputStream is = requireContext().getAssets().open("image_data.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            String jsonString = new String(buffer, StandardCharsets.UTF_8);
            JSONArray jsonArray = new JSONArray(jsonString);

            imageDataList.clear();
            for (int i = 0; i < jsonArray.length(); i++) {
                imageDataList.add(jsonArray.getJSONObject(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        List<JSONObject> filteredList = new ArrayList<>();
        for (JSONObject obj : imageDataList) {
            try {
                String title = obj.getString("title");
                if (title.toLowerCase().contains(query.toLowerCase())) {
                    filteredList.add(obj);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        adapter.updateData(filteredList);
    }
}
