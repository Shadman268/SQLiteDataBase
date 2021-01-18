package com.example.sqlitedatabasedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Student.db";
    private static final String TABLE_NAME = "student_details";
    private static final int VERSION_NUMBER = 1;

    private static final String ID = "_id";
    private static final String NAME = "Name";
    private static final String AGE = "Age";
    private static final String GENDER = "Gender";
    private static final String CREATE_TABLE = "CREATE TABLE " +TABLE_NAME+ "("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +NAME+ "  VARCHAR(255)," +AGE+ " INTEGER ," +GENDER+ " VARCHAR(15)); ";
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " +TABLE_NAME;

    private Context context;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION_NUMBER);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try
        {
            Toast.makeText(context,"onCreate is called" ,Toast.LENGTH_SHORT).show();
            db.execSQL(CREATE_TABLE);
        }
        catch (Exception e)
        {
            Toast.makeText(context,"Exception: "+e ,Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        try
        {
            Toast.makeText(context,"onUpgrade is called" ,Toast.LENGTH_SHORT).show();
            db.execSQL(DROP_TABLE);
            onCreate(db);
        }
        catch (Exception e)
        {
            Toast.makeText(context,"Exception: "+e ,Toast.LENGTH_SHORT).show();
        }


    }

    public long insertData(String name,String age,String gender)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Name",name);
        contentValues.put("Age",age);
        contentValues.put("Gender",gender);
        long rowId = db.insert(TABLE_NAME,null,contentValues);
        return rowId;
    }
}
