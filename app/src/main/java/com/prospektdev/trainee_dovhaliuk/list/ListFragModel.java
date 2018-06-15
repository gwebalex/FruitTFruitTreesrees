package com.prospektdev.trainee_dovhaliuk.list;

import com.prospektdev.trainee_dovhaliuk.database.firebase.FirebaseWorker;
import com.prospektdev.trainee_dovhaliuk.database.room.RoomWorker;
import com.prospektdev.trainee_dovhaliuk.utils.manager.InternetConnection;

/**
 * @author Oleksandr Dovhaliuk
 */
class ListFragModel {

    // [START Class Fields]
    private RoomWorker roomWorker;
    private FirebaseWorker firebaseWorker;

    private IListFragModelPresenterBridge bridge;
    // [END Class Fields]


    // [START Class Constructor]
    ListFragModel(IListFragModelPresenterBridge bridge) {
        this.bridge = bridge;
        roomWorker = new RoomWorker();
        firebaseWorker = new FirebaseWorker();
    }
    // [END Class Constructor]


    // [START Class Methods]
    public void getData() {
        if (InternetConnection.isOnline()) {
            firebaseWorker.fetchData(bridge);
        } else {
            roomWorker.fetchData(bridge);
        }
    }

    public void updateData() {
        roomWorker.fetchUpdateListData(bridge);
    }

    public void setNewLikeValue(String treeName, boolean isLiked) {
        // update data in Local Storage - Room
        roomWorker.setNewLikeValue(treeName, isLiked);

        // if online -> update data in Cloud Storage - Firebase Realtime Database
        if (InternetConnection.isOnline()) {
            firebaseWorker.setNewLikeValue(treeName, isLiked);
        }

        roomWorker.fetchUpdateListData(bridge);
    }
    // [END Class Methods]

}
