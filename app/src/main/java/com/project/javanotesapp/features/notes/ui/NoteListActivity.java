package com.project.javanotesapp.features.notes.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.project.javanotesapp.R;
import com.project.javanotesapp.adapters.NoteAdapter;
import com.project.javanotesapp.adapters.NoteViewHolder;
import com.project.javanotesapp.features.notes.local.NoteEntity;
import com.project.javanotesapp.features.notes.viewmodel.NoteListViewModel;

public class NoteListActivity extends AppCompatActivity implements NoteViewHolder.NoteClickListener {
    private NoteListViewModel viewModel;
    private RecyclerView recyclerView;
    private NoteAdapter adapter;
    private static final int EDIT_NOTE_REQUEST = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);

        recyclerView = findViewById(R.id.recyclerView);
        TextView emptyView = findViewById(R.id.empty_view);
        FloatingActionButton fabAddNote = findViewById(R.id.fab_add_note);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize adapter with click listener
        adapter = new NoteAdapter(this);
        recyclerView.setAdapter(adapter);

        viewModel = new ViewModelProvider(this).get(NoteListViewModel.class);
        viewModel.loadNotes(); // Load initial notes

        viewModel.getNotes().observe(this, notes -> {
            if (notes == null || notes.isEmpty()) {
                emptyView.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
            } else {
                emptyView.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                adapter.submitList(notes);
            }
        });

        // Handle "Add Note" button click
        fabAddNote.setOnClickListener(v -> showAddNoteDialog());
    }

    private void showAddNoteDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("New Note");

        // Inflate custom layout for the dialog
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_note, null);
        builder.setView(dialogView);

        EditText editTitle = dialogView.findViewById(R.id.edit_title);

        // Hide the content input field since we only want title input
        View contentLabel = dialogView.findViewById(R.id.content_label);
        EditText editContent = dialogView.findViewById(R.id.edit_content);
        contentLabel.setVisibility(View.GONE);
        editContent.setVisibility(View.GONE);

        builder.setPositiveButton("Save", (dialog, which) -> {
            String title = editTitle.getText().toString().trim();

            if (!title.isEmpty()) {
                try {
                    // Create the new note with empty content - content will be added in detail screen
                    NoteEntity newNote = new NoteEntity(title, "");

                    // Add the note using the ViewModel
                    viewModel.addNote(newNote);

                    // Show confirmation
                    Toast.makeText(this, "Note created successfully", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    // Handle any exceptions that might occur
                    Toast.makeText(this, "Error saving note: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(this, "Title cannot be empty", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void onNoteClicked(NoteEntity note) {
        // Make sure the ID is not null before using it
        if (note == null) {
            Toast.makeText(this, "Cannot open note: Note is null", Toast.LENGTH_SHORT).show();
            return;
        }

        // Navigate to NoteDetailActivity when a note is clicked
        Intent intent = new Intent(this, NoteDetailActivity.class);

        // Use a safe default value (-1) if ID is null
        long noteId = (note.getId() != null) ? note.getId() : -1L;
        intent.putExtra("note_id", noteId);
        intent.putExtra("note_title", note.getTitle());
        intent.putExtra("note_content", note.getContent());
        startActivityForResult(intent, EDIT_NOTE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == EDIT_NOTE_REQUEST && resultCode == RESULT_OK) {
            // Refresh notes list after editing
            viewModel.loadNotes();
        }
    }
}