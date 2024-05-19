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
    EditText email,pwd,rollno,cid,course,city;
    Button register, show, search, clear,update,delete;
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
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!email.getText().toString().isEmpty() && !pwd.getText().toString().isEmpty() && !course.getText().toString().isEmpty() && !cid.getText().toString().isEmpty() && !city.getText().toString().isEmpty()){
                    boolean res = databaseHelper.insertUser(email.getText().toString(),pwd.getText().toString(),cid.getText().toString(),course.getText().toString(),city.getText().toString());
                    if (res == true)
                        Toast.makeText(getApplicationContext(), "Record inserted" , Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(getApplicationContext(), "Failed" , Toast.LENGTH_LONG).show();

                }

            }
        });

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuilder records = new StringBuilder();
                Cursor result = databaseHelper.showRecords();
                records.append("Table has: "+result.getCount() + "records! \n"); while (result.moveToNext())
                {
                    records.append("user-id: "+result.getString(0));
                    records.append("user-email: "+ result.getString(1));
                    records.append("user-password: "+ result.getString(2));
                    records.append("user-cid: "+ result.getString(3));
                    records.append("user-course: "+ result.getString(4));
                    records.append("user-city: "+ result.getString(5));
                    records.append("\n");
                    records.append("\n");
                }

                Toast.makeText(SQLLite_CRUD.this, records, Toast.LENGTH_LONG).show(); records.append(" ");
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor record = databaseHelper.searchRecord(Integer.parseInt(rollno.getText().toString()));
                if(record.getCount()==0)
                    Toast.makeText(SQLLite_CRUD.this,"No Record Found !!",Toast.LENGTH_SHORT);
                else
                {
                    while (record.moveToNext())
                    {
                        email.setText(record.getString(1));
                        pwd.setText(record.getString(2));
                        cid.setText(record.getString(3));
                        course.setText(record.getString(4));
                        city.setText(record.getString(5));

                    }
                }

            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email.setText("");
                pwd.setText("");
                rollno.setText("");
                cid.setText("");
                course.setText("");
                city.setText("");

            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!email.getText().toString().isEmpty() && !pwd.getText().toString().isEmpty() && !course.getText().toString().isEmpty() && !cid.getText().toString().isEmpty() && !city.getText().toString().isEmpty()){
                    boolean res = databaseHelper.updateRecord(Integer.parseInt(rollno.getText().toString()),email.getText().toString(),pwd.getText().toString(),cid.getText().toString(),course.getText().toString(),city.getText().toString());
                    if (res == true)
                        Toast.makeText(getApplicationContext(), "Record updated" , Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(getApplicationContext(), "Failed" , Toast.LENGTH_LONG).show();

                }
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!email.getText().toString().isEmpty() && !pwd.getText().toString().isEmpty() && !course.getText().toString().isEmpty() && !cid.getText().toString().isEmpty() && !city.getText().toString().isEmpty()){
                    boolean res = databaseHelper.deleteRecord(Integer.parseInt(rollno.getText().toString()),email.getText().toString(),pwd.getText().toString(),cid.getText().toString(),course.getText().toString(),city.getText().toString());
                    if (res == true)
                        Toast.makeText(getApplicationContext(), "Record deleted" , Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(getApplicationContext(), "Failed" , Toast.LENGTH_LONG).show();

                }
            }
        });
        databaseHelper = new DataHelper(this);
        Toast.makeText(getApplicationContext(), "DB instance created" , Toast.LENGTH_LONG).show();
    }
}
