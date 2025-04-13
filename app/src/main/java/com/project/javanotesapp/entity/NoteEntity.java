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

    public NoteEntity(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public NoteEntity() {
    }

    public String getTitle() { return title; }
    public String getContent() { return content; }
    public void setTitle(String title) { this.title = title; }
    public void setContent(String content) { this.content = content; }
}
