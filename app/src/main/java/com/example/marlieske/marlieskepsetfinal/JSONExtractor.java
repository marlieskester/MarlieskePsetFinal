package com.example.marlieske.marlieskepsetfinal;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * Created by Marlieske on 9-12-2016.
 * Class is called upon from ListOfSongs Activity.
 * Extracts the JSONstring provided by the HTTPRequesthelper, returns ArrayList to ListOfSongs.
 */

public class JSONExtractor {
    private String mResults;

    /** constructor */
    public JSONExtractor(String results){
        this.mResults = results;
    }

    /** extracts info from JSON */
    public ArrayList getSongs(){
        ArrayList songs = new ArrayList();
        JSONArray jsonsongs = new JSONArray();
        JSONObject jsonwholething = new JSONObject();
        try {
            jsonwholething = new JSONObject(mResults);
            JSONObject jresults = (JSONObject) jsonwholething.get("results");
            JSONObject jattr = (JSONObject) jresults.get("trackmatches");
            jsonsongs = (JSONArray) jattr.get("track");
        } catch (JSONException e) {
            // if get top tracks is used, there's an other format
            try {
                JSONObject alljsonsongs = (JSONObject) jsonwholething.get("tracks");
                jsonsongs = (JSONArray) alljsonsongs.get("track");
            } catch (JSONException e1) {
                e1.printStackTrace();
            }
        }

        for (int i = 0; i < jsonsongs.length(); i++){
            // for all songs, extract info from JSONArray, put in one song, add song to arraylist.
            try {
                JSONObject result = jsonsongs.getJSONObject(i);
                String artist = result.getString("artist");
                if (artist.contains("name")) {
                    try {
                        // again, for top tracks another format
                        JSONObject artistjson = (JSONObject) result.get("artist");
                        artist = artistjson.getString("name");
                    } catch (JSONException e){
                        Log.d("Extractor", "toptracksartist");
                        e.printStackTrace();
                    }
                }
                String albumimage = result.getString("image");
                String albuminfo = result.getString("url");
                String title = result.getString("name");
                Song song = new Song(title, artist, albuminfo, albumimage);
                songs.add(song);
            } catch (JSONException e) {
                Log.d("exception", "tracksinfo");
                e.printStackTrace();
            }
        }
        return songs;
    }
}
