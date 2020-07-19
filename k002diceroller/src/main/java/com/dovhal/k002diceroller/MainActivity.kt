    package com.dovhal.k002diceroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.concurrent.schedule

    @Suppress("IMPLICIT_CAST_TO_ANY")
    class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btnRoll.setOnClickListener(){
            rollDice()
        }
        Timer("SettingUp", false).schedule(1000) {
            /* do a dice roll when the app starts */
            rollDice()
        }

    }
        /* dice rolling */
        private fun rollDice() : Unit {
            /*create a new Dice object */
            val dice = Dice(6)
            val diceRoll = dice.roll()

            val diceImage : ImageView = findViewById(R.id.dice)


            /* determine drawable resource to dice ImageView */
            val drawableResource = when(diceRoll){
                1 -> R.drawable.dice_1
                2 -> R.drawable.dice_2
                3 -> R.drawable.dice_3
                4 -> R.drawable.dice_4
                5 -> R.drawable.dice_5
                6 -> R.drawable.dice_6
                else -> {
                    Toast.makeText(this,"Something went wrong", Toast.LENGTH_SHORT)
                            .show()
                    R.drawable.dice_1
                }
            }
            /* assign resource */
            diceImage.setImageResource(drawableResource)
            /* content description for screen readers */
            diceImage.contentDescription = diceRoll.toString()
        }
}
