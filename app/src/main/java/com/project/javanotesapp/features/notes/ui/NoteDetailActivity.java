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
import com.project.javanotesapp.database.NoteDatabase;
import com.project.javanotesapp.features.notes.local.NoteEntity;
import com.project.javanotesapp.features.notes.viewmodel.NoteDetailViewModel;

public class NoteDetailActivity extends AppCompatActivity {

    private EditText editTitle;
    private EditText editContent;
    private NoteDetailViewModel viewModel;
    private Long noteId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        android.util.Log.d("NoteDetailActivity", "onCreate started");
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.activity_note_detail);
        } catch (Exception e) {
            // Fallback to a simpler layout if there's an inflation error
            setContentView(R.layout.activity_note_detail_fallback);
            Toast.makeText(this, "Error loading layout: " + e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }


        // Set up toolbar
        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Edit Note");

        // Initialize views
        editTitle = findViewById(R.id.edit_note_title);
        editContent = findViewById(R.id.edit_note_content);
        FloatingActionButton fabSave = findViewById(R.id.fab_save_note);

        // Initialize ViewModel with proper error handling
        try {
            viewModel = new ViewModelProvider(this).get(NoteDetailViewModel.class);

            // Set the DAO - this is critical
            NoteDatabase database = NoteDatabase.getInstance(this);
            viewModel.setNoteDao(database.noteDao());
        } catch (Exception e) {
            Toast.makeText(this, "Error initializing: " + e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

        // Get data from intent with null checks
        Intent intent = getIntent();
        if (intent != null) {
            noteId = intent.getLongExtra("note_id", -1);
            String title = intent.getStringExtra("note_title");
            String content = intent.getStringExtra("note_content");

            // Set initial data with null checks
            editTitle.setText(title != null ? title : "");
            editContent.setText(content != null ? content : "");
        }

        // Set save button click listener
        fabSave.setOnClickListener(v -> saveNote());
        android.util.Log.d("NoteDetailActivity", "onCreate completed");
    }


    private void saveNote() {
        try {
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

            // Check if viewModel is properly initialized
            if (viewModel == null) {
                Toast.makeText(this, "Error: ViewModel not initialized", Toast.LENGTH_SHORT).show();
                return;
            }

            // Save the note
            viewModel.updateNote(updatedNote);

            Toast.makeText(this, "Note saved successfully", Toast.LENGTH_SHORT).show();

            // Return result and finish
            Intent resultIntent = new Intent();
            resultIntent.putExtra("updated_note", true);
            setResult(RESULT_OK, resultIntent);
            finish();
        } catch (Exception e) {
            Toast.makeText(this, "Error saving note: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
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