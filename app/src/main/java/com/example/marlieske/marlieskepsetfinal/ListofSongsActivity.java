package com.example.marlieske.marlieskepsetfinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

/***
 * ListofSongsActivity made by Marlieske Doorn
 * Displays list of songs found by the query.
 * Activity is called from Asynctask OnpostExecute, and passes string on to ResultListAdapter
 */

public class ListofSongsActivity extends AppCompatActivity {
    String result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listof_songs);
        // get list of songs
        if (savedInstanceState != null){
            result = savedInstanceState.getString("result");
        }
        else {
            Intent toListOfSongs = getIntent();
            result = toListOfSongs.getStringExtra("result");
        }
        DisplaySongList();
    }

    public void DisplaySongList(){
        // extract songs
        JSONExtractor ex = new JSONExtractor(result);
        ArrayList<Song> songs = ex.getSongs();
        // Load listview
        ListView LVItems = (ListView) findViewById(R.id.LVSongs);
        ResultListAdapter adapter = new ResultListAdapter(this, R.layout.foundsongs, songs);
        LVItems.setAdapter(adapter);
    }

    // if activity is killed, save current state
    public void onSaveInstanceState(Bundle savedInstanceState){
        savedInstanceState.putString("result", result);
        super.onSaveInstanceState(savedInstanceState);
    }
}
