package com.rpg.app.screen;

/**
 * Created by Valeriy Shtanko on 2017-Nov-06, 22:48
 */
public enum Erase {
    FORWARD  (0, "FORWARD"),
    BACKWARD (1, "BACKWARD"),
    ALL      (2, "ALL");

    private final int    value;
    private final String name;

    Erase(int index, String name) {
        this.value = index;
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public int value() {
        return value;
    }
}