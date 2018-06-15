package com.prospektdev.trainee_dovhaliuk.database.room;

import com.prospektdev.trainee_dovhaliuk.desc.IDescFragModelPresenterBridge;
import com.prospektdev.trainee_dovhaliuk.list.IListFragModelPresenterBridge;
import com.prospektdev.trainee_dovhaliuk.utils.AppClass;

import java.util.List;

/**
 * @author Oleksandr Dovhaliuk
 */
public class RoomWorker {

    // [START Class Fields]
    private RModelTreeDAO treeDAO;
    // [END Class Fields]


    // [START Class Constructor]
    public RoomWorker() {
        // START init Room]
        RAppDatabase roomInstanse = AppClass.getRoomInstanse();
        treeDAO = roomInstanse.getModelTreeDAO();
        // END init Room]
    }
    // [END Class Constructor]


    // [START Class Methods]
    public void fetchData(IListFragModelPresenterBridge bridge) {
        bridge.onDatabaseReadSuccess(treeDAO.getAllTree());
    }

    public void fetchUpdateListData(IListFragModelPresenterBridge bridge) {
        bridge.onDatabaseUpdateSuccess(treeDAO.getAllTree());
    }

    public void fetchUpdateDescData(IDescFragModelPresenterBridge bridge) {
        bridge.onDatabaseUpdateSuccess(treeDAO.getAllTree());
    }

    // for future use
    public void seacrData(IListFragModelPresenterBridge bridge, String query) {
        query = "%" + query + "%";
        List<RModelTree> treeListResponse = treeDAO.getAllTreeWithTreeName(query);
        bridge.onDatabaseQuerySuccess(treeListResponse);
    }

    public void saveData(List<RModelTree> treeList) {
        // Insert Data to Room Database
        treeDAO.insertList(treeList);
    }

    public void setNewLikeValue(String treeName, boolean isLiked) {
        treeDAO.update(treeName, isLiked);
    }
    // [END Class Methods]
}
