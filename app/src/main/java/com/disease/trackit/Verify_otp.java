package com.disease.trackit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class Verify_otp extends AppCompatActivity {

    private String PhoneNumber;
    private EditText enterOtp;
    private CardView mContinue;
    private String verificationid;
    private FirebaseAuth mAuth;
    private String code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_otp);
        getSupportActionBar().hide();


        mAuth = FirebaseAuth.getInstance();
        PhoneNumber = getIntent().getStringExtra("PhoneNumber");
        sendVerificationCode(PhoneNumber);
        enterOtp = findViewById(R.id.otp);
        mContinue = findViewById(R.id.verify_and_continue);




        mContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                code = enterOtp.getText().toString().trim();

                if (code.length() == 6){
                    verifycode(code);
                }

                else {
                    Toast.makeText(Verify_otp.this,"Please enter valid otp",Toast.LENGTH_LONG);
                }

            }
        });
    }

    private void sendVerificationCode(String phoneNumber) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks        // OnVerificationStateChangedCallbacks
        );
    }
    private void verifycode(String code) {
        PhoneAuthCredential credential=PhoneAuthProvider.getCredential(verificationid,code);
        signInWithCredential(credential);

    }
    private void signInWithCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Intent toEditProfile=new Intent(Verify_otp.this,Register.class);
                    toEditProfile.putExtra("PhoneNumber",PhoneNumber);
                    toEditProfile.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(toEditProfile);
                }
                else {
                    Toast.makeText(Verify_otp.this,"Cannot Sign In",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks
            mCallbacks=new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            verificationid=s;
        }

        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            code=phoneAuthCredential.getSmsCode();

            if (code!=""){
                verifycode(code);
            }

        }

        @Override
        public void onVerificationFailed(FirebaseException e) {

            Toast.makeText(Verify_otp.this,"verification was unsuccessful",Toast.LENGTH_LONG).show();

        }
    };
}