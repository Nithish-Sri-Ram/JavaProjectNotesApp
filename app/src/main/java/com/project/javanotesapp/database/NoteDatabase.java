package com.project.javanotesapp.database;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.project.javanotesapp.dao.NoteDao;
import com.project.javanotesapp.entity.NoteEntity;

@Database(entities = {NoteEntity.class}, version = 1, exportSchema = false)
public abstract class NoteDatabase extends RoomDatabase {
    public abstract NoteDao noteDao();
}
