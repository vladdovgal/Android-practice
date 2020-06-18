package com.dovhal.p0191simplecalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private final int RESET_MENU_ID = 1;
    private final int EXIT_MENU_ID = 2;
    EditText etNum1;
    EditText etNum2;
    Button btnAdd;
    Button btnSub;
    Button btnMul;
    Button btnDiv;
    TextView tvResult;
    String operator = "";

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0,RESET_MENU_ID,1,"RESET");
        menu.add(0,EXIT_MENU_ID,2,"QUIT");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case RESET_MENU_ID:
                tvResult.setText("");
                etNum1.setText("");
                etNum2.setText("");
                break;
            case EXIT_MENU_ID:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNum1 = findViewById(R.id.etNum1);
        etNum2 = findViewById(R.id.etNum2);
        btnAdd = findViewById(R.id.btnAdd);
        btnSub = findViewById(R.id.btnSub);
        btnMul = findViewById(R.id.btnMul);
        btnDiv = findViewById(R.id.btnDiv);
        tvResult = findViewById(R.id.tvResult);
        btnAdd.setOnClickListener(this);
        btnMul.setOnClickListener(this);
        btnDiv.setOnClickListener(this);
        btnSub.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        float num1, num2, result = 0;

        if (TextUtils.isEmpty(etNum1.getText().toString())
                || TextUtils.isEmpty(etNum2.getText().toString())){
            return;
        }

        num1 = Float.parseFloat(String.valueOf(etNum1.getText()));
        num2 = Float.parseFloat(String.valueOf(etNum2.getText()));

        switch (v.getId()){
            case R.id.btnAdd:
                operator = "+";
                result = num1 + num2;
                break;
            case R.id.btnSub:
                operator = "-";
                result = num1 - num2;
                break;
            case R.id.btnMul:
                operator = "*";
                result = num1 * num2;
                break;
            case R.id.btnDiv:
                operator = "/";
                break;
        }
        if(num2== (float)(0.0) && operator.equals("/")){
            Toast.makeText(this,"Division by 0",Toast.LENGTH_SHORT).show();
            tvResult.setText("");
        } else tvResult.setText(num1 + " " + operator + " " + num2 + " = " + result);
    }
}
