package com.project.javanotesapp.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.project.javanotesapp.dao.NoteDao;
import com.project.javanotesapp.entity.NoteEntity;
import com.project.javanotesapp.repository.NoteRepository;

import java.util.List;

public class NoteListViewModel extends ViewModel {
    private final NoteRepository repository;
    private final MutableLiveData<String> searchQuery = new MutableLiveData<>();

    public NoteListViewModel(NoteRepository repository) {
        this.repository = repository;
    }

    public LiveData<List<NoteEntity>> getNotes() {
        return Transformations.switchMap(searchQuery, repository::searchNotes);
    }

    public void setSearchQuery(String query) {
        searchQuery.setValue(query);
    }
}
