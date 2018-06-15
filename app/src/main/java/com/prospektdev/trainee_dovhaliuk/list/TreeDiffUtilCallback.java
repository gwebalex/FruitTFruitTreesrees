package com.prospektdev.trainee_dovhaliuk.list;

import android.support.v7.util.DiffUtil;

import com.prospektdev.trainee_dovhaliuk.database.room.RModelTree;

import java.util.List;

/**
 * @author Oleksandr Dovhaliuk
 */
public class TreeDiffUtilCallback extends DiffUtil.Callback {

    // [START Class Fields]
    private List<RModelTree> oldList;
    private List<RModelTree> newList;
    // [END Class Fields]


    // [START Class Constructor]
    TreeDiffUtilCallback(List<RModelTree> oldList, List<RModelTree> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }
    // [END Class Constructor]


    // [START Class Callbacks]
    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        RModelTree oldTree = oldList.get(oldItemPosition);
        RModelTree newTree = newList.get(newItemPosition);

        return oldTree.getTreeName().equals(newTree.getTreeName());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        RModelTree oldTree = oldList.get(oldItemPosition);
        RModelTree newTree = newList.get(newItemPosition);

        return oldTree.getTreeDesc().equals(newTree.getTreeDesc())
                && oldTree.getTreeImgUrl().equals(newTree.getTreeImgUrl())
                && oldTree.isTreeLike() == newTree.isTreeLike();
    }
    // [END Class Callbacks]

}
