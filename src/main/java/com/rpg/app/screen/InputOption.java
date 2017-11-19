package com.rpg.app.screen;

/**
 * Created by Valeriy Shtanko on 2017-Nov-18, 17:55
 */
public class InputOption {
    private String command;
    private String text;

    public InputOption(String command, String text) {
        this.command = command;
        this.text = text;
    }

    public String getCommand() {
        return command;
    }

    public String getText() {
        return text;
    }
}
