package com.mapps.encoder;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class MyDatabase extends SQLiteOpenHelper {

    private static final String CREATE_TABLE = "CREATE TABLE user(_id INTEGER PRIMARY KEY, Name TEXT);";

    public MyDatabase(Context context) {
        super(context, "Encrypt", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}