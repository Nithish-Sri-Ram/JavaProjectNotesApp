package com.project.javanotesapp.di;

import android.app.Application;

import androidx.room.Room;

import com.project.javanotesapp.database.NoteDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {
    @Provides
    @Singleton
    public NoteDatabase provideDatabase(Application app) {
        return Room.databaseBuilder(app, NoteDatabase.class, "notes.db")
                .build();
    }
}
