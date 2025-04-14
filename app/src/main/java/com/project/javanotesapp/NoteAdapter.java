package com.project.javanotesapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.javanotesapp.entity.NoteEntity;


import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

public class NoteAdapter extends ListAdapter<NoteEntity, NoteViewHolder> {
    public NoteAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<NoteEntity> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<>() {
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

    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_item, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NoteViewHolder holder, int position) {
        holder.bind(getItem(position));
    }
}