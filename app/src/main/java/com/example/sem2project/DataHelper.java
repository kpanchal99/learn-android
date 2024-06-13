package com.example.sem2project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "mca202325.db";
    private static final String TABLE_NAME = "MCAFY";
    private static final int DATABASE_VERSION = 1;
    private SQLiteDatabase database;

    public DataHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        database = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
                "rollno INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "email TEXT, " +
                "password TEXT, " +
                "cid TEXT, " +
                "course TEXT, " +
                "city TEXT)";
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        // Handle database upgrade as needed
    }

    public boolean insertUser(String email, String pwd, String cid, String course, String city) {
        ContentValues cv = new ContentValues();
        cv.put("email", email);
        cv.put("password", pwd);
        cv.put("cid", cid);
        cv.put("course", course);
        cv.put("city", city);
        long res = database.insert(TABLE_NAME, null, cv);
        return res != -1;
    }

    public Cursor showRecords() {
        return database.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }

    public Cursor searchRecord(int rollno) {
        return database.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE rollno=" + rollno, null);
    }

    public boolean updateRecord(int rollno, String email, String pwd, String cid, String course, String city) {
        ContentValues cv = new ContentValues();
        cv.put("email", email);
        cv.put("password", pwd);
        cv.put("cid", cid);
        cv.put("course", course);
        cv.put("city", city);
        long res = database.update(TABLE_NAME, cv, "rollno=?", new String[]{String.valueOf(rollno)});
        return res != -1;
    }

    public boolean deleteRecord(int rollno) {
        long res = database.delete(TABLE_NAME, "rollno=?", new String[]{String.valueOf(rollno)});
        return res != -1;
    }
}
