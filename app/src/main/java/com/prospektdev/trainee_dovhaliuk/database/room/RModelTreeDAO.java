package com.prospektdev.trainee_dovhaliuk.database.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Room Data Access Object
 * @author Oleksandr Dovhaliuk
 */

@Dao
public interface RModelTreeDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(RModelTree tree);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertList(List<RModelTree> treeList);

    @Query("UPDATE RModelTree SET treeLike = :isLiked  WHERE treeName = :treeName")
    void update(String treeName, boolean isLiked);

    @Delete
    void delete(RModelTree tree);

    @Query("SELECT * FROM rmodeltree")
    List<RModelTree> getAllTree();

    // For future use
    @Query("SELECT * FROM rmodeltree WHERE treeName LIKE :search")
    List<RModelTree> getAllTreeWithTreeName(String search); // search = "%myquery%";
}
