package com.prospektdev.trainee_dovhaliuk.desc;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.prospektdev.trainee_dovhaliuk.database.room.RModelTree;

import java.util.List;

/**
 * @author Oleksandr Dovhaliuk
 */

@InjectViewState
public class DescFragPresenter extends MvpPresenter<IDescFrag> {

    // [START Class Fields]
    private DescFragModel descFragModel;
    // [END Class Fields]


    // [START Class Constructor]
    DescFragPresenter() {
        IDescFragModelPresenterBridge bridge = new IDescFragModelPresenterBridge() {
            @Override
            public void onDatabaseUpdateSuccess(List<RModelTree> treeList) {
                getViewState().onLikeUpdate(treeList);
            }
        };
        this.descFragModel = new DescFragModel(bridge);
    }
    // [END Class Constructor]


    // [END Class Callbacks]


    // [START Class Methods]
    public void onLikeBtnClicked(String treeName, boolean isLiked) {
        descFragModel.setNewLikeValue(treeName, isLiked);
    }
    // [END Class Methods]
}
