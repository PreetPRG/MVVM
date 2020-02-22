package com.example.roommvvm.Entity;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// use for generating instance of Database.
// It will be used by repository class.
@Database( entities = {Note.class}, version = 1,exportSchema = false)
public abstract class NoteDatabase extends RoomDatabase {
    private static NoteDatabase instance;

    public abstract NoteDao noteDao();

    private static final int NUMBER_OF_THREADS = 4;

    //We've created an ExecutorService with a fixed thread pool that you will use to run database operations asynchronously on a background thread.
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

// Use of Synchronized as multiple thread might try to build multiple Database Instance and we want single database
//returns Instance of Database
    public static synchronized NoteDatabase getInstance(Context context){
        if(instance == null)
        {
            Builder<NoteDatabase> note_database = Room.databaseBuilder(context.getApplicationContext(),
                    NoteDatabase.class, "note_database");
            note_database.fallbackToDestructiveMigration();
            note_database.addCallback(roomCallback);
            instance= note_database
                    .build();
        }
        return instance;
    }
    private static RoomDatabase.Callback roomCallback =new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void,Void,Void>{
        private NoteDao noteDao;
        private PopulateDbAsyncTask(NoteDatabase db)
        {
            this.noteDao=db.noteDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.insert(new Note("Title 1","Description 1", 1));
            noteDao.insert(new Note("Title 2","Description 2", 2));
            return null;
        }
    }
}
