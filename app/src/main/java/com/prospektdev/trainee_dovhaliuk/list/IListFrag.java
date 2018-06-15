package com.prospektdev.trainee_dovhaliuk.list;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.prospektdev.trainee_dovhaliuk.database.room.RModelTree;

import java.util.List;

/**
 * @author Oleksandr Dovhaliuk
 */

@StateStrategyType(AddToEndSingleStrategy.class)
public interface IListFrag extends MvpView {

    void showProgress();

    void hideProgress();

    void showWidgetsForEmptyDatabase();

    void hideWidgetsForEmptyDatabase();

    void showRecycler(List<RModelTree> treeList);

    void updateAdapter(List<RModelTree> newTreeList);

}
