package com.example.sem2project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class DataHelper extends SQLiteOpenHelper {

    SQLiteDatabase database;
    public DataHelper(@Nullable Context context) {
        super(context,"mca202325.db",null,1);
//        sqLiteDatabase = getWritableDatabase();
        database = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
//        sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL("create table MCAFY(rollno integer primary key autoincrement,email text,password text,cid text,course text,city text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public boolean insertUser(String email,String pwd,String cid,String course,String city){
        ContentValues cv = new ContentValues();
        cv.put("email",email);
        cv.put("password",pwd);
        cv.put("cid",cid);
        cv.put("course", course);
        cv.put("city",city);
        long res = database.insert("MCAFY",null, cv);
        if (res == -1)
            return false;
        else
            return true;
    }
    public Cursor showRecords(){
        Cursor result  = database.rawQuery("select * from MCAFY", null);
        if(result.getCount()!=0)
            return result;
        else
            return result;
    }

    public Cursor searchRecord(int rollno) {
        Cursor result  = database.rawQuery("select * from MCAFY where rollno=" + rollno , null);
        if(result.getCount()>0)
            return result;
        else
            return result;
    }
    public boolean updateRecord(int rollno, String email,String pwd, String cid, String course,String city){
        ContentValues cv = new ContentValues();
        cv.put("email",email);
        cv.put("password",pwd);
        cv.put("cid",cid);
        cv.put("course",course);
        cv.put("city",city);
        long res = database.update("MCAFY",cv,"rollno=?",new String[]{String.valueOf(rollno)});

        if (res != -1)
            return true;
        else
            return false;
    }
    public boolean deleteRecord(int rollno, String email,String pwd, String cid, String course,String city){
        ContentValues cv = new ContentValues();
        cv.put("email",email);
        cv.put("password",pwd);
        cv.put("cid",cid);
        cv.put("course",course);
        cv.put("city",city);
        long res = database.delete("MCAFY","rollno=?",new String[]{String.valueOf(rollno)});
        if (res != -1)
            return true;
        else
            return false;
    }
}
