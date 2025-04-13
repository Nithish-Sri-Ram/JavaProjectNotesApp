package com.project.javanotesapp.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.project.javanotesapp.dao.NoteDao;
import com.project.javanotesapp.entity.NoteEntity;

import java.util.List;
import java.util.concurrent.Executor;

public class NoteRepository {
    private final NoteDao noteDao;
    private final Executor executor;

    public NoteRepository(NoteDao noteDao, Executor executor) {
        this.noteDao = noteDao;
        this.executor = executor;
    }

    public LiveData<List<NoteEntity>> getAllNotes() {
        MutableLiveData<List<NoteEntity>> liveData = new MutableLiveData<>();
        executor.execute(() -> liveData.postValue(noteDao.getAllNotes()));
        return liveData;
    }

    public LiveData<List<NoteEntity>> searchNotes(String query) {
        MutableLiveData<List<NoteEntity>> liveData = new MutableLiveData<>();
        executor.execute(() -> liveData.postValue(noteDao.searchNotes(query)));
        return liveData;
    }
}
