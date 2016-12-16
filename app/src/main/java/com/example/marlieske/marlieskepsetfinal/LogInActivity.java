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
 * LoginActivity made by Marlieske Doorn, based on Firebase login.
 * Activity is first in line, asks for login information.
 * If the user is new a signup activity is offered.
 * Information is saved to firebase and Mainactivity is loaded after succesfull login.
 */

public class LogInActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private String mEMail;
    private String mPassword;
    EditText mail;
    EditText key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        mAuth = FirebaseAuth.getInstance();
        mail = (EditText) findViewById(R.id.ETMail);
        key = (EditText) findViewById(R.id.ETPassword);
        if (savedInstanceState != null){
            mEMail = savedInstanceState.getParcelable("mail");
            mPassword = savedInstanceState.getParcelable("key");
            mail.setText(mEMail);
            key.setText(mPassword);
        }
    }

    /** on buttonclick, login and go to main */
    public void toMain(View view) {
        // get mail and password form edittext
        mEMail = mail.getText().toString();
        mPassword = key.getText().toString();

        // if either is empty, notify user
        if (mEMail.equals("") || mPassword.equals("")){
            Toast.makeText(this, "Please enter mail and password", Toast.LENGTH_SHORT).show();
        }

        // else, sign in
        else {
            mAuth.signInWithEmailAndPassword(mEMail, mPassword)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Log.d("TAG", "signInWithEmail:onComplete:" + task.isSuccessful());

                            // If sign in fails, display a message to the user. If sign in succeeds
                            // the auth state listener will be notified and logic to handle the
                            // signed in user can be handled in the listener.
                            if (!task.isSuccessful()) {
                                Log.d("TAG", "signInWithEmail:failed", task.getException());
                                Toast.makeText(LogInActivity.this, "Please try again",
                                        Toast.LENGTH_SHORT).show();
                            }

                            // on succes, go to main
                            else {
                                Intent toMain = new Intent(LogInActivity.this, MainActivity.class);
                                startActivity(toMain);
                                finish();
                            }
                        }
                    });
                }
    }

    /** or go to signup activity (buttonclick) */
    public void toSignUp(View view) {
        Intent toSignUp = new Intent(this, SignupActivity.class);
        startActivity(toSignUp);
    }

    /** if activity is killed, save current state */
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("mail", mEMail);
        savedInstanceState.putString("key", mPassword);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        String email = savedInstanceState.getString("mail");
        String password = savedInstanceState.getString("key");

    }
}