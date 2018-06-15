package com.prospektdev.trainee_dovhaliuk.list;

import com.prospektdev.trainee_dovhaliuk.database.room.RModelTree;

/**
 * @author Oleksandr Dovhaliuk
 */
public interface IOnButtonClickListener {

    void onImageClicked(RModelTree tree);
    void onLikeClicked(String treeName, boolean isLiked);
    void onShareClicked(String shareText);
}
