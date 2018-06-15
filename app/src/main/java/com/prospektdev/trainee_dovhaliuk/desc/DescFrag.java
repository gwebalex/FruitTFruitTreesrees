package com.prospektdev.trainee_dovhaliuk.desc;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.MvpFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.prospektdev.trainee_dovhaliuk.IToolbar;
import com.prospektdev.trainee_dovhaliuk.MainActivity;
import com.prospektdev.trainee_dovhaliuk.R;
import com.prospektdev.trainee_dovhaliuk.database.room.RModelTree;
import com.prospektdev.trainee_dovhaliuk.utils.AppLogs;

import java.util.List;

/**
 * @author Oleksandr Dovhaliuk
 */

public class DescFrag extends MvpFragment implements IDescFrag, View.OnClickListener {

    // [START Class Fields]
    private static final String IMAGE_SRC_TEXT = "Image source:";

    private ImageButton imgBtnLike;

    private RModelTree tree;

    @InjectPresenter
    DescFragPresenter descFragPresenter;
    // [END Class Fields]


    // [START Class Callbacks]
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        // [START Get Payload from Bundle]
        Bundle treeObject = getArguments();
        tree = new RModelTree();
        if (treeObject != null) {
            String treeStr = treeObject.getString(MainActivity.BUNDLE_TREE_OBJECT_TAG);
            tree = new Gson().fromJson(treeStr, RModelTree.class);
        }
        // [END Get Payload from Bundle]


        View descFragView = inflater.inflate(R.layout.frag_desc, container, false);

        ImageView image = descFragView.findViewById(R.id.frag_desc_item_img);
        TextView tvName = descFragView.findViewById(R.id.frag_desc_item_item_name);
        TextView tvDesc = descFragView.findViewById(R.id.frag_desc_tv_desc);

        imgBtnLike = descFragView.findViewById(R.id.frag_desc_item_img_btn_like);
        imgBtnLike.setOnClickListener(this);
        ImageButton imgBtnShare = descFragView.findViewById(R.id.frag_desc_item_img_btn_share);
        imgBtnShare.setOnClickListener(this);

        if (tree != null) {
            // set Image
            String imageUrl = tree.getTreeImgUrl();
            Glide
                    .with(getActivity())
                    .load(imageUrl)
                    .into(image);
            // set Name
            String treeName = tree.getTreeName();
            tvName.setText(treeName);
            // get Image Like
            boolean imageLike = tree.isTreeLike();
            setLikeValue(imageLike);
            // set Title
            IToolbar toolbar = (IToolbar) getActivity();
            toolbar.onTitleChanged(treeName);

            // set Description
            tvDesc.setText(tree.getTreeDesc());
        }

        return descFragView;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.frag_desc_item_img_btn_like:
                AppLogs.show("like clicked");
                boolean inverseLike = !tree.isTreeLike();
                descFragPresenter.onLikeBtnClicked(tree.getTreeName(), inverseLike);
                break;

            case R.id.frag_desc_item_img_btn_share:

                String imageName = tree.getTreeName();
                String imageDesc = tree.getTreeDesc();
                String imageLink = tree.getTreeImgUrl();

                String shareText = imageName + "\n"
                        + "\n"
                        + imageDesc + "\n"
                        + "\n"
                        + IMAGE_SRC_TEXT + " " + imageLink;

                onShareClicked(shareText);
                break;
        }
    }

    @Override
    public void onLikeUpdate(List<RModelTree> treeList) {
        for (RModelTree updTree : treeList) {
            if (updTree.getTreeName().equals(tree.getTreeName())) {
                setLikeValue(updTree.isTreeLike());
                break;
            }
        }
    }
    // [END Class Callbacks]

    // [START Class Methods]
    private void onShareClicked(String shareText) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");

        shareIntent.putExtra(Intent.EXTRA_TEXT, shareText);
        startActivity(
                Intent.createChooser(
                        shareIntent,
                        getString(R.string.dialog_share_title)
                )
        );
    }

    private void setLikeValue(boolean imageLike) {
        AppLogs.show("ima " + imageLike);
        tree.setTreeLike(imageLike);
        if (imageLike) {
            imgBtnLike.setColorFilter(ContextCompat.getColor(getActivity(), R.color.colorAccent));
            imgBtnLike.setImageResource(R.drawable.ic_cards_heart);
        } else {
            imgBtnLike.setColorFilter(ContextCompat.getColor(getActivity(), android.R.color.transparent));
            imgBtnLike.setImageResource(R.drawable.ic_heart_outline);
        }
    }
    // [END Class Methods]
}
