package com.dovhal.p0141menuadv

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.CheckBox
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var tv: TextView? = null
    private var tv2: TextView? = null
    private var chb: CheckBox?  = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // getting view-elements
        tv = findViewById<View>(R.id.textView) as TextView
        tv2 = findViewById<View>(R.id.textView2) as TextView
        chb = findViewById<View>(R.id.chbExtMenu) as CheckBox
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.mymenu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        menu.setGroupVisible(R.id.group1, chb!!.isChecked)
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val sb = StringBuilder()
        if (item.title == "exit") {
            finish()
        }
        sb.append("Item menu")
        sb.append("\r\n groupId: ").append(item.groupId)
        sb.append("\r\n itemId: ").append(item.itemId)
        sb.append("\r\n order: ").append(item.order)
        sb.append("\r\n title: ").append(item.title)
        tv2!!.text = sb.toString()
        return super.onOptionsItemSelected(item)
    }
}