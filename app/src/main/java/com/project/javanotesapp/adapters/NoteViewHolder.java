package com.project.javanotesapp.adapters;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.javanotesapp.R;
import com.project.javanotesapp.features.notes.local.NoteEntity;

public class NoteViewHolder extends RecyclerView.ViewHolder {
    private final TextView titleTextView;
    private final TextView contentTextView;
    private NoteClickListener listener;

    public interface NoteClickListener {
        void onNoteClicked(NoteEntity note);
    }

    public NoteViewHolder(@NonNull View itemView) {
        super(itemView);
        titleTextView = itemView.findViewById(R.id.text_title);
        contentTextView = itemView.findViewById(R.id.text_content);
    }

    public void setClickListener(NoteClickListener listener) {
        this.listener = listener;
    }

    public void bind(NoteEntity note) {
        if (note != null) {
            titleTextView.setText(note.getTitle() != null ? note.getTitle() : "");

            // Show a preview of content or a placeholder
            String contentPreview = note.getContent();
            if (contentPreview == null || contentPreview.isEmpty()) {
                contentPreview = "No content";
            } else if (contentPreview.length() > 50) {
                contentPreview = contentPreview.substring(0, 50) + "...";
            }
            contentTextView.setText(contentPreview);
        } else {
            titleTextView.setText("");
            contentTextView.setText("No content");
        }
    }
}