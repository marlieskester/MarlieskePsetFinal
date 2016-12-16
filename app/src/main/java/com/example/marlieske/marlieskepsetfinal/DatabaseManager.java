package com.example.marlieske.marlieskepsetfinal;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
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
    ArrayList<Song> returnsongs = new ArrayList<>();
    String email;

    // Write song to list, use title as name tag
    public void WriteToList(Song song, String title){
        String uid = getUserInfo();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        myRef.child(uid).child(title).setValue(song);
    }

    public void ReadList(){
        String uid = getUserInfo();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference(uid);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot songSnapShot: dataSnapshot.getChildren()) {
                    Song news = songSnapShot.getValue(Song.class);
                    Log.d("playlistadded", news.toString());
//                    String title = (String) songSnapShot.child("title").getValue();
//                    dataSnapshot.getValue();
                     returnsongs.add(news);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

//    // Read from the database, return all songs savd by this user
//    public void ReadList() {
//        Log.d("readlist", "list");
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        String uid = getUserInfo();
//        myRef = database.getReference(uid);
//        myRef.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                returnsongs = new ArrayList<>();
//                int i = 0;
//                String title = null, artist = null, info = null, image = null;
//                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                    Object other = snapshot.getValue();
//                        if (i % 4 == 0) {
//                            info = other.toString();
//                        }
//                        else if (i % 4 == 1) {
//                            image = other.toString();
//                        }
//                        else if (i % 4 == 2) {
//                            artist = other.toString();
//                        }
//                        else {
//                            title = other.toString();
//                        }
//                    Song lastTry = new Song(title, artist, info, image);
//                    returnsongs.add(lastTry);
//                    i ++;
//                    Song song = dataSnapshot.getValue(Song.class);
////                    Song tryAgain = snapshot.child(other.toString()).getValue(Song.class);
//                    Object value = dataSnapshot.getValue();
//
//
//                    Song thisone = dataSnapshot.child(songmap.toString()).getValue(Song.class);
//                    Log.d("r", "" + other);
//                    Log.d("read", ""+lastTry);
//                    Log.d("read", "" + value);
//                    Log.d("readlist", "" + song.artist);
//                }
//            }

//            @Override
//            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onChildRemoved(DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });

//        myRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                returnsongs = new ArrayList<>();
//                for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
//                    Song other = snapshot.getValue(Song.class);
//                    Song song = dataSnapshot.getValue(Song.class);
//                    song.
//                    String value = dataSnapshot.getValue();
//
//                    // Song thisone = dataSnapshot.child(songmap.toString()).getValue(Song.class);
//                    Log.d("r", "" + other);
//                    Log.d("read", "" + value);
//                    Log.d("readlist", "" + song);
//                }
//                }
//                //Song song = songsnapshot.getValue(Song.class);
//                //Log.d("song", song.toString());
//                //     returnsongs.add(myRef.child("songs").orderByChild("title"));
//                //  returnsongs.add(song);
//                //  Log.d("ondatachenge", ""+ returnsongs);




//            @Override
//            public void onCancelled(DatabaseError error) {
//                // if failed to read value
//                Log.w(TAG, "Failed to read value.", error.toException());
////                PlayListActivity activity = new PlayListActivity();
////                activity.ErrorLoading();
//            }
//        });
//    }

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
            email = user.getEmail();
            uid = user.getUid();
        }
        return uid;
    }

    public String getMail(){
        return email;
    }

}
