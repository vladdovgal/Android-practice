package com.dovhal.p0121logsandmessages;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvOut;
    private Button btnOk;
    private Button btnCancel;

    private static final String TAG = "myLogs";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // getting view-elements
        Log.d(TAG,"getting view-elements");
        tvOut = (TextView) findViewById(R.id.tvOut);
        btnOk = (Button) findViewById(R.id.btnOk);
        btnCancel = (Button) findViewById(R.id.btnCancel);

        Log.d(TAG, "setting onclick-listeners");
        btnOk.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("option1");
        menu.add("option2");
        menu.add("option3");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Toast.makeText(this, item.getTitle(),Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnOk:
                tvOut.setText("OK pressed");
                Log.d(TAG, "OK Pressed");
                Toast.makeText(this,"OK btn pressed", Toast.LENGTH_LONG).show();
                break;
            case R.id.btnCancel:
                tvOut.setText("CANCEL pressed");
                Log.d(TAG,"Cancel Pressed");
                Toast.makeText(this,"Cancel btn pressed", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
