package com.example.marlieske.marlieskepsetfinal;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

/**
 * MainActivity made by Marlieske Doorn
 * Activity is called from login or signup activity. Either passes keyword to Asynctask or loads playlist.
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatabaseManager manager = new DatabaseManager();
        manager.getUserInfo();
        String mail = manager.mEmail;
        TextView welcome = (TextView) findViewById(R.id.welcome);
        welcome.setText(getString(R.string.signed_in) + mail);
    }

    /** load songs based on keyword search */
    public void loadSongs(View view) {
        EditText ETKeyWord = (EditText) findViewById(R.id.ETKeyWord);
        String Keyword = ETKeyWord.getText().toString();

        // if empty, notify user
        if (Keyword.equals("")) {
            Toast.makeText(this, "Please enter Keyword", Toast.LENGTH_SHORT).show();
        }

        //else load asynctask with provided keyword
        else {
            AsyncTask songAsyncTask = new SearchAsyncTask(this);
            songAsyncTask.execute(Keyword);
        }
    }

    /**  or on buttonclick go to playlist */
    public void toPlayList(View view) {
        Intent toPlayLisy = new Intent(this, PlayListActivity.class);
        startActivity(toPlayLisy);
    }

    public void signout(View view) {
        FirebaseAuth.getInstance().signOut();
        Intent toLogin = new Intent(this, LogInActivity.class);
        startActivity(toLogin);
        finish();
    }

    /** Onclick, load toptracks using other HTTPrequest */
    public void loadtop(View view) {
        AsyncTask songAsyncTask = new SearchAsyncTask(this);
        songAsyncTask.execute("Ikzoektoptracks");
    }
}
