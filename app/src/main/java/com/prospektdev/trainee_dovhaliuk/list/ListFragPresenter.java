package com.prospektdev.trainee_dovhaliuk.list;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.prospektdev.trainee_dovhaliuk.database.room.RModelTree;

import java.util.List;

/**
 * @author Oleksandr Dovhaliuk
 */

@InjectViewState
public class ListFragPresenter extends MvpPresenter<IListFrag> {

    // [START Class Fields]
    private ListFragModel listFragModel;

    private static int recyclerScrollPosition;
    // [END Class Fields]


    // [START Class Constructor]
    ListFragPresenter() {
        IListFragModelPresenterBridge bridge = new IListFragModelPresenterBridge() {
            @Override
            public void onDatabaseQuerySuccess(List<RModelTree> treeList) {

            }

            @Override
            public void onDatabaseReadSuccess(List<RModelTree> treeList) {
                if (treeList.size() > 0) {
                    getViewState().hideWidgetsForEmptyDatabase();
                    getViewState().showRecycler(treeList);
                } else {
                    getViewState().showWidgetsForEmptyDatabase();
                }
                getViewState().hideProgress();
            }

            @Override
            public void onDatabaseUpdateSuccess(List<RModelTree> newTreeList) {
                getViewState().updateAdapter(newTreeList);
            }
        };
        listFragModel = new ListFragModel(bridge);
    }
    // [END Class Constructor]


    // [START Class Callbacks]
    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getData();
    }

    // [END Callbacks between ListFragModel and ListFragPresenter]

    // [END Class Callbacks]


    // [START Class Methods]
    public void getData() {
        getViewState().showProgress();
        getViewState().hideWidgetsForEmptyDatabase();
        listFragModel.getData();
    }

    public void updateData() {
        listFragModel.updateData();
    }

    public void onLikeClicked(String treeName, boolean isLiked) {
        listFragModel.setNewLikeValue(treeName, isLiked);
    }

    public void saveRecyclerScrollPosition(int position) {
        recyclerScrollPosition = position;
    }

    public int getRecyclerScrollPosition() {
        return recyclerScrollPosition;
    }
    // [END Class Methods]

}
