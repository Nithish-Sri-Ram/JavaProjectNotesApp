package com.project.javanotesapp.features.search;

import com.project.javanotesapp.features.notes.local.NoteEntity;

import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class SearchNotes {
    public List<NoteEntity> execute(List<NoteEntity> notes, String query) {
        if (query.isEmpty()) return notes;

        return notes.stream()
                .filter(n -> n.title.toLowerCase(Locale.getDefault())
                        .contains(query.toLowerCase(Locale.getDefault())))
                .sorted(Comparator.comparingLong(n -> n.created))
                .collect(Collectors.toList());
    }
}
