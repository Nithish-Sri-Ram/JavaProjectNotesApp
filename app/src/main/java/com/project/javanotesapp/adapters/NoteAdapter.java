package com.project.javanotesapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.project.javanotesapp.R;
import com.project.javanotesapp.features.notes.local.NoteEntity;

public class NoteAdapter extends ListAdapter<NoteEntity, NoteViewHolder> {
    private NoteViewHolder.NoteClickListener clickListener;

    public NoteAdapter() {
        super(DIFF_CALLBACK);
    }

    // Constructor that accepts click listener
    public NoteAdapter(NoteViewHolder.NoteClickListener listener) {
        super(DIFF_CALLBACK);
        this.clickListener = listener;
    }

    private static final DiffUtil.ItemCallback<NoteEntity> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<NoteEntity>() {
                @Override
                public boolean areItemsTheSame(NoteEntity oldItem, NoteEntity newItem) {
                    // Handle null IDs properly
                    if (oldItem == null || newItem == null) {
                        return oldItem == newItem;
                    }
                    if (oldItem.getId() == null || newItem.getId() == null) {
                        return false;
                    }
                    return oldItem.getId().equals(newItem.getId());
                }

                @Override
                public boolean areContentsTheSame(NoteEntity oldItem, NoteEntity newItem) {
                    return oldItem.equals(newItem);
                }
            };

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_item, parent, false);
        NoteViewHolder holder = new NoteViewHolder(view);
        holder.setClickListener(clickListener); // Now works with the new method
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        try {
            NoteEntity note = getItem(position);

            // Trim content to the first 50 characters with ellipsis
            String trimmedContent = note.getContent();
            if (trimmedContent != null && trimmedContent.length() > 50) {
                trimmedContent = trimmedContent.substring(0, 50) + "...";
                note.setContent(trimmedContent); // Update content in the entity for consistency
            }

            holder.bind(note);

            holder.itemView.setOnClickListener(v -> {
                if (clickListener != null) {
                    clickListener.onNoteClicked(note);
                }
            });
        } catch (Exception e) {
            android.util.Log.e("NoteAdapter", "Error binding view holder: " + e.getMessage());
            e.printStackTrace();
        }
    }




    // Method to set click listener after initialization
    public void setOnNoteClickListener(NoteViewHolder.NoteClickListener listener) {
        this.clickListener = listener;
    }
}