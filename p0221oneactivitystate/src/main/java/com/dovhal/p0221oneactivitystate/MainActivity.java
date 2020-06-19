package com.dovhal.p0221oneactivitystate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public String STATE = "STATES";
    private Button btnActTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnActTwo = findViewById(R.id.btnActTwo);
        btnActTwo.setOnClickListener(this);
        Log.d(STATE, "Main activity: onCreate()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(STATE, "Main activity: onRestart()");

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(STATE, "Main activity: onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(STATE, "Main activity: onResume()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(STATE, "Main activity: onDestroy()");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(STATE, "Main activity: onStop()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(STATE, "Main activity: onPause()");
    }

    @Override
    public void onClick(View v) {
        Intent intent1 = new Intent(this, ActivityTwo.class);
        startActivity(intent1);
    }
}
