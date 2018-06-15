package com.prospektdev.trainee_dovhaliuk.list;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.prospektdev.trainee_dovhaliuk.R;
import com.prospektdev.trainee_dovhaliuk.database.room.RModelTree;

import java.util.List;

/**
 * @author Oleksandr Dovhaliuk
 */
class ListFragRVAdapter extends RecyclerView.Adapter<ListFragRVHolder> {

    // [START Class Fields]
    private static final String IMAGE_SRC_TEXT = "Image source:";

    private Context context;
    private List<RModelTree> treeList;

    private IOnButtonClickListener iOnButtonClickListener;
    // [END Class Fields]


    // [START Class Constructor]
    ListFragRVAdapter(Context context, List<RModelTree> treeList, IOnButtonClickListener iOnButtonClickListener) {
        this.context = context;
        this.treeList = treeList;
        this.iOnButtonClickListener = iOnButtonClickListener;
    }
    // [END Class Constructor]


    // [START Class Callbacks]
    @NonNull
    @Override
    public ListFragRVHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.model_frag_list_rv_item, parent, false);


        final ListFragRVHolder viewHolder = new ListFragRVHolder(itemView);
        // [START Set OnClickListener for ImageView]
        viewHolder.getItemImage().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int adapterPosition = viewHolder.getAdapterPosition();
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    itemImageClicked(adapterPosition);
                }
            }
        });
        // [END Set OnClickListener for ImageView]

        // [START Set OnClickListener for ItemClick]
        viewHolder.getItemLike().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int adapterPosition = viewHolder.getAdapterPosition();
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    itemLikeClicked(adapterPosition);
                }
            }
        });
        // [END Set OnClickListener for ItemClick]


        // [START Set OnclickListener for ItemShare]
        viewHolder.getItemShare().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int adapterPosition = viewHolder.getAdapterPosition();
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    itemShareClicker(adapterPosition);
                }
            }
        });
        // [END Set OnclickListener for ItemShare]

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListFragRVHolder holder, int position) {
        // get tree for current position
        RModelTree tree = treeList.get(position);

        // get Item Name
        String itemName = tree.getTreeName();
        holder.getItemName().setText(itemName);

        // get Image
        String itemImageLink = tree.getTreeImgUrl();
        Glide
                .with(context)
                .load(itemImageLink)
                .into(holder.getItemImage());

        // get Image Like
        boolean imageLike = tree.isTreeLike();
        if (imageLike) {
            holder.getItemLike().setColorFilter(ContextCompat.getColor(context, R.color.colorAccent));
            holder.getItemLike().setImageResource(R.drawable.ic_cards_heart);
        } else {
            holder.getItemLike().setColorFilter(ContextCompat.getColor(context, android.R.color.transparent));
            holder.getItemLike().setImageResource(R.drawable.ic_heart_outline);
        }
    }

    @Override
    public int getItemCount() {
        return treeList.size();
    }
    // [END Class Callbacks]


    // [START Class Methods]
    public void setData(List<RModelTree> trees) {
        this.treeList = trees;
    }

    public List<RModelTree> getData() {
        return treeList;
    }

    private void itemImageClicked(int position) {
        RModelTree tree = treeList.get(position);
        iOnButtonClickListener.onImageClicked(tree);
    }

    private void itemLikeClicked(int position) {
        // get tree for current position
        RModelTree tree = treeList.get(position);

        String treeName = tree.getTreeName();
        boolean inverseLike = !tree.isTreeLike();
        // set inverse value
        iOnButtonClickListener.onLikeClicked(treeName, inverseLike);
    }

    private void itemShareClicker(int position) {
        // get tree for current position
        RModelTree tree = treeList.get(position);

        String imageName = tree.getTreeName();
        String imageDesc = tree.getTreeDesc();
        String imageLink = tree.getTreeImgUrl();

        String shareText = imageName + "\n"
                + "\n"
                + imageDesc + "\n"
                + "\n"
                + IMAGE_SRC_TEXT + " " + imageLink;

        iOnButtonClickListener.onShareClicked(shareText);
    }
    // [END Class Methods]
}
