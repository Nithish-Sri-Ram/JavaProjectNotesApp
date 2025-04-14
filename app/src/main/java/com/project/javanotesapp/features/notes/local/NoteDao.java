package com.project.javanotesapp.features.notes.local;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NoteDao {
    @Query("SELECT * FROM noteEntity ORDER BY created DESC")
    List<NoteEntity> getAllNotes();

    @Query("SELECT * FROM noteEntity WHERE id = :id")
    NoteEntity getNoteById(long id);

    // Keep original method signature to avoid conflict
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertNote(NoteEntity note);

    // Add update method
    @Update
    void updateNote(NoteEntity note);

    @Delete
    void deleteNote(NoteEntity note);

    @Query("SELECT * FROM noteEntity WHERE title LIKE '%' || :query || '%' OR content LIKE '%' || :query || '%' ORDER BY created DESC")
    List<NoteEntity> searchNotes(String query);

    // Add method to get the last inserted ID
    @Query("SELECT last_insert_rowid()")
    long getLastInsertedId();
}