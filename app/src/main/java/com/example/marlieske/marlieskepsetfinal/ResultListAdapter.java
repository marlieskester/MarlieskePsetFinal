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
 */

public class ResultListAdapter extends ArrayAdapter<Song>{
    JSONObject result;
    Context context;
    ArrayList<Song> songs;
    public ResultListAdapter(Context context, int resource, ArrayList<Song> songs) {
        super(context, resource, songs);
        this.songs = songs;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.foundsongs, null);
        }

        final TextView TVArtist = (TextView) convertView.findViewById(R.id.artist);
        final TextView TVTitle = (TextView) convertView.findViewById(R.id.title);

        final Song song = songs.get(position);
        TVArtist.setText(song.artist);
        TVTitle.setText(song.title);

        TVTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("onclick", "works");
                Intent selectedSong = new Intent(context, SongInfoActivity.class);
                selectedSong.putExtra("songnumber", getCount());
                selectedSong.putExtra("stringofsongs", songs);
                context.startActivity(selectedSong);
            }
        });
        return convertView;
    }
    public int getCount(){
        Log.d("getcount", ""+songs.size());
        return songs.size();
    }
}
