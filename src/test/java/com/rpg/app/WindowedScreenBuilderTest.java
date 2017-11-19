package com.rpg.app;

import org.junit.Test;

import com.rpg.app.screen.ScreenBuilder;
import com.rpg.app.screen.impl.ScreenBuilderImpl;
import com.rpg.app.screen.impl.WindowedScreenBuilderImpl;

/**
 * Created by Valeriy Shtanko on 2017-Nov-16, 12:12
 */
public class WindowedScreenBuilderTest {
    @Test
    public void testScreenBuilderCursorPos() {
        System.out.println(new ScreenBuilderImpl().eraseScreen().append("[X]"));

        ScreenBuilder windowedScreenBuilder = new WindowedScreenBuilderImpl(3, 3, 50, 50);

        windowedScreenBuilder.eraseScreen()
                             .append("[*]")
                             .newLine()
                             .append("Test string 11111")
                             .newLine()
                             .append("Test string 22222")
                             .setCursorPosition(10, 20)
                             .append("Test string 33333")
                             .setCursorPosition(10, 21)
                             .append("Test string 44444")
                             .setCursorPosition(10, 22)
                             .append("Test string 55555") ;

        System.out.println(windowedScreenBuilder);


        // Stick terminal screen
        System.out.println(new ScreenBuilderImpl().setCursorPosition(1, 40)
                                                  .newLine()
                                                  .append("---------"));
    }

    @Test
    public void testScreenBuilderWithWrappedText() {
        System.out.println(new ScreenBuilderImpl().eraseScreen()
                                                  .append("[X]"));

        ScreenBuilder windowedScreenBuilder = new WindowedScreenBuilderImpl(3, 3, 20, 50);

        windowedScreenBuilder.eraseScreen()
                             .append("[*]")
                             .newLine()
                             .append("Test test test test test test test test test test test test test test test test test test test test test test");

        System.out.println(windowedScreenBuilder);

        // Stick terminal screen
        System.out.println(new ScreenBuilderImpl().setCursorPosition(1, 40)
                                                  .newLine()
                                                  .append("---------"));
    }
}
