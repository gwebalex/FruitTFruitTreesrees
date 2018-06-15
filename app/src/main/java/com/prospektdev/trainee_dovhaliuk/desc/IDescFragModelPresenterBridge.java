package com.prospektdev.trainee_dovhaliuk.desc;

import com.prospektdev.trainee_dovhaliuk.database.room.RModelTree;

import java.util.List;

/**
 * @author Oleksandr Dovhaliuk
 */
public interface IDescFragModelPresenterBridge {

    void onDatabaseUpdateSuccess(List<RModelTree> treeList);
}
