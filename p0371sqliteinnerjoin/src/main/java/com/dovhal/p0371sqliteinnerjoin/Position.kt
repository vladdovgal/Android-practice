package com.dovhal.p0371sqliteinnerjoin

enum class Position(salary: Int) {
    DIRECTOR(15000),
    PROGRAMMER(11000),
    ACCOUNTANT(9000),
    CLEANER(6000);


    var salary: Int = 0;
}