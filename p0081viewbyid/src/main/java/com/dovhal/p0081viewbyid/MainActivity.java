package com.dovhal.p0081viewbyid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView textView;
    Button okBtn;
    Button cancelBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

/*        textView = (TextView)findViewById(R.id.myText);
        okBtn = (Button) findViewById(R.id.okBtn);
        cancelBtn = (Button) findViewById(R.id.cancelBtn);

        okBtn.setOnClickListener(this);
        cancelBtn.setOnClickListener(this);*/

        /*Задання ресурсів з файлу ресурсів*/
        LinearLayout llBottom = (LinearLayout)findViewById(R.id.llBottom);
        TextView tvBottom = (TextView)findViewById(R.id.tvBottom);
        Button btnBottom = (Button)findViewById(R.id.btnBottom);

        llBottom.setBackgroundResource(R.color.llBtmColor);
        tvBottom.setText(R.string.btmText);
        btnBottom.setText(R.string.btmBtnText);

        String txt = getResources().getString(R.string.btmText);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.okBtn:
                textView.setText("OK button pressed");
                break;
            case R.id.cancelBtn:
                textView.setText("Cancel button pressed");
                break;
            default:
                textView.setText("None btn pressed");
                break;
        }
    }


}
