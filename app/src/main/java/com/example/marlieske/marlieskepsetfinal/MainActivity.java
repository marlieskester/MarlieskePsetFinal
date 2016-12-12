package com.example.marlieske.marlieskepsetfinal;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * MainActivity made by Marlieske Doorn
 * Activity is called from login or signup activity. Either passes keyword to Asynctask or loads playlist.
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // load songs based on keyword search
    public void loadSongs(View view) {
        // retrieve keyword
        EditText ETKeyWord = (EditText) findViewById(R.id.ETKeyWord);
        String Keyword = ETKeyWord.getText().toString();

        // if empty, notify user
        if (Keyword.equals("")) {
            Toast.makeText(this, "Please enter Keyword", Toast.LENGTH_SHORT).show();
        }

        //else load asynctask with provided keyword
        else {
            AsyncTask songAsyncTask = new SearchAsyncTask(this);
            songAsyncTask.execute(Keyword);
        }
    }

    //  or on buttonclick go to playlist
    public void toPlayList(View view) {
        Intent toPlayLisy = new Intent(this, PlayListActivity.class);
        startActivity(toPlayLisy);
    }

}
