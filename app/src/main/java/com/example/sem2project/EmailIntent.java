package com.example.sem2project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.os.Bundle;

public class EmailIntent extends AppCompatActivity {

    EditText to, cc, subject, body;
    Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_intent);

        to = findViewById(R.id.to);
        cc = findViewById(R.id.cc);
        subject = findViewById(R.id.subject);
        body = findViewById(R.id.body);
        send = findViewById(R.id.send);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mail = new Intent(Intent.ACTION_SEND);
                mail.putExtra(Intent.EXTRA_EMAIL, new String[]{to.getText().toString()});
                mail.putExtra(Intent.EXTRA_CC, new String[]{cc.getText().toString()});
                mail.putExtra(Intent.EXTRA_SUBJECT, subject.getText().toString());
                mail.putExtra(Intent.EXTRA_TEXT, body.getText().toString());

                mail.setType("message/rfc822");
                startActivity(Intent.createChooser(mail, "Select email client"));
            }
        });
    }
}
