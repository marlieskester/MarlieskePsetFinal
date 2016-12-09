package com.example.marlieske.marlieskepsetfinal;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Marlieske on 6-12-2016.
 */

public class SearchAsyncTask extends AsyncTask<Object, Void, String>{
    Context context;
    MainActivity activity;
    String result;

    public SearchAsyncTask(MainActivity activity){
        // constructor
        this.activity = activity;
        this.context = this.activity.getApplicationContext();
    }

    @Override
    protected String doInBackground(Object... params) {
        // executes HTTPrequest, returns URL
        return HTTPRequestHelper.executeRequest(params[0]);
    }

    @Override
    protected void onPreExecute() {
        // displays message to indicate start search
        Toast.makeText(context, "Loading your song", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPostExecute(String result) {
        if (result.equals("")) {
            Toast.makeText(context,"Sorry, does not exist", Toast.LENGTH_SHORT).show();
        } else {
            Log.d("result", result);
            Intent toListOfSongs = new Intent(context, ListofSongsActivity.class);
            toListOfSongs.putExtra("result", result);
            toListOfSongs.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(toListOfSongs);
        }
    }

}
