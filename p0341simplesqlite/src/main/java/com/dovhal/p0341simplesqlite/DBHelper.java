package com.dovhal.p0341simplesqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(@Nullable Context context) {
        super(context, "myDb", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(MainActivity.LOG_TAG, "---- onCreate() database -----");
        db.execSQL("CREATE TABLE my_table("
        + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
        + "name TEXT, "
        + "email TEXT " + ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
