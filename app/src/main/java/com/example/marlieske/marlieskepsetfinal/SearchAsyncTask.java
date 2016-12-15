package com.example.marlieske.marlieskepsetfinal;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Marlieske on 6-12-2016.
 * SearchAsynctask is called upon from main, uses HTTPRequesthelper and launches ListofSongs.
 * Gets a string based on keyword search.
 */

public class SearchAsyncTask extends AsyncTask<Object, Void, String>{
    Context context;
    MainActivity activity;

    // constructor
    public SearchAsyncTask(MainActivity activity){
        this.activity = activity;
        this.context = this.activity.getApplicationContext();
    }

    // executes HTTPrequest, returns URL
    @Override
    protected String doInBackground(Object... params) {
        return HTTPRequestHelper.executeRequest(params[0]);
    }

    // displays message to indicate start search
    @Override
    protected void onPreExecute() {
        Toast.makeText(context, "Loading your songs", Toast.LENGTH_SHORT).show();
    }

    // handles result
    @Override
    protected void onPostExecute(String result) {
        // if no result, tell user
        if (result.equals("")) {
            Toast.makeText(context,"Sorry, no songs found", Toast.LENGTH_SHORT).show();
        }

        // else pass result on to ListofSongs
        else {
            JSONArray jsonsongs = new JSONArray();
            try {
                JSONObject jsonwholething = new JSONObject(result);
                JSONObject jresults = (JSONObject) jsonwholething.get("results");
                JSONObject jattr = (JSONObject) jresults.get("trackmatches");
                jsonsongs = (JSONArray) jattr.get("track");
            } catch (JSONException e) {
                e.printStackTrace();
            }


            Intent toListOfSongs = new Intent(context, ListofSongsActivity.class);
            toListOfSongs.putExtra("result", jsonsongs.toString());
            toListOfSongs.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(toListOfSongs);
        }
    }

}
