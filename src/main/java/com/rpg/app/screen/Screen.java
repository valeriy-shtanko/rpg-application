package com.rpg.app.screen;

import com.rpg.app.screen.impl.InputControlImpl;
import com.rpg.app.screen.impl.ScreenBuilderImpl;
import com.rpg.app.screen.impl.WindowedScreenBuilderImpl;

/**
 * Created by Valeriy Shtanko on 2017-Nov-05, 20:48
 */
public class Screen {
    private static final ScreenBuilder gameScreenBuilder = getScreenBuilder();

    public static final int BANNER_HEIGHT = 15;

    public static final int SCREEN_LEFT   =  1;
    public static final int SCREEN_TOP    =  1;
    public static final int SCREEN_WIDTH  = 114;
    public static final int SCREEN_HEIGHT = 70;

    public static final int STATS_WINDOW_LEFT   = SCREEN_LEFT;
    public static final int STATS_WINDOW_TOP    = BANNER_HEIGHT;
    public static final int STATS_WINDOW_WIDTH  = 40;
    public static final int STATS_WINDOW_HEIGHT =  5;

    public static final int INVENTORY_WINDOW_LEFT   = STATS_WINDOW_LEFT + STATS_WINDOW_WIDTH + 1;
    public static final int INVENTORY_WINDOW_TOP    = BANNER_HEIGHT;
    public static final int INVENTORY_WINDOW_WIDTH  = SCREEN_WIDTH - STATS_WINDOW_WIDTH;
    public static final int INVENTORY_WINDOW_HEIGHT =  5;

    public static final int BRIEF_WINDOW_LEFT   = SCREEN_LEFT;
    public static final int BRIEF_WINDOW_TOP    = Math.max(STATS_WINDOW_TOP + STATS_WINDOW_HEIGHT, INVENTORY_WINDOW_TOP + INVENTORY_WINDOW_HEIGHT) + 1;
    public static final int BRIEF_WINDOW_WIDTH  = SCREEN_WIDTH;
    public static final int BRIEF_WINDOW_HEIGHT = 7;

    public static final int INPUT_WINDOW_LEFT   = SCREEN_LEFT;
    public static final int INPUT_WINDOW_TOP    = BRIEF_WINDOW_TOP + BRIEF_WINDOW_HEIGHT + 1;
    public static final int INPUT_WINDOW_WIDTH  = SCREEN_WIDTH;
    public static final int INPUT_WINDOW_HEIGHT = 15;

    public static ScreenBuilder getScreenBuilder() {
        return new ScreenBuilderImpl();
    }

    public static ScreenBuilder getStatsWindowScreenBuilder() {
        return new WindowedScreenBuilderImpl(STATS_WINDOW_LEFT, STATS_WINDOW_TOP, STATS_WINDOW_WIDTH, STATS_WINDOW_HEIGHT);
    }

    public static ScreenBuilder getInventoryWindowScreenBuilder() {
        return new WindowedScreenBuilderImpl(INVENTORY_WINDOW_LEFT, INVENTORY_WINDOW_TOP, INVENTORY_WINDOW_WIDTH, INVENTORY_WINDOW_HEIGHT);
    }

    public static ScreenBuilder getBriefWindowScreenBuilder() {
        return new WindowedScreenBuilderImpl(BRIEF_WINDOW_LEFT, BRIEF_WINDOW_TOP, BRIEF_WINDOW_WIDTH, BRIEF_WINDOW_HEIGHT);
    }

    public static ScreenBuilder getInputWindowScreenBuilder() {
        return new WindowedScreenBuilderImpl(INPUT_WINDOW_LEFT, INPUT_WINDOW_TOP, INPUT_WINDOW_WIDTH, INPUT_WINDOW_HEIGHT);
    }

    public static ScreenInput getInputControl() {
        return new InputControlImpl();
    }

    public static void append(ScreenBuilder screenBuilder) {
        gameScreenBuilder.append(screenBuilder.getEscapeSequence());
    }

    public static void display() {
        doDisplay();
    }

    public static void display(ScreenBuilder screenBuilder) {
        gameScreenBuilder.append(screenBuilder.getEscapeSequence());

        doDisplay();
    }

    //---------------------------------------------------------------------------------------------
    // Private methods
    //
    private Screen(){
        // Disable to create screen object
    }

    private static void doDisplay() {
        displayGameBanner();
        System.out.print(gameScreenBuilder);

        //System.out.print(getScreenBuilder().setCursorPosition(SCREEN_LEFT, SCREEN_TOP + SCREEN_HEIGHT));
    }


    private static void  displayGameBanner() {
        ScreenBuilder builder = getScreenBuilder();

        builder.eraseScreen()
               .setForeground(Color.GREEN)
               .bold() ;

        builder.append("                                                                                                                              ").newLine()
               .append("  .d8888b.                                d8888      888                            888                                       ").newLine()
               .append(" d88P  Y88b                              d88888      888                            888                                       ").newLine()
               .append(" Y88b.                                  d88P888      888                            888                                       ").newLine()
               .append("  \"Y888b.   88888b.  888  888          d88P 888  .d88888 888  888  .d88b.  88888b.  888888 888  888 888d888 .d88b.           ").newLine()
               .append("     \"Y88b. 888 \"88b 888  888         d88P  888 d88\" 888 888  888 d8P  Y8b 888 \"88b 888    888  888 888P\"  d8P  Y8b      ").newLine()
               .append("       \"888 888  888 888  888        d88P   888 888  888 Y88  88P 88888888 888  888 888    888  888 888    88888888          ").newLine()
               .append(" Y88b  d88P 888 d88P Y88b 888       d8888888888 Y88b 888  Y8bd8P  Y8b.     888  888 Y88b.  Y88b 888 888    Y8b.               ").newLine()
               .append("  \"Y8888P\"  88888P\"   \"Y88888      d88P     888  \"Y88888   Y88P    \"Y8888  888  888  \"Y888  \"Y88888 888     \"Y8888   ").newLine()
               .append("            888           888                                                                                                 ").newLine()
               .append("            888      Y8b d88P                                                                                                 ").newLine()
               .append("            888       \"Y88P\"                                                                                                ").newLine()
               .append("                                                                                                                              ").newLine();

        builder.setForeground(Color.DEFAULT);
        
        System.out.println(builder);
    }
}
