package com.dovhal.p0171dynamiclayout2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    LinearLayout llMain;
    RadioGroup rgGravity;
    TextView etName;
    Button btnCreate, btnCanlel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        llMain = (LinearLayout)findViewById(R.id.llMain);
        rgGravity = (RadioGroup)findViewById(R.id.rgGravity);
        etName = (TextView)findViewById(R.id.etName);

        Button btnCreate = (Button)findViewById(R.id.createBtn);
        btnCreate.setOnClickListener(this);
        Button btnClear = (Button)findViewById(R.id.btnClear);
        btnClear.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.createBtn:
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                int gravity = Gravity.LEFT;
                switch (rgGravity.getCheckedRadioButtonId()){
                    case R.id.rbLeft:
                        gravity = Gravity.LEFT;
                        break;
                    case R.id.rbRight:
                        gravity = Gravity.RIGHT;
                        break;
                    case R.id.rbCenter:
                        gravity = Gravity.CENTER;
                        break;
                }
                Button newBtn = new Button(this);
                newBtn.setText(etName.getText().toString());
                params.gravity = gravity;
                newBtn.setLayoutParams(params);
                llMain.addView(newBtn);
                break;
            case R.id.btnClear:
                llMain.removeAllViews();
                Toast.makeText(this, "Cleared",Toast.LENGTH_LONG).show();
                break;
        }
    }
}
