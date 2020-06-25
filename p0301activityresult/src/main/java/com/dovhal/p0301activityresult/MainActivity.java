package com.dovhal.p0301activityresult;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView tv;
    Button btnColor, btnAlign;
    final int REQUEST_CODE_COLOR = 1;
    final int REQUEST_CODE_ALIGN = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.tvText);
        btnColor = findViewById(R.id.btnColor);
        btnAlign = findViewById(R.id.btnAlign);

        btnAlign.setOnClickListener(this);
        btnColor.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.btnColor:
                intent.setClass(this, ColorActivity.class);
                startActivityForResult(intent, REQUEST_CODE_COLOR);
                break;
            case R.id.btnAlign:
                Toast.makeText(this, "btn align pressed", Toast.LENGTH_SHORT).show();
                intent.setClass(this, AlignmentActivity.class);
                startActivityForResult(intent, REQUEST_CODE_ALIGN);
                break;
            default: break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("myLogs", "Request code: " + requestCode
                + ", Result code: " + requestCode);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_COLOR:
                    assert data != null;
                    int color = data.getIntExtra("color", Color.WHITE);
                    tv.setTextColor(color);
                    break;
                case REQUEST_CODE_ALIGN:
                    Toast.makeText(this, "onActivityResult case alignment", Toast.LENGTH_SHORT).show();
                    assert data != null;
                    int align = data.getIntExtra("alignment", Gravity.LEFT);
                    tv.setGravity(align);
                    break;
            }
        } else {
            Toast.makeText(this, "Wrong result", Toast.LENGTH_SHORT).show();
        }
    }
}
