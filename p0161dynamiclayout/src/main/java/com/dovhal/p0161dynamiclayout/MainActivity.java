package com.dovhal.p0161dynamiclayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

        // setting some parameters
        ViewGroup.LayoutParams lpView = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams leftMarginParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        leftMarginParams.leftMargin = 45;
        LinearLayout.LayoutParams rightGravityParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        rightGravityParams.gravity = Gravity.RIGHT;
        // creating view-elements
        TextView tv = new TextView(this);
        tv.setText("Some text");
        tv.setLayoutParams(lpView);
        Button btn = new Button(this);
        btn.setText("Button");
        Button btn1 = new Button(this);
        btn1.setText("Button 1");
        btn1.setLayoutParams(leftMarginParams);
        Button btn2 = new Button(this);
        btn2.setText("Button 2");

        // adding view-elements to layout
        linearLayout.addView(tv);
        linearLayout.addView(btn, lpView);
        linearLayout.addView(btn1);
        linearLayout.addView(btn2,  rightGravityParams);

        setContentView(linearLayout, layoutParams);

    }
}
