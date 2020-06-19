package com.dovhal.p0221oneactivitystate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class ActivityTwo extends AppCompatActivity {
    public String STATE = "STATES";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        Log.d(STATE, "Second activity: onCreate()");

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(STATE, "Second activity: onRestart()");

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(STATE, "Second activity: onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(STATE, "Second activity: onResume()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(STATE, "Second activity: onDestroy()");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(STATE, "Second activity: onStop()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(STATE, "Second activity: onPause()");
    }

}