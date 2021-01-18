package com.example.sqlitedatabasedemo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText nameEditText,ageEditText,genderEditText;
    private Button addBtn,displayDataBtn;

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
        displayDataBtn = (Button) findViewById(R.id.DisplayDataButtonId);

        addBtn.setOnClickListener(this);
        displayDataBtn.setOnClickListener(this);
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

        if(v.getId() == R.id.DisplayDataButtonId)
        {
            Cursor cursor = databaseHelper.displayData();

            if(cursor.getCount() == 0)
            {
                showData("error","No data found");
                return;
            }
            StringBuffer stringBuffer = new StringBuffer();
            while (cursor.moveToNext())
            {
                stringBuffer.append(" ID: " +cursor.getString(0)+" \n ");
                stringBuffer.append(" Name: " +cursor.getString(1)+" \n ");
                stringBuffer.append(" Age: " +cursor.getString(2)+" \n ");
                stringBuffer.append(" Gender: " +cursor.getString(3)+" \n ");
            }

            showData("ResultSet",stringBuffer.toString());
        }

    }

    public void showData(String title,String message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setCancelable(true);
        builder.show();

    }
}