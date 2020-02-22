package com.example.roommvvm.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.roommvvm.Entity.Note;
import com.example.roommvvm.Repository.NoteRepository;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {
    private NoteRepository repository;
    private LiveData<List<Note>> allNotes;

   // public NoteViewModel(){}

    public NoteViewModel(@NonNull Application application) {
        super(application);
        repository=new NoteRepository(application);
        allNotes=repository.getAllNotes();
    }

    public void Insert(Note note)
    {
        repository.insert(note);
    }

    public void update(Note note)
    {
        repository.update(note);
    }

    public void delete(Note note)
    {
        repository.delete(note);
    }

    public void deleteAll()
    {
        repository.deleteAll();
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }


}
