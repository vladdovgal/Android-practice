package com.dovhal.p0361sqlitequery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private final String LOG_TAG = "myLogs";

    String names[] = {"Китай", "США", "Бразилия", "Россия", "Япония",
            "Германия", "Египет", "Италия", "Франция", "Канада"};
    int people[] = {1400, 311, 195, 142, 128, 82, 80, 60, 66, 35};
    String region[] = {"Азия", "Америка", "Америка", "Европа", "Азия",
            "Европа", "Африка", "Европа", "Европа", "Америка"};

    private Button btnAll, btnFunc, btnPopulation, btnContinent, btnPopulationCont, btnSort;
    private EditText etPopulationMore, etPopulationLess, etContinentMore,
            etContinentLess, etFunction;
    private RadioGroup rgSort;
    private ListView lvCountries;

    private DBHelper dbHelper;
    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialisation block
        btnAll = findViewById(R.id.btnAll);
        btnAll.setOnClickListener(this);

        btnFunc = findViewById(R.id.btnFunction);
        btnFunc.setOnClickListener(this);

        btnPopulation = findViewById(R.id.btnPopulation);
        btnPopulation.setOnClickListener(this);

        btnContinent = findViewById(R.id.btnContinent);
        btnContinent.setOnClickListener(this);

        btnPopulationCont = findViewById(R.id.btnPopulationCont);
        btnPopulationCont.setOnClickListener(this);

        btnSort = findViewById(R.id.btnSort);
        btnSort.setOnClickListener(this);

        etPopulationMore = findViewById(R.id.etPopulationMore);
        etPopulationLess = findViewById(R.id.etPopulationLess);
        etContinentMore = findViewById(R.id.etContinentMore);
        etContinentLess = findViewById(R.id.etContinentLess);
        etFunction = findViewById(R.id.etFunction);

        rgSort = findViewById(R.id.rgSort);
        lvCountries = findViewById(R.id.lvCountries);

        dbHelper = new DBHelper(this);
        // connecting to DB
        database = dbHelper.getWritableDatabase();

        // make list of countries entities
        List<Country> allCountries = new LinkedList<>();
        for (int i = 0; i < names.length; i++) {
            Country country = new Country(names[i], people[i], region[i]);
            allCountries.add(country);
            Log.d(LOG_TAG,  country.toString());
        }

        // check if table is not empty
        Cursor cursor = database.query("countries", null, null,
                null, null, null, null);
        if (cursor.getCount() != 0) {
            ContentValues values = new ContentValues();
            // fill table from allCountries List
            for (Country country : allCountries) {
                values.put("id", country.getId());
                values.put("name", country.getName());
                values.put("population", country.getPopulation());
                values.put("continent", country.getRegion());
                Log.d(LOG_TAG, "id = " + database.insert("countries",
                        null, values));
            }
        }
        cursor.close();
        dbHelper.close();
        // emulating press of btnAll
        onClick(btnAll);
    }

    @Override
    public void onClick(View v) {
        // connect to db
        database = dbHelper.getWritableDatabase();

        // getting data from EditTextFields
        String populationLeft = etPopulationMore.getText().toString();
        String populationRight = etPopulationLess.getText().toString();
        String contPopLeft = etContinentMore.getText().toString();
        String contPopRight = etContinentLess.getText().toString();
        String function = etFunction.getText().toString();

        // variables for query
        String[] columns = null;
        String selection = null;
        String[] selectionArgs = null;
        String groupBy = null;
        String having = null;
        String orderBy = null;

        // cursor
        Cursor cursor = null;

        // determine button pressed;
        switch (v.getId()) {
            // all data
            case R.id.btnAll:
                Log.d(LOG_TAG, "-- All countries --");
                cursor = database.query("countries", null, null,
                        null, null, null, null);
                break;
            // function
            case R.id.btnFunction:
                Log.d(LOG_TAG, "-- Function " + function + " --");
                columns = new String[]{function};
                cursor = database.query("countries", columns, null,
                        null, null, null, null);
                break;
            // population FROM ... TO ...
            case R.id.btnPopulation:
                Log.d(LOG_TAG, "-- Population from " + populationLeft + "to" +
                        populationRight + "--");
                selection = "population > ? AND population < ?";
                selectionArgs = new String[]{populationLeft, populationRight};
                cursor = database.query("countries", null, selection, selectionArgs,
                        null, null, null);
                break;
            // population by continent
            case R.id.btnContinent:
                Log.d(LOG_TAG, " -- Population by continent -- ");
                columns = new String[]{"continent", "sum(population) as population"};
                groupBy = "continent";
                cursor = database.query("countries", columns, null,
                        null, groupBy, null, null);
                break;
            // population by continent FROM ... TO ...
            case R.id.btnPopulationCont:
                Log.d(LOG_TAG, " -- Population by continent -- ");
                columns = new String[]{"continent", "sum(population) as population"};
                groupBy = "continent";
                having = "sum(population) > " + contPopLeft + " and sum(population) < " + contPopRight;
                cursor = database.query("countries", columns, null, null,
                        groupBy, having, null);
                break;
            // sorting
            case R.id.btnSort:
                // sort by ?
                switch (rgSort.getCheckedRadioButtonId()) {
                    case R.id.rbName:
                        Log.d(LOG_TAG, " -- Sort by name -- ");
                        orderBy = "name";
                        break;
                    case R.id.rbContinent:
                        Log.d(LOG_TAG, " -- Sort by continent -- ");
                        orderBy = "continent";
                        break;
                    case R.id.rbPopulation:
                        Log.d(LOG_TAG, " -- Sort by population -- ");
                        orderBy = "population";
                        break;
                }
                cursor = database.query("countries", null, null,
                        null, null, null, orderBy);
                break;
        }

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                String str;
                do {
                    str = "";
                    for (String cn : cursor.getColumnNames()) {
                        str = str.concat(cn + " = "
                                + cursor.getString(cursor.getColumnIndex(cn)) + "; ");
                    }
                    Log.d(LOG_TAG, str);

                } while (cursor.moveToNext());
            }
            cursor.close();
        } else
            Log.d(LOG_TAG, "Cursor is null");

        dbHelper.close();
    }
}