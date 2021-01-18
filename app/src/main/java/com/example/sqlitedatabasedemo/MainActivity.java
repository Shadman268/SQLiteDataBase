package com.example.sqlitedatabasedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText nameEditText,ageEditText,genderEditText;
    private Button addBtn;

    DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        nameEditText = (EditText) findViewById(R.id.NameEditTextId);
        ageEditText = (EditText) findViewById(R.id.AgeEditTextId);
        genderEditText = (EditText) findViewById(R.id.GenderEditTextId);
        addBtn = (Button) findViewById(R.id.AddButtonId);

        addBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        String name = nameEditText.getText().toString().trim();
        String age = ageEditText.getText().toString().trim();
        String gender = genderEditText.getText().toString().trim();

        if(v.getId() == R.id.AddButtonId)
        {
            long rowId = databaseHelper.insertData(name,age,gender);
            if(rowId==-1)
            {
                Toast.makeText(getApplicationContext(),"Unsuccessful",Toast.LENGTH_SHORT).show();

            }
            else
            {
                Toast.makeText(getApplicationContext(),"Row "+rowId+" Successfully inserted",Toast.LENGTH_SHORT).show();
            }

        }

    }
}