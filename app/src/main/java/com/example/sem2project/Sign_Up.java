package com.example.sem2project;


import androidx.annotation.NonNull;
        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Toast;

        import com.google.android.gms.tasks.OnCompleteListener;
        import com.google.android.gms.tasks.Task;
        import com.google.firebase.auth.AuthResult;
        import com.google.firebase.auth.FirebaseAuth;

public class Sign_Up extends AppCompatActivity {

    Button signUpBtn,loginRedirectBtn;
    EditText signUpPwd,signUpEmail,signUpConfirmPwd;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signUpBtn=findViewById(R.id.signUpBtn);
        loginRedirectBtn=findViewById(R.id.loginRedirectBtn);

        signUpEmail=findViewById(R.id.signUpEmail);
        signUpPwd=findViewById(R.id.signUpPwd);
        signUpConfirmPwd=findViewById(R.id.signUpConfirmPwd);

        firebaseAuth = FirebaseAuth.getInstance();

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // create and redirect
                signUpUser();
            }
        });

        loginRedirectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // redirect to login/main activity
                Intent intent = new Intent(Sign_Up.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void signUpUser(){
        // create new user in firebase
        String email = signUpEmail.getText().toString();
        String pwd = signUpPwd.getText().toString();
        String confirm_pwd = signUpConfirmPwd.getText().toString();

        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(pwd) || TextUtils.isEmpty(confirm_pwd)){
            Toast.makeText(this, "Please fill values!!", Toast.LENGTH_SHORT).show();
            return;
        }
        //check password in firebase
        firebaseAuth.createUserWithEmailAndPassword(email,pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(Sign_Up.this, "Registered Successful", Toast.LENGTH_SHORT).show();
                    // redirect to home activity after success
                    Intent intent = new Intent(Sign_Up.this, HomePage.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(Sign_Up.this, "Failed!! Try Again..", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}