package com.dovhal.k001hb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv.text = "Hello from Kotlin"
        var flag = true;

        btnPush.setOnClickListener(){
            flag = !flag
            if (flag){
                btnPush.text = "Pushed"
            } else btnPush.text = "Not pushed"
        }
    }
}
