package com.example.sem2project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
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
import java.util.Locale;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Button signUpRedirectBtn,loginBtn,languageButton;
    EditText loginEmail,loginPwd;
    FirebaseAuth firebaseAuth;
    AlertDialog.Builder alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadLocale();
        alertDialog = new AlertDialog.Builder(this);

        //Button
        loginBtn = findViewById(R.id.loginBtn);
        signUpRedirectBtn = findViewById(R.id.signUpRedirectBtn);
        languageButton = findViewById(R.id.languageButton);
        //EditText
        loginEmail = findViewById(R.id.loginEmail);
        loginPwd = findViewById(R.id.loginPwd);

        firebaseAuth = FirebaseAuth.getInstance();

        signUpRedirectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // redirect to signup activity
                Intent intent = new Intent(MainActivity.this, Sign_Up.class);
                startActivity(intent);
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//                SharedPreferences.Editor editor = settings.edit();
//                editor.putString("username", loginEmail.getText().toString().split("@gmail\\.com")[0]);
//                editor.apply(); // Apply the changes (alternatively, you can use commit())
//
//                Intent intent = new Intent(MainActivity.this, HomePage.class);
//                startActivity(intent);
                loginUser();
            }
        });
        languageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String[] languages = {"English","हिंदी","मराठी","ગુજરાતી"};

                alertDialog.setSingleChoiceItems(languages, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int langId) {
                        if (langId==0){
                            setLocale("en");
                            recreate();
                        }else if (langId==1){
                            setLocale("hi");
                            recreate();
                        } else if (langId==2) {
                            setLocale("mr");
                            recreate();
                        } else if (langId==3) {
                            setLocale("gu");
                        }
                    }
                });
                alertDialog.create();
                alertDialog.show();


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

                    SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putString("username", loginEmail.getText().toString().split("@gmail\\.com")[0]);
                    editor.apply();


                    Intent intent = new Intent(MainActivity.this, HomePage.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    Toast.makeText(MainActivity.this, "Try Again!!..", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



    private void setLocale (String lang) {
        Locale locale = new Locale (lang);
        Configuration configuration = new Configuration();
        configuration.setLocale(locale);
        getBaseContext().getResources().updateConfiguration(configuration, getBaseContext().getResources().getDisplayMetrics());

        SharedPreferences. Editor editor = getSharedPreferences (  "Settings", MODE_PRIVATE).edit();
        editor.putString("mylang", lang);
        editor.apply();
    }

    private void loadLocale(){
        SharedPreferences sharedPreferences = getSharedPreferences ( "Settings", MODE_PRIVATE);
        String lang = sharedPreferences.getString( "mylang", "");
        setLocale (lang);
    }

}