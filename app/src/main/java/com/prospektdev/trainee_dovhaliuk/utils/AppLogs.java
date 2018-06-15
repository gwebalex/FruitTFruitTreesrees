package com.prospektdev.trainee_dovhaliuk.utils;

import android.util.Log;

import com.prospektdev.trainee_dovhaliuk.BuildConfig;

/**
 * @author Oleksandr Dovhaliuk
 */
public class AppLogs {

    public AppLogs() {
    }

    public static void show(String logMsg) {
        if (BuildConfig.DEBUG) {
            Log.d("myLogs", logMsg);
        }
    }
}
