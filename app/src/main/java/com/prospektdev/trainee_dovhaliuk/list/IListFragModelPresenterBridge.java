package com.prospektdev.trainee_dovhaliuk.list;

import com.prospektdev.trainee_dovhaliuk.database.room.RModelTree;

import java.util.List;

/**
 * @author Oleksandr Dovhaliuk
 */
public interface IListFragModelPresenterBridge {

    void onDatabaseReadSuccess(List<RModelTree> treeList);
    void onDatabaseQuerySuccess(List<RModelTree> treeList);
    void onDatabaseUpdateSuccess(List<RModelTree> newTreeList);
}
