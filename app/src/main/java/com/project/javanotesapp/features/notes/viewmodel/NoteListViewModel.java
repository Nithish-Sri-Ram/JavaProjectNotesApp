package com.project.javanotesapp.features.notes.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.project.javanotesapp.features.notes.local.NoteDao;
import com.project.javanotesapp.features.notes.local.NoteEntity;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class NoteListViewModel extends ViewModel {

    private final MutableLiveData<List<NoteEntity>> notes = new MutableLiveData<>();
    private final Executor executor = Executors.newSingleThreadExecutor();
    private NoteDao noteDao; // This should be properly initialized via dependency injection or App class

    // This is for demonstration purposes - in a real app use dependency injection
    public void setNoteDao(NoteDao noteDao) {
        this.noteDao = noteDao;
    }

    public LiveData<List<NoteEntity>> getNotes() {
        return notes;
    }

    public void loadNotes() {
        executor.execute(() -> {
            if (noteDao != null) {
                List<NoteEntity> noteList = noteDao.getAllNotes();
                notes.postValue(noteList);
            }
        });
    }

    public void addNote(NoteEntity note) {
        executor.execute(() -> {
            if (noteDao != null) {
                // Insert the note
                noteDao.insertNote(note);

                // Get the last inserted ID and set it on the note
                long lastId = noteDao.getLastInsertedId();
                note.setId(lastId);

                // Reload notes to update the UI
                loadNotes();
            }
        });
    }
}