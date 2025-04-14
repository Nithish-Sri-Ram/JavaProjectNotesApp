package com.project.javanotesapp.features.notes.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.project.javanotesapp.R;
import com.project.javanotesapp.features.notes.local.NoteEntity;
import com.project.javanotesapp.features.notes.viewmodel.NoteDetailViewModel;

public class NoteDetailActivity extends AppCompatActivity {

    private EditText editTitle;
    private EditText editContent;
    private NoteDetailViewModel viewModel;
    private Long noteId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);

        // Set up toolbar
        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Edit Note");

        // Initialize views
        editTitle = findViewById(R.id.edit_note_title);
        editContent = findViewById(R.id.edit_note_content);
        FloatingActionButton fabSave = findViewById(R.id.fab_save_note);

        // Initialize ViewModel
        viewModel = new ViewModelProvider(this).get(NoteDetailViewModel.class);

        // Get data from intent
        Intent intent = getIntent();
        if (intent != null) {
            noteId = intent.getLongExtra("note_id", -1);
            String title = intent.getStringExtra("note_title");
            String content = intent.getStringExtra("note_content");

            // Set initial data
            editTitle.setText(title);
            editContent.setText(content);
        }

        // Set save button click listener
        fabSave.setOnClickListener(v -> saveNote());
    }

    private void saveNote() {
        String title = editTitle.getText().toString().trim();
        String content = editContent.getText().toString().trim();

        if (title.isEmpty()) {
            Toast.makeText(this, "Title cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        // Update the note
        NoteEntity updatedNote = new NoteEntity(title, content);
        if (noteId != -1) {
            updatedNote.setId(noteId);
        }

        // Save the note
        viewModel.updateNote(updatedNote);

        Toast.makeText(this, "Note saved successfully", Toast.LENGTH_SHORT).show();

        // Return result and finish
        Intent resultIntent = new Intent();
        resultIntent.putExtra("updated_note", true);
        setResult(RESULT_OK, resultIntent);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            // Handle back button in app bar
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}