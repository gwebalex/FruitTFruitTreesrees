package com.prospektdev.trainee_dovhaliuk.utils.manager;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.prospektdev.trainee_dovhaliuk.utils.AppClass;

/**
 * @author Oleksandr Dovhaliuk
 */
public class InternetConnection {

    public static boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager)
                AppClass.getAppContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo netInfo = null;
        if (cm != null) {
            netInfo = cm.getActiveNetworkInfo();
        }
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}
