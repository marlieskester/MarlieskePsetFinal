package com.example.marlieske.marlieskepsetfinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

public class ListofSongsActivity extends AppCompatActivity {
    String result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listof_songs);
        Log.d("oncreate", "listofsongs");
        Intent toListOfSongs = getIntent();
        result = toListOfSongs.getStringExtra("result");
        DisplaySongList();
    }

    public void DisplaySongList(){
        JSONExtractor ex = new JSONExtractor(result);
        ArrayList<Song> songs = ex.getSongs();
        ListView LVItems = (ListView) findViewById(R.id.LVSongs);
        ResultListAdapter adapter = new ResultListAdapter(this, R.layout.foundsongs, songs);
        LVItems.setAdapter(adapter);
    }
}
