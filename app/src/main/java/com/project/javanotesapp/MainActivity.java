// MainActivity.java
package com.project.javanotesapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import androidx.core.splashscreen.SplashScreen;

import androidx.activity.ComponentActivity;

public class MainActivity extends ComponentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SplashScreen.installSplashScreen(this);

        // Delay navigation
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            startActivity(new Intent(this, NoteListActivity.class));
            finish();
        }, 2000);
    }
}