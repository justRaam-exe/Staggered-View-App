package com.raam.weebapp;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.widget.Button;

import com.google.firebase.database.*;
import java.util.ArrayList;

public class HomeFragment extends Fragment {

    RecyclerView recyclerView;
    ImageAdapter adapter;
    ArrayList<ImageModel> imageList = new ArrayList<>();

    DatabaseReference databaseReference;
    Button btnAll, btnNaruto, btnSolo, btnDeSlay;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        );

        // 🔹 Adapter
        adapter = new ImageAdapter(requireContext(), imageList);
        recyclerView.setAdapter(adapter);

        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.recycler_spacing);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, spacingInPixels, true));

        databaseReference = FirebaseDatabase.getInstance().getReference("Wallpaper");

        btnAll = view.findViewById(R.id.btnAll);
        btnNaruto = view.findViewById(R.id.btnNaruto);
        btnSolo = view.findViewById(R.id.btnSoloLeveling);
        btnDeSlay = view.findViewById(R.id.btnDemonSlayer);

        btnAll.setOnClickListener(v -> loadData(null));
        btnNaruto.setOnClickListener(v -> loadData("Naruto"));
        btnSolo.setOnClickListener(v -> loadData("Solo Leveling"));
        btnDeSlay.setOnClickListener(v -> loadData("Demon Slayer"));

        loadData(null);
        return view;
    }

    private void loadData(String catagory) {
        databaseReference.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        imageList.clear();

                        for (DataSnapshot data : snapshot.getChildren()) {
                            ImageModel model = data.getValue(ImageModel.class);
                            if (model == null) continue;

                            if (catagory == null || model.category.equalsIgnoreCase(catagory)) {
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
