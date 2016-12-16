package com.example.marlieske.marlieskepsetfinal;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by Marlieske on 7-12-2016.
 * This class manages the database using write, read and delete.
 * Userinformation can also be retrieved here.
 */

public class DatabaseManager {
    private ArrayList<Song> mReturnsongs = new ArrayList<>();
    String mEmail;
    private String uid = getUserInfo();
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference(uid);

    /** Writes song to list, use title as name tag */
    public void WriteToList(Song song, String title){
        myRef.child(title).setValue(song);
    }

    /**Reads list from firebase, creates arraylist */
    public void ReadList(){
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot songSnapShot: dataSnapshot.getChildren()) {
                    Song news = songSnapShot.getValue(Song.class);
                    mReturnsongs.add(news);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("oncancelled", "DatabaseManager");
            }
        });
    }

    /** returns songs added to string in write function */
    public ArrayList ReturnSongs(){
        return mReturnsongs;
    }

    /** deletes song from database by searching its name tag */
    public void DeleteSong(String title){
        myRef.child(title).removeValue();
    }

    /** returns user information if requested. */
    public String getUserInfo(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = null;
        if (user != null) {
            mEmail = user.getEmail();
            uid = user.getUid();
        }
        return uid;
    }


}