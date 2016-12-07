package com.example.marlieske.marlieskepsetfinal;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Marlieske on 6-12-2016.
 */

public class ListAdapter extends ArrayAdapter{
    JSONObject result;
    Context context;
    public ListAdapter(Context context, int resource) {
        super(context, resource);
    }

    public View getView(int position, View convertView, ViewGroup parent){
        MainActivity activity = new MainActivity();
        ListofSongsActivity list = new ListofSongsActivity();
        String results = list.returnresult();

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.foundsongs, null);
        }

        final TextView TVArtist = (TextView) convertView.findViewById(R.id.artist);
        final TextView TVTitle = (TextView) convertView.findViewById(R.id.title);

        try {
            JSONObject jsonwholething = new JSONObject(results);
            JSONArray jsonsongs = (JSONArray) jsonwholething.get("track");
            result = jsonsongs.getJSONObject(position);
            TVArtist.setText(result.getString("artist"));
            TVTitle.setText(result.getString("name"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        TVTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent selectedSong = new Intent(context, PlayListActivity.class);
                selectedSong.putExtra("song", (Parcelable) result);
                context.startActivity(selectedSong);
            }
        });
        return convertView;
    }

}
