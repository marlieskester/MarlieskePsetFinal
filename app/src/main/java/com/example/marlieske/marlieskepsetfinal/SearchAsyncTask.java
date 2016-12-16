package com.example.marlieske.marlieskepsetfinal;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

/**
 * Created by Marlieske on 6-12-2016.
 * SearchAsynctask is called upon from main, uses HTTPRequesthelper and launches ListofSongs.
 * Gets a string based on keyword search.
 */

public class SearchAsyncTask extends AsyncTask<Object, Void, String>{
    private Context mContext;
    private MainActivity mActivity;

    /** constructor */
    public SearchAsyncTask(MainActivity activity){
        this.mActivity = activity;
        this.mContext = this.mActivity.getApplicationContext();
    }

    /** executes HTTPrequest, returns URL */
    @Override
    protected String doInBackground(Object... params) {
        return HTTPRequestHelper.executeRequest(params[0]);
    }

    /** displays message to indicate start search */
    @Override
    protected void onPreExecute() {
        Toast.makeText(mContext, "Loading your songs", Toast.LENGTH_SHORT).show();
    }

    /** passes result to next activity */
    @Override
    protected void onPostExecute(String result) {
        if (result.equals("")) {
            Toast.makeText(mContext,"Sorry, no songs found", Toast.LENGTH_SHORT).show();
        } else {
            Intent toListOfSongs = new Intent(mContext, ListofSongsActivity.class);
            toListOfSongs.putExtra("result", result);
            toListOfSongs.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(toListOfSongs);
        }
    }

}
