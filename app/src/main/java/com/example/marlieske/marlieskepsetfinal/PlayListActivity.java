package com.example.marlieske.marlieskepsetfinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.net.URL;

public class PlayListActivity extends AppCompatActivity {
    String addedSong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_list);
        Intent selectedSong = getIntent();
        addedSong = selectedSong.getStringExtra("song");
    }

    public class Song{
        String title;
        String artist;
        URL albumimage;
        URL albuminfo;
    }

}
