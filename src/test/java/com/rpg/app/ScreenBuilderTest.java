package com.rpg.app;

import org.junit.Test;

import com.rpg.app.screen.ScreenBuilder;
import com.rpg.app.screen.impl.ScreenBuilderImpl;

/**
 * Created by Valeriy Shtanko on 2017-Nov-16, 11:44
 */
public class ScreenBuilderTest {

    @Test
    public void testScreenBuilderCursorPos() {
        ScreenBuilder screenBuilder = new ScreenBuilderImpl();

        screenBuilder.eraseScreen()
                     .setCursorPosition(1,1)
                     .append("Test string [1,1]")
                     .setCursorPosition(5,10)
                     .append("Test string [5, 10]")
                     .setCursorPosition(10,5)
                     .append("Test string [10, 5]")
                     .newLine()
                     .append("---------")
                     .newLine();

        System.out.println(screenBuilder);

        // Stick terminal screen
        System.out.println(new ScreenBuilderImpl().setCursorPosition(1, 30)
                                                  .newLine()
                                                  .append("---------"));
    }
}