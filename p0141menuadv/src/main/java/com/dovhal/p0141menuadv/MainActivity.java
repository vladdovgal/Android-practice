package com.dovhal.p0141menuadv;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private TextView tv;
    private TextView tv2;
    private CheckBox chb;
    private Switch sw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // getting view-elements
        tv = (TextView)findViewById(R.id.textView);
        tv2 = (TextView)findViewById(R.id.textView2);
        chb = (CheckBox)findViewById(R.id.chbExtMenu);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.setGroupVisible(R.id.group1, chb.isChecked());
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        StringBuilder sb = new StringBuilder();
        if (item.getTitle().equals("exit")){
            this.finish();
        }
        sb.append("Item menu");
        sb.append("\r\n groupId: ").append(item.getGroupId());
        sb.append("\r\n itemId: ").append(item.getItemId());
        sb.append("\r\n order: ").append(item.getOrder());
        sb.append("\r\n title: ").append(item.getTitle());
        tv2.setText(sb.toString());

        return super.onOptionsItemSelected(item);
    }

}


// menu creation from code
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        menu.add(0, 1, 0, "add");
//        menu.add(0, 2, 0, "edit");
//        menu.add(0, 3, 3, "delete");
//        menu.add(1, 4, 1, "copy");
//        menu.add(1, 5, 2, "paste");
//        menu.add(1, 6, 4, "exit");
//        return super.onCreateOptionsMenu(menu);
//    }


