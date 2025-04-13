package com.project.javanotesapp;

import com.project.javanotesapp.entity.NoteEntity;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SearchNotes {
    public List<NoteEntity> execute(List<NoteEntity> notes, String query) {
        if (query.isEmpty()) return notes;

        return notes.stream()
                .filter(n -> n.title.toLowerCase().contains(query.toLowerCase()))
                .sorted(Comparator.comparingLong(n -> n.created))
                .collect(Collectors.toList());
    }
}
