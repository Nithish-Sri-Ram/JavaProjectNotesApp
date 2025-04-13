// NoteAdapter.java
package com.project.javanotesapp;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;

import com.project.javanotesapp.entity.NoteEntity;

import java.util.Collections;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteViewHolder> {
    private List<NoteEntity> notes = Collections.emptyList(); // Initialize with an empty list

    public void setNotes(List<NoteEntity> notes) {
        this.notes = notes;
        notifyDataSetChanged();
    }

    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new NoteViewHolder(inflater.inflate(R.layout.note_item, parent, false));
    }

    @Override
    public void onBindViewHolder(NoteViewHolder holder, int position) {
        holder.bind(notes.get(position));
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }
}
