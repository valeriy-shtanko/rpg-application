package com.rpg.app.screen.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.rpg.app.screen.Color;
import com.rpg.app.screen.InputOption;
import com.rpg.app.screen.ScreenBuilder;
import com.rpg.app.screen.ScreenInput;

/**
 * Created by Valeriy Shtanko on 2017-Nov-15, 22:43
 */
public class InputControlImpl implements ScreenInput {

    private static final String REFRES_KEY = "r";
    private static final String SAVE_KEY   = "s";
    private static final String QUIT_KEY   = "q";
    private static final String PROMPT     = "=> ";

    private List<InputOption> options = new ArrayList<>();

    public ScreenBuilder display(ScreenBuilder screenBuilder) {
        screenBuilder.eraseScreen()
                     .setBackground(Color.RED)
                     .setForeground(Color.WHITE)
                     .bold()
                     .moveCursorRight(1)
                     .append("Select your action:")
                     .boldOff()
                     .resetTextAttributes()
                     .newLine().newLine();

        for (int i = 0; i < options.size(); i++) {
            InputOption option = options.get(i);

            screenBuilder.moveCursorRight(1)
                         .append("%d) %s %s", i+ 1, option.getCommand(), option.getText())
                         .newLine();
        }

        // Functional keys
        screenBuilder.newLine()
                     .moveCursorRight(1).append("%s) Look around", REFRES_KEY).newLine()
                     .moveCursorRight(1).append("%s) Save game", SAVE_KEY).newLine()
                     .moveCursorRight(1).append("%s) Quit game", QUIT_KEY).newLine()
                     .moveCursorRight(1).newLine()
                     .moveCursorRight(1).append(PROMPT);

        // Set cursor position right after prompt
        screenBuilder.setCursorPosition(PROMPT.length() + 1, options.size() + 7);

        return screenBuilder;
    }

    public int readInput() {
        String input = new Scanner(System.in).nextLine();

        if (input == null || "".equals(input.trim())) {
            return INVALID_INPUT;
        }

        input = input.toLowerCase().trim();

        // Functional key's

        if (REFRES_KEY.equals(input)) {
            return REFRESH;
        }

        if (SAVE_KEY.equals(input)) {
            return SAVE_GAME;
        }

        if (QUIT_KEY.equals(input)) {
            return QUIT_GAME;
        }

        // Options key
        int selectedOption;

        try {
            selectedOption = Integer.parseUnsignedInt(input);
        }
        catch (NumberFormatException e) {
            return INVALID_INPUT;
        }

        // Check options number
        int optionNumber = selectedOption - 1;

        if ( optionNumber < 0 || optionNumber > options.size() - 1) {
            return INVALID_INPUT;
        }

        return optionNumber;
    }

    public List<InputOption> getOptions() {
        return options;
    }

    public void setOptions(List<InputOption> options) {
        this.options = options;
    }
}
