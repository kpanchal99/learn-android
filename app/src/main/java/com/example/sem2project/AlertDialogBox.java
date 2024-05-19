package com.example.sem2project;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AlertDialogBox extends AppCompatActivity {

    Button alert;
    AlertDialog alertDialog;
    TextView output;
    StringBuilder sb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_dialog_box);

        Resources res = getResources();
        String[] cities = res.getStringArray(R.array.city);

        sb = new StringBuilder();

        alert = findViewById(R.id.alert);
        output = findViewById(R.id.output);

        for (String city : cities) {
            sb.append("\n").append(city);
        }

        output.setText(sb.toString());

        alertDialog = new AlertDialog.Builder(this).create();

        alert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.setTitle("ALERT BOX-");
                alertDialog.setMessage(sb.toString());
                alertDialog.setCancelable(true);

                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        alertDialog.dismiss();
                    }
                });

                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        onStart();
                    }
                });
                alertDialog.show();
            }
        });
    }
}
