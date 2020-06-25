package com.dovhal.p0271getintentaction;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.text.SimpleDateFormat;

public class DateTimeActivity extends AppCompatActivity {
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_time);
        Intent intent = getIntent();
        String action = intent.getAction();
        String format = "", textInfo = "";

        assert action != null;
        if (action.equals("com.dovhal.intent.showtime")){
            format = "HH:mm:ss";
            textInfo = "Time: ";
        } else if (action.equals("com.dovhal.intent.showdate")){
            format = "EEE:dd:MM:dd";
            textInfo = "Date: ";
        }

        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String text = sdf.format(System.currentTimeMillis());

        tv = findViewById(R.id.tv);
        tv.setText(textInfo + text);
    }
}