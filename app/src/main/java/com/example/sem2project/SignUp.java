package com.example.sem2project;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignUp extends AppCompatActivity {

    Button signUpBtn,loginRedirectBtn;
    EditText signUpPwd,signUpEmail,signUpConfirmPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signUpBtn=findViewById(R.id.signUpBtn);
        loginRedirectBtn=findViewById(R.id.loginRedirectBtn);

        signUpPwd=findViewById(R.id.signUpPwd);
        signUpEmail=findViewById(R.id.signUpEmail);
        signUpConfirmPwd=findViewById(R.id.signUpConfirmPwd);

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // redirect to home activity
                Intent intent = new Intent(SignUp.this, HomePage.class);
                startActivity(intent);
            }
        });

        loginRedirectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // redirect to login/main activity
                Intent intent = new Intent(SignUp.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}