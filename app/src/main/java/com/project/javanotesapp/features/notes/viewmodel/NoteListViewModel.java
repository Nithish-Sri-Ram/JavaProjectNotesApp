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

    // In loadNotes() method of NoteListViewModel
    public void loadNotes() {
        executor.execute(() -> {
            if (noteDao != null) {
                List<NoteEntity> noteList = noteDao.getAllNotes();
                android.util.Log.d("NoteListViewModel", "Loaded " + noteList.size() + " notes");
                notes.postValue(noteList);
            } else {
                android.util.Log.e("NoteListViewModel", "noteDao is null in loadNotes()");
            }
        });
    }

    public void addNote(NoteEntity note) {
        executor.execute(() -> {
            if (noteDao != null) {
                // Insert the note
                noteDao.insertNote(note);

                // Reload notes to update the UI
                loadNotes();
            } else {
                // For debugging: log when noteDao is null
                android.util.Log.e("NoteListViewModel", "noteDao is null");
            }
        });
    }
}