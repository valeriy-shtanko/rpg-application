package com.rpg.app.screen.impl;

import java.util.Arrays;
import java.util.List;

import com.rpg.app.screen.ScreenBuilder;

/**
 * Created by Valeriy Shtanko on 2017-Nov-13, 23:12
 */
public class WindowedScreenBuilderImpl extends ScreenBuilderImpl {
    private int left;
    private int top;
    private int width;
    private int height;

    private int cursorX;
    private int cursorY;

    public WindowedScreenBuilderImpl(int left, int top, int width, int height) {
        this.left = left;
        this.top = top;
        this.width = width;
        this.height = height;

        setCursorPosition(0, 0);
    }

    @Override
    public ScreenBuilder eraseScreen() {
        setCursorPosition(0, 0);

        for(int i = 0; i <= height; i++) {
            setCursorPosition(0, i);
            eraseLine();
            newLine();
        }

        drawBorder();

        return setCursorPosition(0, 0);
    }

    public ScreenBuilder drawBorder() {
        saveCursorPosition();

        setCursorPosition(0, 0);

        append(createString('─', width));

        restoreCursorPosition();

        return this;
    }


    @Override
    public ScreenBuilder newLine() {
        return setCursorPosition(0, cursorY + 1);
    }

    /**
     *
     * @param x - x position inside window
     * @param y - y position inside window
     */
    @Override
    public ScreenBuilder setCursorPosition(final int x, final int y) {
        cursorX = checkCursorX(x);
        cursorY = checkCursorY(y);

        return super.setCursorPosition(left + cursorX, top + cursorY);
    }

    @Override
    public ScreenBuilder append(String value) {
        List<String> words = Arrays.asList(value.split("\\s+"));

        if(words.size() == 1) {
            return super.append(value);
        }

        String line = "";

        for (String word : words) {
            if (word.length() + 1 + line.length() <= width) {
                line = line.concat(" ").concat(word);
            } else {
                super.append(line);
                line = "";
                newLine();
            }
        }

        if (line.length() > 0) {
            super.append(line);
        }

        return this;
    }

    // --------------------------------------------------------------------------------------------
    // Private methods
    //
    /**
     *
     * @param  x - x position inside window
     * @return corrected x position
     */
    private int checkCursorX(int x) {
        if (x < 0 || x > width) {
            x = left;
        }

        return x;
    }

    /**
     *
     * @param y - y position inside window
     * @return  - corrected y position
     */
    private int checkCursorY(int y) {
        if (y < 0 || y > height) {
            y = top;
        }

        return y;
    }
}