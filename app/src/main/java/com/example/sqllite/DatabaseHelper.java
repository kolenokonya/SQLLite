package com.example.sqllite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(@Nullable Context context) {
        super(context, "UserData.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create Table UserInfo(name Text primary key, " + "phone Text, dateOfBirth Text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop Table if exists UserInfo");
    }

    public Boolean insert(String name, String phone, String dateOfBirth) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("phone", phone);
        contentValues.put("dateOfBirth", dateOfBirth);
        long result = DB.insert("UserInfo", null, contentValues);
        return result != -1;
    }

    public Boolean update(String name, String phone, String dateOfBirth) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("phone", phone);
        contentValues.put("dateOfBirth", dateOfBirth);
        long result = DB.update("UserInfo", contentValues, "name=?", new String[]{name});
        return result != -1;
    }

    public Boolean delete(String name) {
        SQLiteDatabase DB = this.getWritableDatabase();
        long result = DB.delete("UserInfo", "name=?", new String[]{name});
        return result != -1;
    }

    public Cursor getData() {
        SQLiteDatabase DB = this.getWritableDatabase();
        return DB.rawQuery("Select * from UserInfo", null);
    }
}
