package com.prospektdev.trainee_dovhaliuk;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.prospektdev.trainee_dovhaliuk.list.ListFrag;
import com.prospektdev.trainee_dovhaliuk.utils.AppLogs;

public class MainActivity extends AppCompatActivity implements IFragmentSwitch, IToolbar {

    public static final String BUNDLE_TREE_OBJECT_TAG = "treeObject";
    public static final String FRAG_LIST_TAG = "listFrag";
    public static final String FRAG_DESC_TAG = "aboutFrag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);

        // init Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (savedInstanceState == null) {
            onFragmentSwitch(new ListFrag(), FRAG_LIST_TAG);
        }
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        if (getSupportActionBar() != null && getFragmentManager().getBackStackEntryCount() > 0) {
//            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        }
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                AppLogs.show("BackStackEntryCount: " + getFragmentManager().getBackStackEntryCount());
                if (getSupportActionBar() != null && getFragmentManager().getBackStackEntryCount() > 0) {
                    onBackPressed();
                } else {
                    getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                }
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentSwitch(Fragment fragment, String fragTag) {
        FragmentManager fragManager = getFragmentManager();
        FragmentTransaction fragTransaction = fragManager.beginTransaction();

        fragTransaction
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(R.id.act_main_container, fragment, fragTag);
        if (!fragTag.equals(FRAG_LIST_TAG)) {
            fragTransaction.addToBackStack(fragTag);
        }

        fragTransaction.commit();
    }

    @Override
    public void onTitleChanged(String title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }
}
