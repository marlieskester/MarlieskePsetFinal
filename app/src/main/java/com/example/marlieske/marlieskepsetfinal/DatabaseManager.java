package com.example.marlieske.marlieskepsetfinal;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * Created by Marlieske on 7-12-2016.
 */

public class DatabaseManager {
    DatabaseReference myRef;
    ArrayList returnsongs;
    public void WriteToList(Song song, String title){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("message");

    }

    public void WritetoList(String title, Song song){
        myRef.child("songs").child(title).setValue(song);
    }

    public void ReadList(){
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                returnsongs = null;
                for (DataSnapshot songsnapshot: dataSnapshot.getChildren()) {
                    returnsongs.add(myRef.child("songs").orderByChild("title"));
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }

    public ArrayList ReturnSongs(){
        return returnsongs;
    }

    public void DeleteSong(String title){
        myRef.child("songs").child(title).removeValue();
    }


}
