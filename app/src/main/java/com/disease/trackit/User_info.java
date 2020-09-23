package com.disease.trackit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class User_info extends AppCompatActivity {

    TextView decoded_name ;
    String decoded_info ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        getSupportActionBar().hide();
        decoded_name = findViewById(R.id.decoded_name);

        decoded_name.setText(getIntent().getStringExtra("decoded_data"));
        decoded_info = decoded_name.getText().toString();
        System.out.println("******************************************************************");
        System.out.println(decoded_info);
        System.out.println("******************************************************************");

    }
}