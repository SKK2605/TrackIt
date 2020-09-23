package com.disease.trackit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {

    private FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        FirebaseApp.initializeApp(this);
        user = FirebaseAuth.getInstance().getCurrentUser();

        if(user==null)
        {
            Intent to_signup = new Intent(MainActivity.this,Sign_up.class);
            to_signup.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(to_signup);
            finish();

        }

        else {
            Toast.makeText(this,"The Firebase uid is"+user.getUid(),Toast.LENGTH_LONG).show();

        }



    }
}