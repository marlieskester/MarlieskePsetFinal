package com.example.marlieske.marlieskepsetfinal;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

import static android.content.ContentValues.TAG;

/**
 * Created by Marlieske on 7-12-2016.
 * This class manages the database using write, read and delete.
 * Userinformation can also be retrieved here.
 */

public class DatabaseManager {
    DatabaseReference myRef;
    ArrayList<Song> returnsongs;

    // Write song to list, use title as name tag
    public void WriteToList(Song song, String title){
        String uid = getUserInfo();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        myRef.child(uid).child(title).setValue(song);
    }

    // Read from the database, return all songs savd by this user
    public void ReadList() {
        Log.d("readlist", "list");
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        String uid = getUserInfo();
        myRef = database.getReference(uid);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                returnsongs = new ArrayList<>();
//
                    HashMap<String, String> songmap = (HashMap<String, String>) dataSnapshot.getValue();
                Song thisone = dataSnapshot.child(songmap.toString()).getValue(Song.class);


                    Log.d("readlist", songmap.toString());
                }
//                //Song song = songsnapshot.getValue(Song.class);
//                //Log.d("song", song.toString());
//                //     returnsongs.add(myRef.child("songs").orderByChild("title"));
//                //  returnsongs.add(song);
//                //  Log.d("ondatachenge", ""+ returnsongs);




            @Override
            public void onCancelled(DatabaseError error) {
                // if failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
//                PlayListActivity activity = new PlayListActivity();
//                activity.ErrorLoading();
            }
        });
    }

    // returns songs added to string in write function
    public ArrayList ReturnSongs(){
        return returnsongs;
    }

    // deletes song from database by searching its name tag
    public void DeleteSong(String title){
        myRef.child("songs").child(title).removeValue();
    }

    // returns user information if requested.
    public String getUserInfo(){

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = null;
        if (user != null) {
            String email = user.getEmail();
            uid = user.getUid();
        }
        return uid;
    }

}
