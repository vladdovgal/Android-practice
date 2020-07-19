package com.dovhal.p0381sqlitetransaction;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    public static final String LOG_TAG = "myLogs";

    DBHelper dbHelper;
    SQLiteDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(LOG_TAG, " -- onCreate activity -- ");
        dbHelper = new DBHelper(this);
        actions();
    }

    private void actions() {
        Log.d(LOG_TAG, " -- Actions start -- ");

        try (SQLiteDatabase database = dbHelper.getWritableDatabase()) {
            delete(database , "testTable");
            database.beginTransaction();
            insert(database, "testTable", "val1");
            database.setTransactionSuccessful();
            insert(database, "testTable", "val2");
            database.endTransaction();
            insert(database, "testTable", "val3");
            read(database, "testTable");
        } catch (Exception e){
            Log.d(LOG_TAG, " !-- Something went wrong --! ");
        }


    }

    private void delete(SQLiteDatabase database, String table) {
        Log.d(LOG_TAG, " -- Deleting from table -- ");
        database.delete(table, null, null);
    }

    private void insert(SQLiteDatabase database, String table, String value) {
        Log.d(LOG_TAG, " -- Inserting into table -- ");
        ContentValues contentValues = new ContentValues();
        contentValues.put("val", value);
        database.insert(table, null, contentValues);
    }

    private void read(SQLiteDatabase database, String table) {
        Log.d(LOG_TAG, " -- Reading table -- ");
        Cursor cursor;
        cursor = database.query(table, null, null,
                null, null, null, null);
        if (cursor != null) {
            Log.d(LOG_TAG, "Records count: " + cursor.getCount());
            if (cursor.moveToFirst()){
                do {
                    Log.d(LOG_TAG, cursor.getString(cursor.getColumnIndex("val")));
                } while (cursor.moveToNext());
            }
        cursor.close();
        }
    }
}
