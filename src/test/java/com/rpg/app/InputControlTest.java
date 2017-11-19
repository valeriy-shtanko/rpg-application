package com.rpg.app;

import java.util.Arrays;

import org.junit.Test;

import com.rpg.app.screen.InputOption;
import com.rpg.app.screen.impl.InputControlImpl;
import com.rpg.app.screen.ScreenBuilder;
import com.rpg.app.screen.impl.ScreenBuilderImpl;
import com.rpg.app.screen.impl.WindowedScreenBuilderImpl;

/**
 * Created by Valeriy Shtanko on 2017-Nov-16, 16:45
 */
public class InputControlTest {
    @Test
    public void testInputControlDisplayOptions() {
        InputControlImpl inputControl = new InputControlImpl();

        inputControl.setOptions(Arrays.asList(
            new InputOption("Command 1", "Option 1"),
            new InputOption("Command 2", "Option 2"),
            new InputOption("Command 3", "Option 3")));

        // Prepare screen
        System.out.print(new ScreenBuilderImpl().eraseScreen().append("[X]"));

        ScreenBuilder windowedScreenBuilder = new WindowedScreenBuilderImpl(5, 5, 50, 50);

        // Display options
        inputControl.display(windowedScreenBuilder);
        System.out.print(windowedScreenBuilder);

        // Stick terminal screen
        System.out.println(new ScreenBuilderImpl().setCursorPosition(1, 30)
                                                  .newLine()
                                                  .append("---------"));
    }

    @Test
    public void testInputControlReadInput() {
        InputControlImpl inputControl = new InputControlImpl();

        inputControl.setOptions(Arrays.asList(
            new InputOption("Command 1", "Option 1"),
            new InputOption("Command 2", "Option 2"),
            new InputOption("Command 3", "Option 3")
        ));

        // Prepare screen
        System.out.print(new ScreenBuilderImpl().eraseScreen().append("[X]"));

        ScreenBuilder windowedScreenBuilder = new WindowedScreenBuilderImpl(3, 3, 50, 50);

        // Display options
        System.out.print(inputControl.display(windowedScreenBuilder));

        int selectedOptions = inputControl.readInput();

        // Stick terminal screen
        System.out.println(new ScreenBuilderImpl().setCursorPosition(1, 30)
                                                  .append("Selected option: %d", selectedOptions)
                                                  .newLine()
                                                  .append("---------"));
    }
}