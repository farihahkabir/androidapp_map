package com.example.testmap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.IOException;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String  DATABASE_NAME = "envDatabase";
    public static final String  CONTACTS_TABLE_NAME = "aqiInfo";

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            db.execSQL(
                    "create table "  + CONTACTS_TABLE_NAME + "(id INTEGER PRIMARY KEY, aqi INTEGER, name text, gmt text, lat real, longitude real, co real, no2 real, o3 real, so2 real)"
            );
        } catch (SQLiteException e){
            try  {
                throw new IOException(e);
            } catch  (IOException e1){
                e1.printStackTrace();
            }
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CONTACTS_TABLE_NAME);
        onCreate(db);
    }

    public boolean insert(String name, int aqi, String gmt, Float lat, Float longitude, Float co, Float no2, Float o3, Float so2){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("aqi", aqi);
        contentValues.put("gmt", gmt);
        contentValues.put("lat", lat);
        contentValues.put("longitude", longitude);
        contentValues.put("co", co);
        contentValues.put("no2", no2);
        contentValues.put("o3", o3);
        contentValues.put("so2", so2);
        db.replace(CONTACTS_TABLE_NAME, null, contentValues);
        return true;
    }

    public ArrayList getAllInfo(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> array_list = new ArrayList<String>();
        Cursor res = db.rawQuery("select * from  " + CONTACTS_TABLE_NAME , null);
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex("name")));
            res.moveToNext();
        }
        return array_list;
    }

    public boolean update(String name, int aqi, String gmt, Float lat, Float longitude, Float co, Float no2, Float o3, Float so2){
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("UPDATE " + CONTACTS_TABLE_NAME + " SET name="+"'"+name+"'"+"WHERE aqi="+"'"+aqi+"'");
        return true;
    }
}
