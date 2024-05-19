package com.example.sem2project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Button signUpRedirectBtn,loginBtn;
    EditText loginEmail,loginPwd;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //Button
        loginBtn = findViewById(R.id.loginBtn);
        signUpRedirectBtn = findViewById(R.id.signUpRedirectBtn);
        //EditText
        loginEmail = findViewById(R.id.loginEmail);
        loginPwd = findViewById(R.id.loginPwd);

        firebaseAuth = FirebaseAuth.getInstance();

        signUpRedirectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // redirect to home activity
                Intent intent = new Intent(MainActivity.this, Sign_Up.class);
                startActivity(intent);
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, HomePage.class);
                startActivity(intent);
//                loginUser();
            }
        });

    }

    public void loginUser(){

        String email = loginEmail.getText().toString();
        String pwd = loginPwd.getText().toString();

        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(pwd)){
            Toast.makeText(this, "Please fill values!!", Toast.LENGTH_SHORT).show();
            return;
        }
        //check password in firebase
        firebaseAuth.signInWithEmailAndPassword(email,pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    // redirect to home activity admin123
                    Intent intent = new Intent(MainActivity.this, HomePage.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(MainActivity.this, "Try Again!!..", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}