package com.example.marlieske.marlieskepsetfinal;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Made be Marlieske Doorn, based on Firebase Signup
 * Is called through buttonclick in loginActivity, goes to Main on buttonclick.
 * Registers new users to firebase
 */

public class SignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
    }

    public void signUp(View view) {
        // find text typed by user
        EditText ETMail = (EditText) findViewById(R.id.ETMailSU);
        EditText ETPW1 = (EditText) findViewById(R.id.ETPW1);
        EditText ETPW2 = (EditText) findViewById(R.id.ETPW2);
        String Mail = ETMail.getText().toString();
        String PW1 = ETPW1.getText().toString();
        String PW2 = ETPW2.getText().toString();

        // if either is empty, notify user
        if (Mail.equals("")||PW1.equals("")||PW2.equals("")){
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
        }

        // if password doesn't match check, notiify user
        else if (! PW1.equals(PW2)){
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            ETPW1.setHint("Password");
            ETPW2.setText("");
        }

        // else, sign in and go to Main
        else {
            createAccount(Mail, PW1);
            Intent tomain = new Intent(this, MainActivity.class);
            startActivity(tomain);
        }
    }
    public void createAccount(String email, String password) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("TAG", "createUserWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Toast.makeText(SignupActivity.this, "Login not successful", Toast.LENGTH_SHORT).show();
                        }


                    }
                });
    }
}
