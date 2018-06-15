package com.prospektdev.trainee_dovhaliuk.list;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.prospektdev.trainee_dovhaliuk.R;

/**
 * @author Oleksandr Dovhaliuk
 */
public class ListFragRVHolder extends RecyclerView.ViewHolder {

    // [START Class Fields]
    private ImageView itemImage;
    private TextView itemName;
    private ImageButton itemLike;
    private ImageButton itemShare;
    // [END Class Fields]


    // [START Class Constructor]
    ListFragRVHolder(View itemView) {
        super(itemView);

        itemImage = itemView.findViewById(R.id.model_frag_list_rv_item_img);
        itemName = itemView.findViewById(R.id.model_frag_list_rv_item_item_name);
        itemLike = itemView.findViewById(R.id.model_frag_list_rv_item_img_btn_like);
        itemShare = itemView.findViewById(R.id.model_frag_list_rv_item_img_btn_share);
    }
    // [END Class Constructor]

    // [START Class Getters and Setters]
    public ImageView getItemImage() {
        return itemImage;
    }

    public void setItemImage(ImageView itemImage) {
        this.itemImage = itemImage;
    }

    public TextView getItemName() {
        return itemName;
    }

    public void setItemName(TextView itemName) {
        this.itemName = itemName;
    }

    public ImageButton getItemLike() {
        return itemLike;
    }

    public void setItemLike(ImageButton itemLike) {
        this.itemLike = itemLike;
    }

    public ImageButton getItemShare() {
        return itemShare;
    }

    public void setItemShare(ImageButton itemShare) {
        this.itemShare = itemShare;
    }
    // [END Class Getters and Setters]

}
