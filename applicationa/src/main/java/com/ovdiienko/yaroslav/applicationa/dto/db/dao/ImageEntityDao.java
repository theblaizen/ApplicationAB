package com.ovdiienko.yaroslav.applicationa.dto.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.database.Cursor;

import com.ovdiienko.yaroslav.applicationa.dto.db.model.ImageEntity;

import java.util.List;

@Dao
public interface ImageEntityDao {
    @Query("SELECT COUNT(*) FROM image")
    int count();

    @Insert
    long insert(ImageEntity imageEntity);

    @Insert
    long[] insertAll(ImageEntity[] cheeses);

    @Query("SELECT * FROM image ORDER BY datetime(dateTime) DESC")
    LiveData<List<ImageEntity>> selectAllByDate();

    @Query("SELECT * FROM image ORDER BY status")
    LiveData<List<ImageEntity>> selectAllByStatus();

    @Query("SELECT * FROM image")
    LiveData<List<ImageEntity>> selectAllLikeObservable();

    @Query("SELECT * FROM image")
    Cursor selectAll();

    @Query("SELECT * FROM image WHERE image.id = :id")
    Cursor selectById(long id);

}
