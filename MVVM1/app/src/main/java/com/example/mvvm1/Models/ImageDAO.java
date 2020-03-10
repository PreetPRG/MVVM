package com.example.mvvm1.Models;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ImageDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void save(List<Image> image);

@Query("Select * from image_table")
    LiveData<List<Image>> getAllImages();
}
