package com.project.javanotesapp.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.project.javanotesapp.entity.NoteEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class NoteListViewModel extends ViewModel {
    private final MutableLiveData<List<NoteEntity>> notes = new MutableLiveData<>(Collections.emptyList());

    public LiveData<List<NoteEntity>> getNotes() {
        return notes;
    }

    // Load notes from a data source (e.g., database)
    public void loadNotes() {
        // Replace with actual data loading logic
        List<NoteEntity> dummyNotes = Arrays.asList(
                new NoteEntity("Note 1", "Content 1"),
                new NoteEntity("Note 2", "Content 2")
        );

        for (NoteEntity note : dummyNotes) {
            note.colorHex = 0xFF000000L; // Example color
            note.created = System.currentTimeMillis();
        }

        notes.setValue(dummyNotes);
    }
}
