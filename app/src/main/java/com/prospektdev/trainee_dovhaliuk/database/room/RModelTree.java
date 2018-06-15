package com.prospektdev.trainee_dovhaliuk.database.room;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * @author Oleksandr Dovhaliuk
 */

@Entity
public class RModelTree {

    // [START Class Fields]
    @PrimaryKey @NonNull
    private String treeName;
    private String treeDesc;
    private boolean treeLike;
    private String treeImgUrl;
    // [END Class Fields]


    // [START Class Getters and Setters]
    @NonNull
    public String getTreeName() {
        return treeName;
    }

    public void setTreeName(@NonNull String treeName) {
        this.treeName = treeName;
    }

    public String getTreeDesc() {
        return treeDesc;
    }

    public void setTreeDesc(String treeDesc) {
        this.treeDesc = treeDesc;
    }

    public boolean isTreeLike() {
        return treeLike;
    }

    public void setTreeLike(boolean treeLike) {
        this.treeLike = treeLike;
    }

    public String getTreeImgUrl() {
        return treeImgUrl;
    }

    public void setTreeImgUrl(String treeImgUrl) {
        this.treeImgUrl = treeImgUrl;
    }
    // [END Class Getters and Setters]

}
