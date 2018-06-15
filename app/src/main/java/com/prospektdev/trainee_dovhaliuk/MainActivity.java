package com.prospektdev.trainee_dovhaliuk;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.prospektdev.trainee_dovhaliuk.list.ListFrag;

public class MainActivity extends AppCompatActivity
        implements IFragmentSwitch, IToolbar {

    // [START Class Fields]
    public static final String BUNDLE_TREE_OBJECT_TAG = "treeObject";
    public static final String FRAG_LIST_TAG = "listFrag";
    public static final String FRAG_DESC_TAG = "aboutFrag";
    // [END Class Fields]


    // [START Class Callbacks]
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);

        // init Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // show new List Fragment if onCreate called not by device rotation
        if (savedInstanceState == null) {
            onFragmentSwitch(new ListFrag(), FRAG_LIST_TAG);
        }
    }

    @Override
    public void onBackPressed() {
        // hide keyboard if it was shown
        if (keyboardIsShowing(getCurrentFocus())) {
            hideKeyboard(getCurrentFocus());
        } else {
            super.onBackPressed();
        }
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
    // [END Class Callbacks]


    // [START Class Methods]
    private static boolean keyboardIsShowing(View view) {
        if (view == null) {
            return false;
        }
        InputMethodManager inputManager = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        assert inputManager != null;
        return inputManager.isActive(view);
    }

    private static void hideKeyboard(View view) {
        if (view == null) {
            return;
        }
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null && !imm.isActive()) {
            return;
        }
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
    // [END Class Methods]

}
