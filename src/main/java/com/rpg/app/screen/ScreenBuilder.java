package com.rpg.app.screen;


/**
 * Created by Valeriy Shtanko on 2017-Nov-13, 23:12
 */
public interface ScreenBuilder {
    ScreenBuilder setForeground(Color color);
    ScreenBuilder setBackground(Color color);

    ScreenBuilder setCursorPosition(final int x, final int y);

    ScreenBuilder moveCursorUp(final int y);
    ScreenBuilder moveCursorDown(final int y);
    ScreenBuilder moveCursorRight(final int x);
    ScreenBuilder moveCursorLeft(final int x);

    ScreenBuilder eraseScreen();
    ScreenBuilder eraseScreen(final Erase kind);
    ScreenBuilder eraseLine();
    ScreenBuilder eraseLine(final Erase kind);

    ScreenBuilder scrollUp(final int rows);
    ScreenBuilder scrollDown(final int rows);

    ScreenBuilder saveCursorPosition();
    ScreenBuilder restoreCursorPosition();

    ScreenBuilder bold();
    ScreenBuilder boldOff();

    ScreenBuilder resetTextAttributes();
    ScreenBuilder append(TextAttribute attribute);

    ScreenBuilder append(String value);
    ScreenBuilder append(StringBuilder value);
    ScreenBuilder append(String pattern, Object... args);

    ScreenBuilder newLine();

    StringBuilder getEscapeSequence();
}
