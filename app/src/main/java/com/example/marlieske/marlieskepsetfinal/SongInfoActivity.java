package com.example.marlieske.marlieskepsetfinal;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

/**
 * Made be Marlieske Doorn
 * Is called from ListAdapter (onclick), shows information about song.
 * On buttonclick go to webpage or add/remove from playlist.
 */

public class SongInfoActivity extends AppCompatActivity {

    String URLstring;
    Song song;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_info);

        if (savedInstanceState != null){
            song = savedInstanceState.getParcelable("song");
        }
        else {
            Intent selectedSong = getIntent();
            Bundle data = selectedSong.getExtras();
            song = data.getParcelable("song");
        }
        Log.d("oncreate", song.toString());
        getSong(song);
    }

    // show songinfo
    public void getSong(Song song){
        // find views, set content according to Song
        TextView TVArtist = (TextView) findViewById(R.id.SIArtist);
        TextView TVTitle = (TextView) findViewById(R.id.SITitle);
        ImageView Album = (ImageView) findViewById(R.id.Albumimage);
        new DownloadImage(Album).execute(song.albumimage);
        TVArtist.setText(song.artist);
        TVTitle.setText(song.title);
    }

    // load webpage on buttonclick
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

    // add or remove from list on buttonclick
    public void AddOrRemove(View view) {
        Button addRemove = (Button) findViewById(R.id.listbtn);
        // load list
        DatabaseManager manager = new DatabaseManager();
        manager.ReadList();
        // if list contains song, offer delete option
        if (manager.returnsongs.contains(song)) {
            addRemove.setText(R.string.remove_list);
            manager.DeleteSong(song.title);
            // after deletion, go to main
            Intent toMain = new Intent(this, MainActivity.class);
            startActivity(toMain);
            finish();
        }
        //else, offer add option
        else{
            addRemove.setText(R.string.add_list);
            manager.WriteToList(song, song.title);
            // after addition, go to list
            Intent toPlayList = new Intent(this, PlayListActivity.class);
            startActivity(toPlayList);
            finish();
        }
    }

    // if activity is killed, save current state
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        savedInstanceState.putParcelable("song", song);
        super.onSaveInstanceState(savedInstanceState);
    }
}
