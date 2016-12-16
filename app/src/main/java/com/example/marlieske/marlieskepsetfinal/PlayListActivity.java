package com.example.marlieske.marlieskepsetfinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;

/**
 * PlayListactivity made by Marlieske Doorn
 * Acivity is loaded from songinfoActivity (if user adds or deletes a song), or from main (on buttonclick).
 * Shows list of songs which was saved to firebase by user.
 */

public class PlayListActivity extends AppCompatActivity {
    DatabaseManager manager = new DatabaseManager();
    ArrayList<Song> songs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_list);
        songs = new ArrayList<>();
        manager.ReadList();
        DisplaySongList();
    }


    /** load info from databasemanager, put information in listview. */
    public void DisplaySongList(){
        songs = manager.ReturnSongs();
        ListView LVItems = (ListView) findViewById(R.id.LVPlaylist);
        ResultListAdapter adapter = new ResultListAdapter(this, R.layout.foundsongs, songs, "PlayList");
        LVItems.setAdapter(adapter);
    }

    public ArrayList<Song> SongsInPlaylist(){
        return songs;
    }
}