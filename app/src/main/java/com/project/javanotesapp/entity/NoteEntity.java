package com.project.javanotesapp.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "noteEntity")
public class NoteEntity {
    @PrimaryKey(autoGenerate = true)
    public Long id;
    public String title;
    public String content;
    public Long colorHex;
    public Long created;
}
