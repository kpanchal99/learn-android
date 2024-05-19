package com.example.sem2project;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import android.os.Bundle;

public class MultipleIntentInOne extends AppCompatActivity {
    Button actionButton;
    EditText inputField;
    Bundle received;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple_intent_in_one);

        actionButton = findViewById(R.id.actionButton);
        inputField = findViewById(R.id.inputField);
        received = getIntent().getExtras();

        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleInputAction(inputField.getText().toString());
            }
        });
    }

    private void handleInputAction(String input) {
        if (TextUtils.isEmpty(input)) {
            Toast.makeText(this, "Input is empty", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isDigitsOnly(input)) {
            Intent phoneIntent = new Intent(Intent.ACTION_DIAL);
            phoneIntent.setData(Uri.parse("tel:" + input));
            startActivity(phoneIntent);
        } else if (input.startsWith("map:")) {
            Intent mapIntent = new Intent(Intent.ACTION_VIEW);
            mapIntent.setData(Uri.parse("geo:0,0?q=" + input.substring(4)));
            startActivity(mapIntent);
        } else if (input.contains("www") || input.contains("http")) {
            Intent webIntent = new Intent(Intent.ACTION_VIEW);
            webIntent.setData(Uri.parse(input));
            startActivity(webIntent);
        } else {
            Intent webSearchIntent = new Intent(Intent.ACTION_VIEW);
            webSearchIntent.setData(Uri.parse("https://www.google.com/search?q=" + input));
            startActivity(webSearchIntent);
        }
    }

}
