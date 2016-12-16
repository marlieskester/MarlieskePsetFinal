package com.example.marlieske.marlieskepsetfinal;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Marlieske on 9-12-2016.
 * Class Song implements parcelable. Song contains Artist, image, link to information and title,
 *
 */

public class Song implements Parcelable {
    String title;
    String artist;
    String albuminfo;
    String albumimage;

    /**Constructor*/
    public Song(String title, String artist, String albuminfo, String albumimage) {
        this.title = title;
        this.artist = artist;
        this.albuminfo = albuminfo;
        this.albumimage = albumimage;
    }

    public Song(){}

    public void setTitle(String title){
        this.title = title;
    }

    private Song(Parcel in) {
        String[] data = new String[4];
        in.readStringArray(data);
        this.title = data[0];
        this.artist = data[1];
        this.albuminfo = data[2];
        this.albumimage = data[3];
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[]{this.title,
                this.artist,
                this.albumimage,
                this.albuminfo});
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Song createFromParcel(Parcel in) {
            return new Song(in);
        }

        public Song[] newArray(int size) {
            return new Song[size];
        }
    };
}