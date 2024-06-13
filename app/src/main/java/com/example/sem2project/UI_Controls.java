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
import android.widget.ArrayAdapter;

public class UI_Controls extends AppCompatActivity {

    AutoCompleteTextView cityAutoCompleteTextView;
    MultiAutoCompleteTextView degreeAutoCompleteTextView;
    EditText dobEditText;
    CheckBox sscCheckBox, hscCheckBox, ugCheckBox;
    RadioGroup mealPreferenceRadioGroup;
    Spinner universitySpinner;
    Button registerButton;
    TextView detailsTextView;
    StringBuilder output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui_controls);

        cityAutoCompleteTextView = findViewById(R.id.city);
        degreeAutoCompleteTextView = findViewById(R.id.degree);
        dobEditText = findViewById(R.id.dob);
        sscCheckBox = findViewById(R.id.ssc);
        hscCheckBox = findViewById(R.id.hsc);
        ugCheckBox = findViewById(R.id.ug);
        mealPreferenceRadioGroup = findViewById(R.id.rg);
        universitySpinner = findViewById(R.id.university);
        registerButton = findViewById(R.id.register);
        detailsTextView = findViewById(R.id.details);

        // Set up AutoCompleteTextView for cities
        String[] cities = getResources().getStringArray(R.array.city);
        ArrayAdapter<String> cityAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, cities);
        cityAutoCompleteTextView.setAdapter(cityAdapter);

        // Set up MultiAutoCompleteTextView for degrees
        String[] degrees = getResources().getStringArray(R.array.degree);
        ArrayAdapter<String> degreeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, degrees);
        degreeAutoCompleteTextView.setAdapter(degreeAdapter);
        degreeAutoCompleteTextView.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());

        // Set up Spinner for universities
        String[] universities = getResources().getStringArray(R.array.university);
        ArrayAdapter<String> universityAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, universities);
        universityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        universitySpinner.setAdapter(universityAdapter);

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
        output.append("Registration Details:\n");

        //  city
        String city = cityAutoCompleteTextView.getText().toString();
        output.append("City: ").append(city).append("\n");

        // degrees
        String degrees = degreeAutoCompleteTextView.getText().toString();
        output.append("Degrees: ").append(degrees).append("\n");

        //  DOB
        String dob = dobEditText.getText().toString();
        output.append("Date of Birth: ").append(dob).append("\n");

        // education
        output.append("Education Levels: ");
        if (sscCheckBox.isChecked()) output.append("SSC ");
        if (hscCheckBox.isChecked()) output.append("HSC ");
        if (ugCheckBox.isChecked()) output.append("UG ");
        output.append("\n");

        // food
        int selectedMealId = mealPreferenceRadioGroup.getCheckedRadioButtonId();
        String mealPreference = "";
        if (selectedMealId == R.id.veg) mealPreference = "Veg";
        else if (selectedMealId == R.id.non_veg) mealPreference = "Non-Veg";
        else if (selectedMealId == R.id.both) mealPreference = "Both";
        output.append("Meal Preference: ").append(mealPreference).append("\n");

        // university
        String university = universitySpinner.getSelectedItem().toString();
        output.append("University: ").append(university).append("\n");

        // show
        detailsTextView.setText(output.toString());
    }
}
