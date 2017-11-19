package com.rpg.app.screen;

import java.util.List;

/**
 * Created by Valeriy Shtanko on 2017-Nov-16, 18:50
 */
public interface ScreenInput {
    int SAVE_GAME = 1000;
    int QUIT_GAME = 1001;

    int INVALID_INPUT = -1;

    ScreenBuilder display(ScreenBuilder screenBuilder);

    int readInput();

    List<InputOption> getOptions();
    void setOptions(List<InputOption> options);
}
