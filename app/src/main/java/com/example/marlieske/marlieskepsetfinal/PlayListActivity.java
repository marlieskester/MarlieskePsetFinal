package com.example.marlieske.marlieskepsetfinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.net.URL;
import java.util.ArrayList;

public class PlayListActivity extends AppCompatActivity {
    DatabaseManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_list);
        manager.ReadList();
        DisplaySongList();
    }


    public void DisplaySongList(){
        ArrayList songs = manager.ReturnSongs();
        ListView LVItems = (ListView) findViewById(R.id.LVPlaylist);
        ResultListAdapter adapter = new ResultListAdapter(this, R.layout.foundsongs, songs);
        LVItems.setAdapter(adapter);
    }
}