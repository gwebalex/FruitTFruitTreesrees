package com.prospektdev.trainee_dovhaliuk.utils;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;

import com.google.firebase.database.FirebaseDatabase;
import com.prospektdev.trainee_dovhaliuk.database.room.RAppDatabase;

/**
 * @author Oleksandr Dovhaliuk
 */
public class AppClass extends Application {

    // [START Class Fields]
    private static Context appContext;

    private static RAppDatabase rAppDatabase;
    private static FirebaseDatabase firebaseDB;
    // [END Class Fields]

    // [START Class Callbacks]
    @Override
    public void onCreate() {
        appContext = this;
        super.onCreate();

        // [START Room Database]
        rAppDatabase = Room.databaseBuilder(this,
                RAppDatabase.class, "trees-database")
                .allowMainThreadQueries() // only for test task 'Prospekt Dev'
                .build();
        // [END Room Database]


        // [START Firebase Realtime Database]
        firebaseDB = FirebaseDatabase.getInstance();
        firebaseDB.setPersistenceEnabled(false);
        // [END Firebase Realtime Database]
    }
    // [END Class Callbacks]


    // [START Class Methods]
    public static Context getAppContext() {
        return appContext;
    }

    public static RAppDatabase getRoomInstanse() {
        return rAppDatabase;
    }

    public static FirebaseDatabase getFirebaseInstanse() {
        return firebaseDB;
    }
    // [END Class Methods]


}
