// MainActivity.java
package com.project.javanotesapp;

import android.content.Intent;
import android.os.Bundle;
import androidx.activity.ComponentActivity;

public class MainActivity extends ComponentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Launch NoteListActivity immediately
        Intent intent = new Intent(this, NoteListActivity.class);
        startActivity(intent);
        finish();
    }
}
