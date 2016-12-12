package com.example.marlieske.marlieskepsetfinal;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Marlieske on 6-12-2016.
 * HTTPRequesthelper builds the HTTP, sends a request and retrieves the returned JSON string.
 * This class is used in AsyncTask, and the string will be returned to Asynctask.
 */

public class HTTPRequestHelper {
    public static String executeRequest(Object Keyword){
        String result = "";
        try {
            // build URL using API key and keyword
            URL link = new URL("http://ws.audioscrobbler.com/2.0/?method=track.search&track=" + Keyword + "&api_key=cc8ee82a7433b3f018502d19a22cf173&format=json");

            HttpURLConnection connection = (HttpURLConnection) link.openConnection();
            Integer ResponseCode = connection.getResponseCode();

            if (ResponseCode >= 300 && ResponseCode <= 200) {
                // if responsecode shows error, get errorstream
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                Log.d("responseCode.if", br.toString());
                return String.valueOf(br);
            } else {
                // else copy information to result
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line = br.readLine();
                while (line != null){
                    result = result + line;
                    line = br.readLine();
                }
            }
        } catch (IOException e) {
            // if something goes wrong: show error.
            e.printStackTrace();
        }
        return result;
    }
}
