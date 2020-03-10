package com.example.mvvm1.Models;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Image.class},version = 1,exportSchema = false)
public abstract  class ImageDatabase extends RoomDatabase {

    private static ImageDatabase instance;
    public abstract ImageDAO imagedao();
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    public static synchronized ImageDatabase getInstance(Context context)
    {
        if(instance == null)
        {
            Builder<ImageDatabase> image_database = Room.databaseBuilder(context.getApplicationContext(),ImageDatabase.class,"image_database");
            image_database.fallbackToDestructiveMigration();
            instance = image_database.build();
            Log.d("ImageDatabase","Image Database Class called");
        }
        return instance;
    }
}


