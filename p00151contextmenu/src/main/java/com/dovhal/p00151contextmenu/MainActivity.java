package com.dovhal.p00151contextmenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView tvColor;
    private TextView tvFont;

    private final int MENU_FONT_30 = 30;
    private final int MENU_FONT_20 = 20;
    private final int MENU_FONT_10 = 10;
    private final int MENU_COLOR_RED = 1;
    private final int MENU_FONT_GREEN = 2;
    private final int MENU_FONT_BLUE = 3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tvColor = (TextView)findViewById(R.id.tvColor);
        tvFont = (TextView)findViewById(R.id.tvSize);

        tvColor.setOnCreateContextMenuListener(this);
//                registerForContextMenu(tvColor);
        tvFont.setOnCreateContextMenuListener(this);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater menuInflater = getMenuInflater();

        switch (v.getId()){
            case R.id.tvColor:
                menuInflater.inflate(R.menu.color_context, menu);
                break;
            case R.id.tvSize:
                menuInflater.inflate(R.menu.font_context, menu);
                break;
        }
        super.onCreateContextMenu(menu, v, menuInfo);
//        Menu using code (more flexible)
/*        switch (v.getId()){
            case R.id.tvColor:
                menu.add(0,MENU_COLOR_RED,0,"Red");
                menu.add(0,MENU_FONT_GREEN,0,"Green");
                menu.add(0,MENU_FONT_BLUE,0,"Blue");
                break;
            case R.id.tvSize:
                menu.add(0,MENU_FONT_30,0,"30");
                menu.add(0,MENU_FONT_20,0,"20");
                menu.add(0,MENU_FONT_10,0,"10");
                break;
        }*/
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.c1:
                tvColor.setTextColor(Color.RED);
                break;
            case R.id.c2:
                tvColor.setTextColor(Color.GREEN);
                break;
            case R.id.c3:
                tvColor.setTextColor(Color.BLUE);
                break;
            case R.id.font20:
                tvFont.setTextSize(20);
                break;
            case R.id.font30:
                tvFont.setTextSize(30);
                break;
            case R.id.font40:
                tvFont.setTextSize(40);
                break;
            case R.id.font50:
                tvFont.setTextSize(50);
                break;
            default:
                return super.onContextItemSelected(item);
        }
//        switch (item.getItemId()){
//            case MENU_COLOR_RED:
//                tvColor.setTextColor(Color.RED);
//                break;
//            case MENU_FONT_GREEN:
//                tvColor.setTextColor(Color.GREEN);
//                break;
//            case MENU_FONT_BLUE:
//                tvColor.setTextColor(Color.BLUE);
//                break;
//            case MENU_FONT_30:
//                tvFont.setTextSize(30);
//                break;
//            case MENU_FONT_20:
//                tvFont.setTextSize(20);
//                break;
//            case MENU_FONT_10:
//                tvFont.setTextSize(10);
//                break;
//        }
        return super.onContextItemSelected(item);
    }
}
