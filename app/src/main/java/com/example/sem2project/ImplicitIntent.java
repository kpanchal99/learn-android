package com.example.sem2project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class ImplicitIntent extends AppCompatActivity {

    ImageButton wp, ws, dial, map;
    EditText url,num,loc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_implicit_intent);
        ws = findViewById(R.id.ws);
        url = findViewById(R.id.url);
        num = findViewById(R.id.num);
        dial =findViewById(R.id.dial);
        map = findViewById(R.id.map);
        loc = findViewById(R.id.loc);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mapIntent = new Intent(Intent.ACTION_VIEW);
                mapIntent.setData(Uri.parse("geo:0,0?q=" + loc.getText().toString()));
                startActivity(mapIntent);
            }
        });
        dial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent dialer = new Intent(Intent.ACTION_DIAL);
                dialer.setData(Uri.parse("tel:"+num.getText().toString()));
                startActivity(dialer);
            }
        });
        ws.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url.getText().toString()));
                startActivity(i);
            }
        });


        wp = findViewById(R.id.wp);
        wp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent changewp = new Intent(Intent.ACTION_SET_WALLPAPER);
                startActivity(Intent.createChooser(changewp,"Select wallpaper"));

            }
        });
    }

}
