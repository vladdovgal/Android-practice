package com.dovhal.p0371sqliteinnerjoin;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public String LOG_TAG = "myLogs";
    Button btnWorkers, btnPositions, btnJoinRaw, btnJoinQuery;
    ListView listView;

    // workers
    String[] names = {"Vlad", "John", "Peter", "Michael", "Dan", "Mark", "Elizabeth", "Victoria", "Kevin"};
    int[] ids = {0, 2, 3, 1, 1, 1, 2, 3, 1};
    List<String> resultList = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnWorkers = findViewById(R.id.btnWorkers);
        btnPositions = findViewById(R.id.btnPositions);
        btnJoinQuery = findViewById(R.id.btnJoinQuery);
        btnJoinRaw = findViewById(R.id.btnJoinRaw);

        btnWorkers.setOnClickListener(this);
        btnJoinQuery.setOnClickListener(this);
        btnPositions.setOnClickListener(this);
        btnJoinRaw.setOnClickListener(this);

        listView = findViewById(R.id.lvResult);
    }

    // cursor output realisation
    void logCursor(Cursor cursor){
        if (cursor != null){
            if (cursor.moveToFirst()){
                String temp;
                resultList.clear();
                do {
                    temp = "";
                    for (String colName : cursor.getColumnNames()){
                        temp = temp.concat(colName + " = " + cursor.getString(
                                cursor.getColumnIndex(colName)) + " , ");
                    }
                    resultList.add(temp);
                    Log.d(LOG_TAG, temp);
                } while (cursor.moveToNext());
            }
        } else Log.d(LOG_TAG, "-- Cursor is null");
    }

    @Override
    public void onClick(View v) {
        // connect to DB
        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        // init cursor
        Cursor cursor;
        switch (v.getId()){
            case R.id.btnWorkers:
                // output workers table
                Log.d(LOG_TAG,"-- Workers --");
                cursor = database.query("workers", null, null,
                        null, null, null, null);
                logCursor(cursor);
                cursor.close();
                Log.d(LOG_TAG,"----");
                break;
            case R.id.btnPositions:
                // output positions table
                Log.d(LOG_TAG,"-- Positions --");
                cursor = database.query("positions", null, null,
                        null, null, null, null);
                logCursor(cursor);
                cursor.close();
                Log.d(LOG_TAG,"----");
                break;
            case R.id.btnJoinRaw:
                // inner join using raw query
                Log.d(LOG_TAG,"-- Inner Join using rawQuery --");
                String sqlQuery = "select W.name as name, P.name as position, P.salary as salary " +
                        "from workers as W " +
                        "inner join positions as P " +
                        "on P.id = W.positionId " +
                        "where salary > ? ";
                cursor = database.rawQuery(sqlQuery, new String[] {"1000"});
                logCursor(cursor);
                cursor.close();
                Log.d(LOG_TAG, "-----");
                break;
            case R.id.btnJoinQuery:
                // inner join using query
                Log.d(LOG_TAG,"-- Inner Join using query --");
                String table = "workers AS W INNER JOIN positions AS P ON W.positionId = P.id";
                String[] columns = {"W.name as Worker", "P.name as Position", "P.salary as Salary"};
                String selection = "salary > ?";
                String[] selectionArgs = {"0"};
                cursor = database.query(table, columns,
                        selection, selectionArgs,null,null, null);
                logCursor(cursor);
                cursor.close();
                Log.d(LOG_TAG, "-----");
                break;
            default:
                break;
        }

        // write result to ListView
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, resultList
        );
        listView.setAdapter(stringArrayAdapter);
        dbHelper.close();

    }

    // class for work with DB
    class DBHelper extends SQLiteOpenHelper{

        public DBHelper(@Nullable Context context) {
            super(context, "myDatabase", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.d(LOG_TAG, "-- Database created --");

            ContentValues contentValues = new ContentValues();

            // create Positions table
            db.execSQL("CREATE TABLE positions (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "name TEXT," +
                    "salary INTEGER);");

            // create Workers table
            db.execSQL("CREATE TABLE workers(" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "name TEXT," +
                    "positionId INTEGER);");

            // fill in Positions table
            for (Position position : Position.values()){
                contentValues.clear();
                contentValues.put("id", position.ordinal());
                contentValues.put("name", position.name());
                contentValues.put("salary", position.getSalary());
                db.insert("positions", null, contentValues);
            }

            // fill in Workers table
            for (int i = 0; i < names.length; i++) {
                contentValues.clear();
                contentValues.put("name", names[i]);
                contentValues.put("positionId", ids[i]);
                db.insert("workers", null, contentValues);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }

}


