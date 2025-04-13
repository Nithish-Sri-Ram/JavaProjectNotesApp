// MainActivity.java
package com.project.javanotesapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.activity.ComponentActivity;


public class MainActivity extends ComponentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content view to the dummy screen
        setContentView(R.layout.activity_main);

        // Delay launching NoteListActivity for 2 seconds (2000 milliseconds)
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(MainActivity.this, NoteListActivity.class);
            startActivity(intent);
            finish();
        }, 2000); // Adjust delay as needed
    }
}
