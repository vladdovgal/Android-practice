package com.dovhal.p0261intentfilter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnTime = findViewById(R.id.btnTime);
        Button btnDate = findViewById(R.id.btnDate);

        btnTime.setOnClickListener(this);
        btnDate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.btnTime:
                intent = new Intent("com.dovhal.intent.showtime");
                startActivity(intent);
                break;
            case R.id.btnDate:
                intent = new Intent("com.dovhal.intent.showdate");
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
