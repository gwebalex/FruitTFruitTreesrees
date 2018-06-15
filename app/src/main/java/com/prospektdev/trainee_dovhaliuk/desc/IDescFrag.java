package com.prospektdev.trainee_dovhaliuk.desc;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.prospektdev.trainee_dovhaliuk.database.room.RModelTree;

import java.util.List;

/**
 * @author Oleksandr Dovhaliuk
 */

@StateStrategyType(AddToEndSingleStrategy.class)
public interface IDescFrag extends MvpView {

    void onLikeUpdate(List<RModelTree> treeList);
}
