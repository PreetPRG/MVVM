package com.example.roommvvm.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Update;

import com.example.roommvvm.Entity.Note;
import com.example.roommvvm.Entity.NoteDao;
import com.example.roommvvm.Entity.NoteDatabase;

import java.util.List;

public class NoteRepository {
    private NoteDao noteDao;
    private LiveData<List<Note>> allNotes;

    public NoteRepository(Application application)
    {
        NoteDatabase database= NoteDatabase.getInstance(application);
        noteDao=database.noteDao();
        allNotes=noteDao.getAllNotes();
    }

    public void insert(Note note)
    {
        new InsertNoteAsyncTask(noteDao).execute(note);
    }



    public void update(Note note){
        new UpdateNoteAsyncTask(noteDao).execute(note);
    }

    public void delete(Note note){
        new DeleteNoteAsyncTask(noteDao).execute(note);
    }

    public void deleteAll()
    {
        new DeletesAllNotesAsyncTask(noteDao).execute();
    }
    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }

    private static class InsertNoteAsyncTask extends android.os.AsyncTask<Note, Void,Void>
    {
        private NoteDao noteDao;

        private InsertNoteAsyncTask(NoteDao noteDao)
        {
            this.noteDao=noteDao;
        }
        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.insert(notes[0]);
            return null;
        }
    }

    private static class UpdateNoteAsyncTask extends android.os.AsyncTask<Note, Void,Void>
    {
        private NoteDao noteDao;

        private UpdateNoteAsyncTask(NoteDao noteDao)
        {
            this.noteDao=noteDao;
        }
        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.update(notes[0]);
            return null;
        }
    }

    private static class DeleteNoteAsyncTask extends android.os.AsyncTask<Note, Void,Void>
    {
        private NoteDao noteDao;

        private DeleteNoteAsyncTask(NoteDao noteDao)
        {
            this.noteDao=noteDao;
        }
        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.delete(notes[0]);
            return null;
        }
    }

    private static class DeletesAllNotesAsyncTask extends android.os.AsyncTask<Void, Void,Void>
    {
        private NoteDao noteDao;

        private DeletesAllNotesAsyncTask(NoteDao noteDao)
        {
            this.noteDao=noteDao;
        }


        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.deleteAll();
            return null;
        }
    }
}
