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

import com.google.firebase.*;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {

    private RecyclerView recyclerSearch;
    private EditText searchInput;
    private SearchAdapter adapter;
    private ArrayList<ImageModel> imageList = new ArrayList<>();
    private DatabaseReference databaseReference;

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

        adapter = new SearchAdapter(requireContext(), imageList);
        recyclerSearch.setAdapter(adapter);

        databaseReference = FirebaseDatabase.getInstance().getReference("Wallpaper");
        loadAllImages();
        setupSearchBar();
        return view;
    }

    private void loadAllImages() {
        databaseReference.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        imageList.clear();

                        for (DataSnapshot data : snapshot.getChildren()) {
                            ImageModel model = data.getValue(ImageModel.class);
                            if(model != null) {
                                imageList.add(model);
                            }
                        }

                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {}
                }
        );
    }

    private void setupSearchBar() {
        searchInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {}
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                searchFirebase(s.toString());
            }
        });
    }

    private void searchFirebase(String keyword) {
        databaseReference.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        imageList.clear();

                        for (DataSnapshot data : snapshot.getChildren()) {
                            ImageModel model = data.getValue(ImageModel.class);
                            if (model == null) continue;
                            if (model.title.toLowerCase().contains(keyword.toLowerCase()) ||
                                model.category.toLowerCase().contains(keyword.toLowerCase())) {
                                imageList.add(model);
                            }
                        }
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {}
                }
        );
    }
}
