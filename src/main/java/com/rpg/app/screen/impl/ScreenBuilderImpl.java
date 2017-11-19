package com.rpg.app.screen.impl;

import java.util.ArrayList;

import com.rpg.app.screen.Color;
import com.rpg.app.screen.Erase;
import com.rpg.app.screen.Screen;
import com.rpg.app.screen.ScreenBuilder;
import com.rpg.app.screen.TextAttribute;

/**
 * Created by Valeriy Shtanko on 2017-Nov-13, 23:12
 */
public class ScreenBuilderImpl implements ScreenBuilder {
    protected final StringBuilder builder;
    protected final ArrayList<Integer> textAttributes;

    public ScreenBuilderImpl() {
        builder = new StringBuilder();
        textAttributes = new ArrayList<>();
    }

    public ScreenBuilderImpl(StringBuilder builder) {
        this();
        this.builder.append(builder);
    }

    @Override
    public ScreenBuilder setForeground(Color color) {
        textAttributes.add(color.foreground());
        return this;
    }

    @Override
    public ScreenBuilder setBackground(Color color) {
        textAttributes.add(color.background());
        return this;
    }

    @Override
    public ScreenBuilder setCursorPosition(final int x, final int y) {
        return appendEscapeSequence('H', y, x);
    }

    @Override
    public ScreenBuilder moveCursorUp(final int y) {
        return appendEscapeSequence('A', y);
    }

    @Override
    public ScreenBuilder moveCursorDown(final int y) {
        return appendEscapeSequence('B', y);
    }

    @Override
    public ScreenBuilder moveCursorRight(final int x) {
        return appendEscapeSequence('C', x);
    }

    @Override
    public ScreenBuilder moveCursorLeft(final int x) {
        return appendEscapeSequence('D', x);
    }

    @Override
    public ScreenBuilder eraseScreen() {
        appendEscapeSequence('J', Erase.ALL.value());
        setCursorPosition(Screen.SCREEN_LEFT, Screen.SCREEN_TOP);

        return this;
    }

    @Override
    public ScreenBuilder eraseScreen(final Erase kind) {
        return appendEscapeSequence('J', kind.value());
    }

    @Override
    public ScreenBuilder eraseLine() {
        return appendEscapeSequence('K');
    }

    @Override
    public ScreenBuilder eraseLine(final Erase kind) {
        return appendEscapeSequence('K', kind.value());
    }

    @Override
    public ScreenBuilder scrollUp(final int rows) {
        return appendEscapeSequence('S', rows);
    }

    @Override
    public ScreenBuilder scrollDown(final int rows) {
        return appendEscapeSequence('T', rows);
    }

    @Override
    public ScreenBuilder saveCursorPosition() {
        return appendEscapeSequence('s');
    }

    @Override
    public ScreenBuilder restoreCursorPosition() {
        return appendEscapeSequence('u');
    }

    @Override
    public ScreenBuilder bold() {
        return append(TextAttribute.INTENSITY_BOLD);
    }

    @Override
    public ScreenBuilder boldOff() {
        return append(TextAttribute.INTENSITY_BOLD_OFF);
    }

    @Override
    public ScreenBuilder resetTextAttributes() {
        return append(TextAttribute.RESET);
    }

    @Override
    public ScreenBuilder append(TextAttribute attribute) {
        textAttributes.add(attribute.value());
        return this;
    }

    @Override
    public ScreenBuilder append(String value) {
        flushAttributes();
        builder.append(value);
        return this;
    }

    @Override
    public ScreenBuilder append(StringBuilder value) {
        flushAttributes();
        builder.append(value);
        return this;
    }

    @Override
    public ScreenBuilder append(String pattern, Object... args) {
        flushAttributes();
        builder.append(String.format(pattern, args));
        return this;
    }

    @Override
    public ScreenBuilder newLine() {
        flushAttributes();
        builder.append(System.getProperty("line.separator"));
        return this;
    }

    @Override
    public StringBuilder getEscapeSequence() {
        return builder;
    }

    @Override
    public String toString() {
        flushAttributes();
        return builder.toString();
    }

    //-----------------------------------------------------------------------------------------------------
    // Private helper methods for building escape sequence
    //
    private static final char FIRST_ESC_CHAR  = 27;
    private static final char SECOND_ESC_CHAR = '[';

    private ScreenBuilder appendEscapeSequence(char command) {
        flushAttributes();
        builder.append(FIRST_ESC_CHAR);
        builder.append(SECOND_ESC_CHAR);
        builder.append(command);
        return this;
    }

    private ScreenBuilder appendEscapeSequence(char command, int option) {
        flushAttributes();
        builder.append(FIRST_ESC_CHAR);
        builder.append(SECOND_ESC_CHAR);
        builder.append(option);
        builder.append(command);
        return this;
    }

    private ScreenBuilder appendEscapeSequence(char command, Object... options) {
        flushAttributes();
        return doAppendEscapeSequence(command, options);
    }

    private void flushAttributes() {
        if (textAttributes.isEmpty()) {
            return;
        }

        if (textAttributes.size() == 1 && textAttributes.get(0) == 0) {
            builder.append(FIRST_ESC_CHAR);
            builder.append(SECOND_ESC_CHAR);
            builder.append('m');
        } else {
            doAppendEscapeSequence('m', textAttributes.toArray());
        }
        textAttributes.clear();
    }

    private ScreenBuilderImpl doAppendEscapeSequence(char command, Object... options) {
        builder.append(FIRST_ESC_CHAR);
        builder.append(SECOND_ESC_CHAR);
        int size = options.length;
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                builder.append(';');
            }
            if (options[i] != null) {
                builder.append(options[i]);
            }
        }
        builder.append(command);
        return this;
    }
}
