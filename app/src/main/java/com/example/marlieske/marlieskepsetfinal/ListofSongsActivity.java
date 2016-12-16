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

    /** Connects Arralylist to adapter*/
    public void DisplaySongList(){
        JSONExtractor ex = new JSONExtractor(result);
        ArrayList<Song> songs = ex.getSongs();
        ListView LVItems = (ListView) findViewById(R.id.LVSongs);
        ResultListAdapter adapter = new ResultListAdapter(this, R.layout.foundsongs, songs, "ListofSongs");
        LVItems.setAdapter(adapter);
    }

    /** if activity is killed, save current state */
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("result", result);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        String result = savedInstanceState.getString("result");

    }
}
