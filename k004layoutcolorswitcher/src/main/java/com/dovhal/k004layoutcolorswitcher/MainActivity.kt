package com.dovhal.k004layoutcolorswitcher

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private var colors : List<Int>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // list of possible colors to set as background
        colors = listOf<Int>(
                R.color.colorAccent,
                R.color.colorLightBlue,
                R.color.colorPink,
                R.color.colorPrimary,
                R.color.colorPrimaryDark,
                R.color.colorYellow,
                R.color.colorRed)

        // list of text views
        val tvList : List<TextView> = listOf(
                textView, textView2, textView3, textView4, textView5
        )

        for (tv in tvList) {
            tv.setOnClickListener(this)
        }
    }

    override fun onClick(v: View?) {
        // when tapping on text view, random background color will be set
        val rand = (colors!!.indices).random()
        Log.d("myLogs", "Color: " + colors!![rand])
        v!!.setBackgroundColor(this.resources.getColor(colors!![rand]))
    }
}
