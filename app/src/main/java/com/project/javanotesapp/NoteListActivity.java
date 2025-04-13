package com.project.javanotesapp;

import android.os.Bundle;

import com.project.javanotesapp.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.project.javanotesapp.model.NoteListViewModel;

public class NoteListActivity extends AppCompatActivity {
    private NoteListViewModel viewModel;
    private RecyclerView recyclerView;
    private NoteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new NoteAdapter();
        recyclerView.setAdapter(adapter);

        viewModel = new ViewModelProvider(this).get(NoteListViewModel.class);
        viewModel.getNotes().observe(this, notes -> {
            adapter.setNotes(notes);
        });
    }
}


