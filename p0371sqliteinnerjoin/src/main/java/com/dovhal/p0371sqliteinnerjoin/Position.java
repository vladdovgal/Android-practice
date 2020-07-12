package com.dovhal.p0371sqliteinnerjoin;

public enum Position {
    DIRECTOR(15000),
    PROGRAMMER(11000),
    ACCOUNTANT(9000),
    CLEANER(6000);

    private int salary;
    Position(int salary) {
        this.salary = salary;
    }

    public int getSalary(){
        return salary;
    }
}
