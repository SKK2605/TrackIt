package com.disease.trackit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Sign_up extends AppCompatActivity {

    private EditText mPhoneNumber;
    private CardView mContinue;
    private String PhoneNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().hide();

        mContinue = findViewById(R.id.continue_card);
        mPhoneNumber = findViewById(R.id.phone_number);

        mContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhoneNumber = mPhoneNumber.getText().toString().trim();
                if (PhoneNumber.length() == 10){

                    PhoneNumber = "+91"+PhoneNumber;
                    Intent to_otp = new Intent(Sign_up.this,Verify_otp.class);
                    to_otp.putExtra("PhoneNumber",PhoneNumber);
                    startActivity(to_otp);
                    finish();

                }

                else {
                    Toast.makeText(Sign_up.this,"Please enter valid phonenumber",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}