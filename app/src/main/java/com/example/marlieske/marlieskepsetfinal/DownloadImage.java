package com.example.marlieske.marlieskepsetfinal;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;

/**
 * Created by Marlieske on 12-12-2016.
 */

public class DownloadImage extends AsyncTask<String, Void, Bitmap> {
    ImageView albumimage;

    public DownloadImage(ImageView albumimage){
        this.albumimage = albumimage;
    }
    @Override
    protected Bitmap doInBackground(String... urls) {
        String url = urls[0];
        Bitmap mIcon = null;
        try {
            InputStream in = new java.net.URL(url).openStream();
            mIcon = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
        }
        return mIcon;
    }

    protected void onPostExecute(Bitmap result) {
        albumimage.setImageBitmap(result);
    }
}