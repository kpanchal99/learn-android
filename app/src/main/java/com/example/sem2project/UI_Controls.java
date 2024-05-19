package com.example.sem2project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class UI_Controls extends AppCompatActivity {

    AutoCompleteTextView cityAutoCompleteTextView;
    MultiAutoCompleteTextView degreeAutoCompleteTextView;
    EditText dobEditText;
    CheckBox sscCheckBox, hscCheckBox, ugCheckBox;
    RadioGroup mealPreferenceRadioGroup;
    RadioButton vegRadioButton, nonVegRadioButton, bothRadioButton;
    Spinner universitySpinner;
    Button registerButton;
    TextView detailsTextView;
    StringBuilder output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui_controls);

        cityAutoCompleteTextView = findViewById(R.id.city);
        // Initialize and set adapter for cityAutoCompleteTextView

        degreeAutoCompleteTextView = findViewById(R.id.degree);
        // Initialize and set adapter for degreeAutoCompleteTextView
        // Set MultiAutoCompleteTextView tokenizer

        dobEditText = findViewById(R.id.dob);
        sscCheckBox = findViewById(R.id.ssc);
        hscCheckBox = findViewById(R.id.hsc);
        ugCheckBox = findViewById(R.id.ug);
        mealPreferenceRadioGroup = findViewById(R.id.rg);
        vegRadioButton = findViewById(R.id.veg);
        nonVegRadioButton = findViewById(R.id.non_veg);
        bothRadioButton = findViewById(R.id.both);
        universitySpinner = findViewById(R.id.university);
        // Initialize and set adapter for universitySpinner
        // Set spinner prompt or default selection

        registerButton = findViewById(R.id.register);
        detailsTextView = findViewById(R.id.details);
        output = new StringBuilder();

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerButtonClick();
            }
        });
    }

    private void registerButtonClick() {
        output.setLength(0);
        output.append("Registration: \n");
        // Append other registration details based on user input
        detailsTextView.setText(output.toString());
    }
}
