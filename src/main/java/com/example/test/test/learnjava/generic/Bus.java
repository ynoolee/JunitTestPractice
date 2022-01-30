package com.example.test.test.learnjava.generic;

public class Bus implements Car {
    private String name;
    public int speed;

    public Bus(String name, int speed) {
        this.name = name;
        this.speed = speed;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getSpeed() {
        return speed;
    }
}
