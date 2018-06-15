package com.prospektdev.trainee_dovhaliuk.model;

import android.support.annotation.Keep;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * @author Oleksandr Dovhaliuk
 */

@Keep
@IgnoreExtraProperties
public class FModelTree {

    // [START Class Fields]
    private String imagedesc;
    private boolean imagelike;
    private String imagelink;
    // [END Class Fields]


    // [START Class Constructor]
    public FModelTree() {
        // for snapshot.getValue(FModelTree.class)
    }
    // [END Class Constructor]


    // [START Class Getters and Setters]
    public String getImagedesc() {
        return imagedesc;
    }

    public void setImagedesc(String imagedesc) {
        this.imagedesc = imagedesc;
    }

    public boolean getImagelike() {
        return imagelike;
    }

    public void setImagelike(boolean imagelike) {
        this.imagelike = imagelike;
    }

    public String getImagelink() {
        return imagelink;
    }

    public void setImagelink(String imagelink) {
        this.imagelink = imagelink;
    }

    // [END Class Getters and Setters]


}
