package com.example.marlieske.marlieskepsetfinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class ListofSongsActivity extends AppCompatActivity {
    String result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listof_songs);
        Intent toListOfSongs = getIntent();
        result = toListOfSongs.getStringExtra("result");
        DisplaySongList();
    }

    public String returnresult(){
        return result;
    }

    public void DisplaySongList(){
        ListView LVItems = (ListView) findViewById(R.id.LVSongs);
        ListAdapter adapter = new ListAdapter(this, R.layout.foundsongs);
        LVItems.setAdapter(adapter);
    }
}
