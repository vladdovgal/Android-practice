package com.example.k006recyclerview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private var phones = arrayListOf<Phone>()
    private lateinit var toolbar : Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // for older Android versions ActionBar -> Toolbar
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)


        initContent()
        val recyclerView = findViewById<RecyclerView>(R.id.list)
        val adapter = DataAdapter(this, phones)
        recyclerView.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.exit) {
            val intent  = Intent(this, CollapsingBarActivity::class.java)
            startActivity(intent)
        }
        return true
    }

    fun initContent() {
        phones.add(Phone("Redmi note 5", "Xiaomi" , R.drawable.xiaomi))
        phones.add(Phone("iPhone 6", "Apple" , R.drawable.i6))
    }
}
