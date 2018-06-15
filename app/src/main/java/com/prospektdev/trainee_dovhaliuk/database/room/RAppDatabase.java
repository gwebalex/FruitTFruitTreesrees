package com.prospektdev.trainee_dovhaliuk.database.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * @author Oleksandr Dovhaliuk
 */

@Database(entities = {RModelTree.class}, version = 1)
public abstract class RAppDatabase extends RoomDatabase {

    public abstract RModelTreeDAO getModelTreeDAO();
}
