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
    String allresults;

    //constructor
    public JSONExtractor(String results){
        this.allresults = results;
    }

    // extractor
    public ArrayList getSongs(){
        ArrayList songs = new ArrayList();

        try {
            // get to the parts where songs are written
//            JSONObject jsonwholething = new JSONObject(allresults);
//            JSONObject jresults = (JSONObject) jsonwholething.get("results");
//            JSONObject jattr = (JSONObject) jresults.get("trackmatches");
            JSONArray jsonsongs = new JSONArray(allresults);



            for (int i = 0; i < jsonsongs.length(); i++){
                // for all songs, extract info from JSONArray, put in one song, add song to arraylist.
                try {
                    JSONObject result = jsonsongs.getJSONObject(i);
                    String artist = result.getString("artist");
                    String albumimage = result.getString("image");
                    String albuminfo = result.getString("url");
                    String title = result.getString("name");
                    Song song = new Song(title, artist, albuminfo, albumimage);
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
