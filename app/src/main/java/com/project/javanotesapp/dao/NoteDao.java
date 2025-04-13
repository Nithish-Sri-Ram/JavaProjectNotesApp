package com.project.javanotesapp.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.project.javanotesapp.entity.NoteEntity;

import java.util.List;

@Dao
public interface NoteDao {
    @Query("SELECT * FROM noteEntity")
    List<NoteEntity> getAllNotes();

    @Query("SELECT * FROM noteEntity WHERE id = :id")
    NoteEntity getNoteById(long id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertNote(NoteEntity note);

    @Delete
    void deleteNote(NoteEntity note);

    @Query("SELECT * FROM noteEntity WHERE title LIKE '%' || :query || '%' OR content LIKE '%' || :query || '%'")
    List<NoteEntity> searchNotes(String query);
}
