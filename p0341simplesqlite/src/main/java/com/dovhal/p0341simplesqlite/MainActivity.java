package com.dovhal.p0341simplesqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String LOG_TAG = "myLogs";

    EditText etName, etEmail, etId;
    Button btnWrite, btnRead, btnClear, btnUpdate, btnDelete;
    ListView listView;

    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etId = findViewById(R.id.etId);
        listView = findViewById(R.id.lvUsers);

        btnWrite = findViewById(R.id.btnWrite);
        btnWrite.setOnClickListener(this);

        btnRead = findViewById(R.id.btnRead);
        btnRead.setOnClickListener(this);

        btnClear = findViewById(R.id.btnClear);
        btnClear.setOnClickListener(this);

        btnUpdate = findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(this);

        btnDelete = findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(this);

        // object for creating and managing database
        dbHelper = new DBHelper(this);
    }

    @Override
    public void onClick(View v) {
        // object for data storage
        ContentValues contentValues = new ContentValues();

        // getting values from EditText
        String name = etName.getText().toString();
        String email = etEmail.getText().toString();
        String id = etId.getText().toString();

        // connect to DB
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        switch (v.getId()){
            case R.id.btnWrite:
                Log.d(LOG_TAG, "-- Insert in my_table");

                // prepare data for insertion
                contentValues.put("name", name);
                contentValues.put("email", email);

                long rowId = database.insert("my_table", null, contentValues);
                Log.d(LOG_TAG, "-- Row inserted, row id = " + rowId + " --");
                Toast.makeText(this, "Added", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnRead:
                List<User> users = new LinkedList<>();
                Log.d(LOG_TAG, "-- Rows in table: --");

                // Execute query to get all data and getting Cursor
                Cursor cursor = database.query("my_table", null, null, null,
                        null, null, null);

                // set Cursor position on first row
                // if selection is empty, returns false
                if (cursor.moveToFirst()){
                    // determine number of column by name
                    int idIndex = cursor.getColumnIndex("id");
                    int nameIndex = cursor.getColumnIndex("name");
                    int emailIndex = cursor.getColumnIndex("email");

                    do {
                        User user = new User(cursor.getInt(idIndex), cursor.getString(nameIndex),
                                cursor.getString(emailIndex));
                        users.add(user);
                        Log.d(LOG_TAG,"ID = " + cursor.getInt(idIndex) +
                                ", name = " + cursor.getString(nameIndex) +
                                ", email = " + cursor.getString(emailIndex));
                        // go to next row
                        // if next row is absent, returns false
                    } while (cursor.moveToNext());
                } else {
                    Log.d(LOG_TAG, "-- Table is empty -- ");
                }
                // ArrayAdapter is used to write data from ArrayList to ListView
                ArrayAdapter<User> userArrayAdapter = new ArrayAdapter<User>(
                        this, android.R.layout.simple_list_item_1, users
                );
                // Setting adapter for ListView-element
                listView.setAdapter(userArrayAdapter);
                cursor.close();
                break;
            case R.id.btnClear:
                Log.d(LOG_TAG, " -- Clear table -- ");
                int clearCount = database.delete("my_table", null, null);
                Toast.makeText(this, clearCount + " rows cleared",
                        Toast.LENGTH_SHORT).show();
                Log.d(LOG_TAG, clearCount + " rows cleared");
                break;
            case R.id.btnUpdate:
                if (id.equalsIgnoreCase("")){
                    break;
                }
                Log.d(LOG_TAG, "-- Update my_table --");
                // Prepare data
                contentValues.put("name", name);
                contentValues.put("email", email);
                // Update by id
                int updCount = database.update("my_table",
                        contentValues, "id = ?", new String[]{id});
                Toast.makeText(this, "User with id = " + updCount + " was updated",
                        Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnDelete:
                if (id.equalsIgnoreCase("")){
                    break;
                }
                Log.d(LOG_TAG, "-- Delete from my_table --");
                // Delete by id
                int delCount = database.delete("my_table", "id = " + id, null);
                Toast.makeText(this, "User with id = " + delCount + " was deleted",
                        Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        dbHelper.close();
    }
}
