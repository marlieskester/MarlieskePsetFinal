package com.example.marlieske.marlieskepsetfinal;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void loadSongs(View view) {
        EditText ETKeyWord = (EditText) findViewById(R.id.ETKeyWord);
        String Keyword = ETKeyWord.getText().toString();
        if (Keyword.equals("")) {
            Log.d("if", Keyword);
            Toast.makeText(this, "Please enter Keyword", Toast.LENGTH_SHORT).show();
        } else {
            AsyncTask songAsyncTask = new SearchAsyncTask(this);
            songAsyncTask.execute(Keyword);
        }
    }

    public void toPlayList(View view) {
        Intent toPlayLisy = new Intent(this, PlayListActivity.class);
        startActivity(toPlayLisy);
    }

}
