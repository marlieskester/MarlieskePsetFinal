package com.example.marlieske.marlieskepsetfinal;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class SongInfoActivity extends AppCompatActivity {
    int songnumber;
    ArrayList<String> songs;
    String URLstring;
    Song song;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_info);
        Intent selectedSong = getIntent();
        songnumber = selectedSong.getIntExtra("songnumber", 0);
        songs = selectedSong.getStringArrayListExtra("stringofsongs");
        getSong(songnumber, songs);
    }

    public void getSong(int songnumber, ArrayList songs){
        song = (Song) songs.get(songnumber);
        TextView TVArtist = (TextView) findViewById(R.id.SIArtist);
        TextView TVTitle = (TextView) findViewById(R.id.SITitle);
        ImageView Album = (ImageView) findViewById(R.id.Albumimage);
//        URI uri = null;
//        try {
//            uri = new URI(song.albumimage);
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//        }
//        Album.setImageURI(uri);
        TVArtist.setText(song.artist);
        TVTitle.setText(song.title);
    }

    public void loadInfo(View view) {
        URLstring = song.albuminfo;
        try {
            URL url = new URL(URLstring);
            URLConnection newConnection = url.openConnection();
            newConnection.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void AddOrRemove(View view) {
        Button addRemove = (Button) findViewById(R.id.listbtn);
        DatabaseManager manager = new DatabaseManager();
        manager.ReadList();
        if (manager.returnsongs.contains(song)) {
            addRemove.setText("Remove from playlist");
            manager.DeleteSong(song.title);
        }
        else{
            addRemove.setText("Add to Playlist");
            manager.WriteToList(song, song.title);
        }
    }
}