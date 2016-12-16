package com.example.marlieske.marlieskepsetfinal;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Marlieske on 6-12-2016.
 * ResultListadapter adapts arraylists to fit a listview.
 * Is called on by either ListofSongs or PlayList, provides an onclick to SongInfoActivity
 */

public class ResultListAdapter extends ArrayAdapter<Song>{

    private Context context;
    private ArrayList<Song> songs;
    private String classname;

    // constructor
    public ResultListAdapter(Context context, int resource, ArrayList<Song> songs, String classname) {
        super(context, resource, songs);
        this.songs = songs;
        this.context = context;
        this.classname = classname;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent){

        // if no line in listview is available, make a new one
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.foundsongs, null);
        }

        // find textviews
        final TextView TVArtist = (TextView) convertView.findViewById(R.id.artist);
        final TextView TVTitle = (TextView) convertView.findViewById(R.id.title);

        // add text to textview
        final Song song = songs.get(position);
        TVArtist.setText(song.artist);
        TVTitle.setText(song.title);

        // onclick pass Song song to next activity
        TVTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent selectedSong = new Intent(context, SongInfoActivity.class);
                Song thisOne = songs.get(position);
                String artist = thisOne.artist;
                String title = thisOne.title;
                String albuminfo = thisOne.albuminfo;
                String albumimage = thisOne.albumimage;

                selectedSong.putExtra("song", new Song(title, artist, albuminfo, albumimage));
                selectedSong.putExtra("sender", classname);
                context.startActivity(selectedSong);
            }
        });
        return convertView;
    }

    public int getCount(){
        return songs.size();
    }
}
