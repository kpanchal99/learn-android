package com.example.sem2project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class SetWallpaper extends AppCompatActivity {

    ImageButton wp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_wallpaper);
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
