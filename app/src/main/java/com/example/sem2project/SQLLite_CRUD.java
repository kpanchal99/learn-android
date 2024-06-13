package com.example.sem2project;

import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SQLLite_CRUD extends AppCompatActivity {
    EditText email, pwd, rollno, cid, course, city;
    Button register, show, search, clear, update, delete;
    DataHelper databaseHelper;
    TextView content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqllite_crud);

        email = findViewById(R.id.email);
        pwd = findViewById(R.id.pwd);
        register = findViewById(R.id.register);
        show = findViewById(R.id.show);
        search = findViewById(R.id.search);
        rollno = findViewById(R.id.rollno);
        clear = findViewById(R.id.clear);
        cid = findViewById(R.id.cid);
        course = findViewById(R.id.course);
        city = findViewById(R.id.city);
        update = findViewById(R.id.update);
        delete = findViewById(R.id.delete);

        databaseHelper = new DataHelper(this);
        Toast.makeText(getApplicationContext(), "DB instance created", Toast.LENGTH_LONG).show();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (allFieldsFilled()) {
                    boolean res = databaseHelper.insertUser(email.getText().toString(), pwd.getText().toString(), cid.getText().toString(), course.getText().toString(), city.getText().toString());
                    showMessage(res ? "Record inserted" : "Insert failed");
                } else {
                    showMessage("Please fill all fields");
                }
            }
        });

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAllRecords();
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchRecord();
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearFields();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (allFieldsFilled()) {
                    boolean res = databaseHelper.updateRecord(Integer.parseInt(rollno.getText().toString()), email.getText().toString(), pwd.getText().toString(), cid.getText().toString(), course.getText().toString(), city.getText().toString());
                    showMessage(res ? "Record updated" : "Update failed");
                } else {
                    showMessage("Please fill all fields");
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rollno.getText().toString() != "") {
                    boolean res = databaseHelper.deleteRecord(Integer.parseInt(rollno.getText().toString()));
                    showMessage(res ? "Record deleted" : "Delete failed");
                } else {
                    showMessage("Please fill all fields");
                }
            }
        });
    }

    private boolean allFieldsFilled() {
        return !email.getText().toString().isEmpty() && !pwd.getText().toString().isEmpty() && !rollno.getText().toString().isEmpty() && !cid.getText().toString().isEmpty() && !course.getText().toString().isEmpty() && !city.getText().toString().isEmpty();
    }

    private void showMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

    private void clearFields() {
        email.setText("");
        pwd.setText("");
        rollno.setText("");
        cid.setText("");
        course.setText("");
        city.setText("");
    }

    private void showAllRecords() {
        StringBuilder records = new StringBuilder();
        Cursor result = databaseHelper.showRecords();
        records.append("Table has: ").append(result.getCount()).append(" records!\n");
        while (result.moveToNext()) {
            records.append("user-id: ").append(result.getString(0)).append("\n");
            records.append("user-email: ").append(result.getString(1)).append("\n");
            records.append("user-password: ").append(result.getString(2)).append("\n");
            records.append("user-cid: ").append(result.getString(3)).append("\n");
            records.append("user-course: ").append(result.getString(4)).append("\n");
            records.append("user-city: ").append(result.getString(5)).append("\n");
            records.append("\n");
        }
        Toast.makeText(SQLLite_CRUD.this, records.toString(), Toast.LENGTH_LONG).show();
    }

    private void searchRecord() {
        Cursor record = databaseHelper.searchRecord(Integer.parseInt(rollno.getText().toString()));
        if (record.getCount() == 0) {
            Toast.makeText(SQLLite_CRUD.this, "No Record Found !!", Toast.LENGTH_SHORT).show();
        } else {
            while (record.moveToNext()) {
                email.setText(record.getString(1));
                pwd.setText(record.getString(2));
                cid.setText(record.getString(3));
                course.setText(record.getString(4));
                city.setText(record.getString(5));
            }
        }
    }
}
