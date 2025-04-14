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
        holder.setClickListener(clickListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        try {
            NoteEntity note = getItem(position);
            holder.bind(note);

            // Add this explicit click handler with error handling
            holder.itemView.setOnClickListener(v -> {
                try {
                    if (clickListener != null) {
                        clickListener.onNoteClicked(note);
                    }
                } catch (Exception e) {
                    android.util.Log.e("NoteAdapter", "Error in click listener: " + e.getMessage());
                    e.printStackTrace();
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