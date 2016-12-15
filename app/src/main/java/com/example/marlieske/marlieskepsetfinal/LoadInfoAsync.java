package com.example.marlieske.marlieskepsetfinal;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Marlieske on 13-12-2016.
 */

public class LoadInfoAsync extends AsyncTask<String, Object, URLConnection> {
    URLConnection newConnection = null;

    public LoadInfoAsync(){

    }
    @Override
    protected URLConnection doInBackground(String... urls) {

        try {
            URL url = new URL(urls[0]);
            newConnection = url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("loadinfo", "catch");
        }
        return newConnection;
    }

    protected void onPostExecute(URLConnection newConnection){
        Log.d("onPE", "LoadInfo");
        try {
            newConnection.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
