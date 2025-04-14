package com.project.javanotesapp.entity;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName = "noteEntity")
public class NoteEntity {
    @PrimaryKey(autoGenerate = true)
    public Long id;
    public String title;
    public String content;
    public Long colorHex;
    public Long created;

    @Ignore // Tell Room to ignore this constructor
    public NoteEntity(String title, String content) {
        // No ID assignment - Room will autoGenerate it
        this.title = title;
        this.content = content;
        this.colorHex = 0xFF000000L; // Default black color
        this.created = System.currentTimeMillis();
    }

    // Default no-arg constructor (used by Room)
    public NoteEntity() {
        // Leave empty for Room
    }

    public String getTitle() { return title; }
    public String getContent() { return content; }
    public void setTitle(String title) { this.title = title; }
    public void setContent(String content) { this.content = content; }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NoteEntity that = (NoteEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(title, that.title) &&
                Objects.equals(content, that.content) &&
                Objects.equals(colorHex, that.colorHex) &&
                Objects.equals(created, that.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, content, colorHex, created);
    }
}