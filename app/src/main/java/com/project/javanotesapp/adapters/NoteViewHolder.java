package com.project.javanotesapp.adapters;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.javanotesapp.R;
import com.project.javanotesapp.features.notes.local.NoteEntity;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class NoteViewHolder extends RecyclerView.ViewHolder {
    private final TextView titleTextView;
    private final TextView contentTextView;
    private NoteClickListener clickListener;

    public NoteViewHolder(@NonNull View itemView) { // Modified constructor
        super(itemView);
        titleTextView = itemView.findViewById(R.id.text_title);
        contentTextView = itemView.findViewById(R.id.text_content);

        // Set click listener on the item view
        itemView.setOnClickListener(v -> {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION && clickListener != null) {
                clickListener.onNoteClicked((NoteEntity) itemView.getTag());
            }
        });
    }

    public void setClickListener(NoteClickListener listener) {
        this.clickListener = listener;
    }

    public void bind(NoteEntity note) {
        // Set the note as a tag on the item view for easy retrieval in click listener
        itemView.setTag(note);

        // Set the title
        titleTextView.setText(note.getTitle());

        // Trim the content to show only the first 50 characters
        String content = note.getContent();
        if (content == null || content.isEmpty()) {
            contentTextView.setVisibility(View.GONE);
        } else {
            contentTextView.setVisibility(View.VISIBLE);

            // Trim content to 50 characters and add ellipsis if longer
            if (content.length() > 50) {
                content = content.substring(0, 50) + "...";
            }

            contentTextView.setText(content);
        }

        TextView timestampTextView = itemView.findViewById(R.id.text_timestamp);
        if (note.getId() != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault());
            timestampTextView.setText(sdf.format(note.getTimestamp()));
            timestampTextView.setVisibility(View.VISIBLE);
        } else {
            timestampTextView.setVisibility(View.GONE);
        }
    }

    public interface NoteClickListener {
        void onNoteClicked(NoteEntity note);
    }
}
