package com.raam.weebapp;

import static com.google.firebase.database.FirebaseDatabase.getInstance;

import androidx.annotation.NonNull;
import com.google.firebase.database.*;
import java.util.ArrayList;
import java.util.List;

public class FirebaseHelper {
        private final DatabaseReference ref;
        public FirebaseHelper() {
            ref = FirebaseDatabase.getInstance().getReference("Wallpaper");
        }

        public void getAllImages(FirebaseCallback callback) {
            ref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    List<ImageModel> list = new ArrayList<>();

                    for (DataSnapshot snap : snapshot.getChildren()) {
                        ImageModel model = snap.getValue(ImageModel.class);
                        list.add(model);
                    }
                    callback.onSuccess(list);
                }
               @Override
               public void onCancelled(@NonNull DatabaseError error) {}
            });
        }

        public void getByCategory(String category, FirebaseCallback callback) {
            ref.orderByChild("catagory")
                    .equalTo(category)
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                       @Override
                       public void onDataChange(@NonNull DataSnapshot snapshot) {
                           List<ImageModel> list = new ArrayList<>();

                           for (DataSnapshot snap : snapshot.getChildren()) {
                               ImageModel model = snap.getValue(ImageModel.class);
                               list.add(model);
                           }

                           callback.onSuccess(list);
                       }

                       @Override
                        public void onCancelled(@NonNull DatabaseError error) {}
                    });
        }

        public interface FirebaseCallback {
            void onSuccess(List<ImageModel> list);
        }
}
