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
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        songs = new ArrayList<>();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot songSnapShot: dataSnapshot.getChildren()) {
                    Log.d("playlistadded", "" + dataSnapshot.getChildren());
//                    String title = (String) songSnapShot.child("title").getValue();
//                    dataSnapshot.getValue();
//                    Song newsong = new Song();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
//        myRef.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
//                Log.d("playlistadded", ""+dataSnapshot.getValue());
//                DatabaseManager manager = new DatabaseManager();
//                String uid = manager.getUserInfo();
//                for (DataSnapshot songSnapShot : dataSnapshot.getChildren()){
//                    Log.d("playlistadded", ""+dataSnapshot.getChildren());
//                    String title = (String) songSnapShot.child("title").getValue();
//                    dataSnapshot.getValue();
//                    Song newsong = new Song();
//                }
//                String newSong = dataSnapshot.getValue().toString();
//                //JSONExtractor ex = new JSONExtractor(newSong);
//                //songs = ParseSongs(newSong);
//                DisplaySongList();
//
//                //songs.add(newSong);
//            }
//            @Override
//            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//
//            }
//            @Override
//            public void onChildRemoved(DataSnapshot dataSnapshot) {
//            }
//            @Override
//            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//            }
 //       });
         //   manager.ReadList();
        //DisplaySongList();
    }
    
//    private ArrayList<Song> ParseSongs(String s)
//    {
//        String[] songs = s.split("} ,");
//        ArrayList<Song> Songs = new ArrayList<Song>();
//        for(String s2 : songs){
//            String song = s2.substring(1);
//            int index=0;
//            String Title = song.substring(1, (index = song.indexOf('=')));
//            String albumImage = song.substring((index = song.indexOf("albumimage", index)+1), song.indexOf(",", index));
//            String albumInfo = song.substring((index = song.indexOf("abluminfo", index)+2), song.indexOf("]", index));
//            String artist = song.substring((index = song.indexOf("artist", index)+1), song.indexOf("}", index));
//            Song newSong = new Song(Title, artist, albumInfo, albumImage);
//            Songs.add(newSong);
//        }
//        return Songs;
//    }

    // load info from databasemanager, put information in listview.
    public void DisplaySongList(){
        //ArrayList songs = manager.ReturnSongs();
        Log.d("Playlist", ""+ songs);
        ListView LVItems = (ListView) findViewById(R.id.LVPlaylist);
        ResultListAdapter adapter = new ResultListAdapter(this, R.layout.foundsongs, songs);
        LVItems.setAdapter(adapter);
    }

    // On error, notify user
    public void ErrorLoading(){
        Toast.makeText(this, "an error occurred. Please try again.", Toast.LENGTH_SHORT).show();
    }
}