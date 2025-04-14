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
                    // Handle null IDs safely
                    if (oldItem.id == null || newItem.id == null) {
                        return oldItem == newItem;
                    }
                    return oldItem.id.equals(newItem.id);
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
        holder.bind(getItem(position));
    }

    // Method to set click listener after initialization
    public void setOnNoteClickListener(NoteViewHolder.NoteClickListener listener) {
        this.clickListener = listener;
    }
}