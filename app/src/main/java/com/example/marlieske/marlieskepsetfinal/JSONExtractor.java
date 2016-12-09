package com.example.marlieske.marlieskepsetfinal;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * Created by Marlieske on 9-12-2016.
 */

public class JSONExtractor {
    String allresults;
    public JSONExtractor(String results){
        this.allresults = results;
    }

    public ArrayList getSongs(){
//        ListofSongsActivity list = new ListofSongsActivity();
//        String results = list.returnresult();
        ArrayList songs = new ArrayList();
        JSONObject jsonwholething = null;
        JSONArray jsonsongs = null;
        try {
            jsonwholething = new JSONObject(allresults);
            JSONObject jresults = (JSONObject) jsonwholething.get("results");
            JSONObject jattr = (JSONObject) jresults.get("trackmatches");
            jsonsongs = (JSONArray) jattr.get("track");

            for (int i = 0; i < jsonsongs.length(); i++){
                try {
                    Song song = new Song();
                    JSONObject result = jsonsongs.getJSONObject(i);
                    song.artist = result.getString("artist");
                    song.albumimage = result.getString("image");
                    song.albuminfo = result.getString("url");
                    song.title = result.getString("name");
                    Log.d("adapter", result.getString("artist"));
                    songs.add(song);
                } catch (JSONException e) {
                    Log.d("exception", "too bad");
                    e.printStackTrace();
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    return songs;
    }
}
