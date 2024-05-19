package com.example.sem2project;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Calculator extends AppCompatActivity {

    Button btn;
    EditText num1, num2, operation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        btn = findViewById(R.id.btn);
        num1 = findViewById(R.id.num1);
        num2 = findViewById(R.id.num2);
        operation = findViewById(R.id.operation);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String op = operation.getText().toString();
                int n1 = Integer.parseInt(num1.getText().toString());
                int n2 = Integer.parseInt(num2.getText().toString());
                if(op.equals("+")){
                    Toast.makeText(getApplicationContext(), "Addition of num " + ( n1 + n2),Toast.LENGTH_LONG).show();
                } else if (op.equals("-")) {
                    Toast.makeText(getApplicationContext(), "Addition of num " + ( n1 - n2),Toast.LENGTH_LONG).show();
                } else if (op.equals("%")) {
                    Toast.makeText(getApplicationContext(), "Addition of num " + ( n1 % n2),Toast.LENGTH_LONG).show();
                } else if (op.equals("*")) {
                    Toast.makeText(getApplicationContext(), "Addition of num " + ( n1 * n2),Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Select correct operator",Toast.LENGTH_LONG).show();
                }
            }
        });


    }
}
