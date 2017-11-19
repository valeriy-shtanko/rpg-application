package com.rpg.app.screen;

/**
 * Created by Valeriy Shtanko on 2017-Nov-05, 23:14
 */
public enum Color {
    BLACK  (0, "BLACK"),
    RED    (1, "RED"),
    GREEN  (2, "GREEN"),
    YELLOW (3, "YELLOW"),
    BLUE   (4, "BLUE"),
    MAGENTA(5, "MAGENTA"),
    CYAN   (6, "CYAN"),
    WHITE  (7, "WHITE"),
    DEFAULT(9, "DEFAULT");

    private final int value;
    private final String name;

    Color(int index, String name) {
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

    public int foreground() {
        return value + 30;
    }

    public int background() {
        return value + 40;
    }
}
