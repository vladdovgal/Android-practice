package com.dovhal.k003aboutmeinteractivestarter

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        done_button.setOnClickListener(this)
        tvNickname.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v!!.id) {
            R.id.done_button -> {
                if (!etNickname.text.toString().equals("")){
                    // assign name for TextView
                    tvNickname.text = etNickname.text;
                    // make btn and EditText invisible
                    done_button.visibility = View.GONE
                    etNickname.visibility = View.GONE
                    tvNickname.visibility = View.VISIBLE
                } else {
                    Toast.makeText(this, "Enter name", Toast.LENGTH_LONG).show()
                }

                // hide qwerty
                val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(v.windowToken, 0)
            }
            R.id.tvNickname -> {
                // back to name Editing
                tvNickname.visibility = View.GONE
                etNickname.visibility = View.VISIBLE
                done_button.visibility = View.VISIBLE

                // set the focus to edit text
                etNickname.requestFocus()

                // show the keyboard
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.showSoftInput(etNickname, 0)
            }
        }
    }
}
