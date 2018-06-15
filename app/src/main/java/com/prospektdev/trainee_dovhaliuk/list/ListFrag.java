package com.prospektdev.trainee_dovhaliuk.list;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;

import com.arellomobile.mvp.MvpFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.google.gson.Gson;
import com.prospektdev.trainee_dovhaliuk.IFragmentSwitch;
import com.prospektdev.trainee_dovhaliuk.IToolbar;
import com.prospektdev.trainee_dovhaliuk.MainActivity;
import com.prospektdev.trainee_dovhaliuk.R;
import com.prospektdev.trainee_dovhaliuk.database.room.RModelTree;
import com.prospektdev.trainee_dovhaliuk.desc.DescFrag;
import com.prospektdev.trainee_dovhaliuk.utils.AppClass;
import com.prospektdev.trainee_dovhaliuk.utils.AppLogs;
import com.prospektdev.trainee_dovhaliuk.utils.manager.InternetConnection;

import java.util.List;

/**
 * @author Oleksandr Dovhaliuk
 */

public class ListFrag extends MvpFragment implements IListFrag, IListFragRVAdapterListener {


// [START Class Fields]
    private ProgressBar progressBar;
    private TextView tvEmptyDb;
    private Button btnRetry;
    private RecyclerView rcViewList;
    private LinearLayoutManager rcVLLManager;
    private ListFragRVAdapter adapter;

    @InjectPresenter
    ListFragPresenter listFragPresenter;
    // [END Class Fields]


    // [START Class Callbacks]
    // [START Android UI Callbacks]
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        IToolbar toolbar = (IToolbar) getActivity();
        toolbar.onTitleChanged(getString(R.string.app_name));

        final View listFragView = inflater.inflate(R.layout.frag_list, container, false);

        // [START init ProgressBar]
        progressBar = listFragView.findViewById(R.id.frag_list_progress_bar);
        // [END init ProgressBar]

        // [START init TextView & Button Empty Database]
        tvEmptyDb = listFragView.findViewById(R.id.frag_list_tv_empty_db);
        btnRetry = listFragView.findViewById(R.id.frag_list_btn_retry);
        btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (InternetConnection.isOnline()) {
                    listFragPresenter.getData();
                } else {
                    Snackbar.make(listFragView,
                            getString(R.string.frag_list_no_internet_connection),
                            Snackbar.LENGTH_SHORT
                    ).show();
                }
            }
        });
        // [END init TextView & Button Empty Database]

        // [START init RecyclerView]
        rcViewList = listFragView.findViewById(R.id.frag_list_rc_view);
        rcVLLManager = new LinearLayoutManager(AppClass.getAppContext());
        rcViewList.setLayoutManager(rcVLLManager); // set scroll position
        // [END init RecyclerView]

        return listFragView;
    }

    @Override
    public void onStart() {
        super.onStart();
        restoreRVScrollPosition();
    }

    @Override
    public void onResume() {
        super.onResume();
        // [START Update Adapter after working with card on DescFrag]
        if (adapter != null) {
            listFragPresenter.updateData();
        }
        // [END Update Adapter after working with card on DescFrag]
    }

    @Override
    public void onPause() {
        super.onPause();
        int rcPosition = rcVLLManager.findLastCompletelyVisibleItemPosition();
        listFragPresenter.saveRecyclerScrollPosition(rcPosition);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_frag_list_search, menu);
        super.onCreateOptionsMenu(menu, inflater);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.menu_frag_list_search_item).getActionView();
        if (searchManager != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        }
        searchView.setMaxWidth(Integer.MAX_VALUE);

        // listening to search query text change
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
                adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                adapter.getFilter().filter(query);
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        // noinspection SimplifiableIfStatement
        if (id == R.id.menu_frag_list_search_item) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onTreeSelected(RModelTree tree) {
        AppLogs.show("tree name " + tree.getTreeName());
        IFragmentSwitch fragmentSwitch = (IFragmentSwitch) getActivity();
        DescFrag descFrag = new DescFrag();
        Bundle treeObject = new Bundle();
        treeObject.putString(MainActivity.BUNDLE_TREE_OBJECT_TAG, new Gson().toJson(tree));
        descFrag.setArguments(treeObject);

        fragmentSwitch.onFragmentSwitch(descFrag, MainActivity.FRAG_DESC_TAG);
    }
    // [END Android UI Callbacks]


    // [START Progressbar Callbacks]
    @Override
    public void showProgress() {
        if (progressBar != null) {
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideProgress() {
        if (progressBar != null) {
            progressBar.setVisibility(View.GONE);
        }
    }
    // [END Progressbar Callbacks]


    @Override
    public void showWidgetsForEmptyDatabase() {
        if (tvEmptyDb != null && btnRetry != null && rcViewList != null) {
            tvEmptyDb.setVisibility(View.VISIBLE);
            btnRetry.setVisibility(View.VISIBLE);
            rcViewList.setVisibility(View.GONE);
        }
    }

    @Override
    public void hideWidgetsForEmptyDatabase() {
        if (tvEmptyDb != null && btnRetry != null && rcViewList != null) {
            tvEmptyDb.setVisibility(View.GONE);
            btnRetry.setVisibility(View.GONE);
            rcViewList.setVisibility(View.VISIBLE);
        }
    }

    // [START RecyclerView Loading Callbacks]
    @Override
    public void showRecycler(List<RModelTree> treeList) {
        adapter = new ListFragRVAdapter(AppClass.getAppContext(), treeList, this, iOnButtonClickListener);
        rcViewList.setAdapter(adapter);
    }

    @Override
    public void updateAdapter(List<RModelTree> newTreeList) {
        TreeDiffUtilCallback treeDiffUtilCallback = new TreeDiffUtilCallback(adapter.getData(), newTreeList);
        DiffUtil.DiffResult productDiffResult = DiffUtil.calculateDiff(treeDiffUtilCallback);
        adapter.setData(newTreeList);
        productDiffResult.dispatchUpdatesTo(adapter);
    }

    private IOnButtonClickListener iOnButtonClickListener = new IOnButtonClickListener() {

        @Override
        public void onImageClicked(RModelTree tree) {
            IFragmentSwitch fragmentSwitch = (IFragmentSwitch) getActivity();
            DescFrag descFrag = new DescFrag();
            Bundle treeObject = new Bundle();
            treeObject.putString(MainActivity.BUNDLE_TREE_OBJECT_TAG, new Gson().toJson(tree));
            descFrag.setArguments(treeObject);

            fragmentSwitch.onFragmentSwitch(descFrag, MainActivity.FRAG_DESC_TAG);
        }

        @Override
        public void onLikeClicked(String treeName, boolean isLiked) {
            listFragPresenter.onLikeClicked(treeName, isLiked);
        }

        @Override
        public void onShareClicked(String shareText) {
            showShareDialog(shareText);
        }
    };
    // [END RecyclerView Loading Callbacks]

    // [END Class Callbacks]


    // [START Class Methods]
    private void showShareDialog(String shareText) {
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

    private void restoreRVScrollPosition() {
        int itemPosition = listFragPresenter.getRecyclerScrollPosition();
        rcVLLManager.scrollToPositionWithOffset(itemPosition, 4);
    }
    // [END Class Methods]
}
