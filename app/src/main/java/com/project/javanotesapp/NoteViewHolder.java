// NoteViewHolder.java
package com.project.javanotesapp;

import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.project.javanotesapp.entity.NoteEntity;

public class NoteViewHolder extends RecyclerView.ViewHolder {
    private final TextView titleTextView;
    private final TextView contentTextView;

    public NoteViewHolder(View itemView) {
        super(itemView);
        titleTextView = itemView.findViewById(R.id.text_title);
        contentTextView = itemView.findViewById(R.id.text_content);
    }

    public void bind(NoteEntity note) {
        titleTextView.setText(note.getTitle());
        contentTextView.setText(note.getContent());
    }
}
