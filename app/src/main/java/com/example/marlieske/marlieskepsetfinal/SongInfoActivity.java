package com.example.marlieske.marlieskepsetfinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Made be Marlieske Doorn
 * Is called from ListAdapter (onclick), shows information about song.
 * On buttonclick go to webpage or add/remove from playlist.
 */

public class SongInfoActivity extends AppCompatActivity {

    String URLstring;
    Song song;
    DatabaseManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_info);
        manager = new DatabaseManager();
        manager.ReadList();

        if (savedInstanceState != null){
            song = savedInstanceState.getParcelable("song");
        }
        else {
            Intent selectedSong = getIntent();
            Bundle data = selectedSong.getExtras();
            song = data.getParcelable("song");
        }
        Log.d("info", ""+manager.ReturnSongs());
        getSong(song);
        Button addRemove = (Button) findViewById(R.id.listbtn);
        ArrayList returnsongs = manager.ReturnSongs();
        Log.d("retsngs", ""+returnsongs);
        if (returnsongs == null){
            addRemove.setText(R.string.add_list);
        }
        else if (returnsongs.contains(song)){
            addRemove.setText(R.string.add_list);
        }
        else {
            addRemove.setText(R.string.remove_list);
        }
    }

    // show songinfo
    public void getSong(Song song){
        // find views, set content according to Song
        TextView TVArtist = (TextView) findViewById(R.id.SIArtist);
        TextView TVTitle = (TextView) findViewById(R.id.SITitle);
        ImageView Album = (ImageView) findViewById(R.id.Albumimage);
        //new DownloadImage(Album).execute(song.albuminfo);
        TVArtist.setText(song.artist);
        TVTitle.setText(song.title);
    }

    // load webpage on buttonclick
    public void loadInfo(View view) {
        URLstring = song.albumimage;
        Log.d("onclick", URLstring);
        LoadInfoAsync async = new LoadInfoAsync();
        async.execute(URLstring);
    }

    // add or remove from list on buttonclick
    public void AddOrRemove(View view) {
            // if list contains song, offer delete option
            if (manager.ReturnSongs() == null || ! manager.ReturnSongs().contains(song)) {
                manager.WriteToList(song, song.title);
                // after addition, go to list
                Intent toPlayList = new Intent(this, PlayListActivity.class);
                startActivity(toPlayList);
                finish();
            }
            //else, offer add option
            else {
                manager.DeleteSong(song.title);
                // after deletion, go to main
                Intent toMain = new Intent(this, MainActivity.class);
                startActivity(toMain);
                finish();
            }
    }

    // if activity is killed, save current state
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putParcelable("song", song);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        song = savedInstanceState.getParcelable("song");
    }
}
