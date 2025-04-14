package com.project.javanotesapp.features.notes.viewmodel;

import androidx.lifecycle.ViewModel;

import com.project.javanotesapp.features.notes.local.NoteDao;
import com.project.javanotesapp.features.notes.local.NoteEntity;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class NoteDetailViewModel extends ViewModel {

    private final Executor executor = Executors.newSingleThreadExecutor();
    private NoteDao noteDao; // Should be properly initialized via dependency injection

    // Default constructor required by ViewModelProvider
    public NoteDetailViewModel() {
        // Empty constructor
    }

    // This is for demonstration purposes - in a real app use dependency injection
    public void setNoteDao(NoteDao noteDao) {
        this.noteDao = noteDao;
    }

    public void updateNote(NoteEntity note) {
        executor.execute(() -> {
            if (noteDao != null) {
                if (note.getId() == null || note.getId() == -1L) {
                    // This is a new note, so insert it
                    noteDao.insertNote(note);
                    // Update the note ID from the database
                    long id = noteDao.getLastInsertedId();
                    note.setId(id);
                } else {
                    // This is an existing note, so update it
                    noteDao.updateNote(note);
                }
            } else {
                android.util.Log.e("NoteDetailViewModel", "noteDao is null in updateNote()");
            }
        });
    }
}