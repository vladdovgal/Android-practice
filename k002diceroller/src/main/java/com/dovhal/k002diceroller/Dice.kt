package com.dovhal.k002diceroller

/**
 * Dice class is the representation of the  n-sided dice roller
 * @param sides quantity of sides
 * @author vladd
 * @version 1.0
 */
class Dice(private val sides : Int) {

    fun roll() : Int {
        return (1..sides).random()
    }
}