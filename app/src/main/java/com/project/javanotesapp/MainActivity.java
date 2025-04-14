package com.project.javanotesapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.activity.ComponentActivity;
import androidx.core.splashscreen.SplashScreen;

import com.project.javanotesapp.features.notes.ui.NoteListActivity;

public class MainActivity extends ComponentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Install SplashScreen before calling super.onCreate()
        SplashScreen splashScreen = SplashScreen.installSplashScreen(this);

        super.onCreate(savedInstanceState);

        // Delay navigation to NoteListActivity for 2 seconds
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            Intent intent = new Intent(MainActivity.this, NoteListActivity.class);
            startActivity(intent);
            finish(); // Close MainActivity so it doesn't remain in the back stack
        }, 2000); // 2000 milliseconds = 2 seconds
    }
}
