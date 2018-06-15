package com.prospektdev.trainee_dovhaliuk.database.firebase;

import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.prospektdev.trainee_dovhaliuk.database.room.RModelTree;
import com.prospektdev.trainee_dovhaliuk.database.room.RoomWorker;
import com.prospektdev.trainee_dovhaliuk.list.IListFragModelPresenterBridge;
import com.prospektdev.trainee_dovhaliuk.model.FModelTree;
import com.prospektdev.trainee_dovhaliuk.utils.AppClass;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Oleksandr Dovhaliuk
 */
public class FirebaseWorker {

    // [START Class Fields]
    private static final String FIRE_ROOT_KEY = "trees";
    private static final String FIRE_ITEM_FIELD_LIKE = "imagelike";

    private DatabaseReference rootRef;
    // [END Class Fields]


    // [START Class Constructor]
    public FirebaseWorker() {

        // [START init Firebase and Node References]
        FirebaseDatabase firebaseDatabase = AppClass.getFirebaseInstanse();
        rootRef = firebaseDatabase.getReference(FIRE_ROOT_KEY);
        // [END init Firebase and Node References]
    }
    // [END Class Constructor]


    public void fetchData(final IListFragModelPresenterBridge bridge) {

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<RModelTree> rModelTreeList = new ArrayList<>();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    // Firebase Key + Body items
                    FModelTree fModelTree = snapshot.getValue(FModelTree.class);

                    if (fModelTree != null) {
                        // [START Create Room Model Tree]
                        RModelTree rModelTree = new RModelTree();
                        rModelTree.setTreeName(snapshot.getKey());
                        rModelTree.setTreeDesc(fModelTree.getImagedesc());
                        rModelTree.setTreeLike(fModelTree.getImagelike());
                        rModelTree.setTreeImgUrl(fModelTree.getImagelink());
                        // [END Create Room Model Tree]

                        // Put Data to List<RModelTree>
                        rModelTreeList.add(rModelTree);
                    }
                }

                if (rModelTreeList.size() > 0) {
                    // Insert Data to Room Database
                    RoomWorker roomWorker = new RoomWorker();
                    roomWorker.saveData(rModelTreeList);

                    // return List to Presenter
                    bridge.onDatabaseReadSuccess(rModelTreeList);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        };
        rootRef.addListenerForSingleValueEvent(valueEventListener);
    }

    public void setNewLikeValue(String treeName, boolean isLiked) {
        Map<String, Object> updMap = new HashMap<>();
        updMap.put(FIRE_ITEM_FIELD_LIKE, isLiked);

        rootRef.child(treeName).updateChildren(updMap);
    }
}
