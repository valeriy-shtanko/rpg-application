package com.rpg.app;

import java.util.ArrayList;
import java.util.List;

import com.rpg.app.core.Player;
import com.rpg.app.core.WorldManager;
import com.rpg.app.entity.BriefEntity;
import com.rpg.app.entity.SceneEntity;
import com.rpg.app.loader.GameLoader;
import com.rpg.app.property.PropertyType;
import com.rpg.app.screen.Color;
import com.rpg.app.screen.InputOption;
import com.rpg.app.screen.Screen;
import com.rpg.app.screen.ScreenBuilder;
import com.rpg.app.screen.ScreenInput;

import static com.rpg.app.screen.ScreenInput.INVALID_INPUT;
import static com.rpg.app.screen.ScreenInput.QUIT_GAME;
import static com.rpg.app.screen.ScreenInput.SAVE_GAME;

/**
 * Created by Valeriy Shtanko on 2017-Nov-05, 00:18
 */
public class RpgApplication {

    public static void main( String[] args ) throws Exception {
        List<BriefEntity> briefs = GameLoader.loadBriefsFromFile("briefs");
        List<SceneEntity> scenes = GameLoader.loadScenesFromFile("scenes");

        WorldManager worldManager = new WorldManager();

        worldManager.init(briefs, scenes);
        worldManager.moveToScene("001");

        List<BriefEntity> commandResponse = worldManager.applyCommand("INIT", null); // big bang kick

        // Game loop
        while(true) {
            // Player stats section
            displayPlayer(worldManager.getPlayer());

            // Display inventory section
            displayInventory();

            // Display Brief section
            displayBrief(commandResponse);

            // Prepare Input section
            List<BriefEntity> entities = worldManager.invokeOptions();
            List<InputOption> options = new ArrayList<>();

            entities.forEach(e -> options.add(new InputOption(e.getCommand(), e.getProperty().getName())));

            ScreenInput input = Screen.getInputControl();
            input.setOptions(options);

            ScreenBuilder inputScreenBuilder = Screen.getInputWindowScreenBuilder();
            input.display(inputScreenBuilder);
            Screen.append(inputScreenBuilder);

            // Display and read user input
            Screen.display();

            int selectedNumber = input.readInput();

            if (selectedNumber == QUIT_GAME) {
                break;
            }

            if (selectedNumber == SAVE_GAME) {
                saveGame(worldManager);
                continue;
            }

            if (selectedNumber == INVALID_INPUT) {
                continue;
            }

            // Handle input
            BriefEntity selectedBrief = entities.get(selectedNumber);

            commandResponse = worldManager.applyCommand(selectedBrief.getCommand(), selectedBrief.getProperty());
        }

    }


    private static void saveGame(WorldManager worldManager) {
        // TODO
    }

    private static void displayPlayer(Player player) {
        ScreenBuilder screenBuilder = Screen.getStatsWindowScreenBuilder();

        screenBuilder.eraseScreen();

        screenBuilder.setBackground(Color.RED)
                     .setForeground(Color.WHITE)
                     .moveCursorRight(1)
                     .append("Player statistics:")
                     .resetTextAttributes()
                     .newLine();

        player.getProperties()
              .forEach(p ->  {
                  if (p.getType() == PropertyType.VALUE) {
                      screenBuilder.moveCursorRight(1).append("%s : %d", p.getName(), p.getValue().asInteger()).newLine();
                  }});

        Screen.append(screenBuilder);
    }

    // TODO
    private static void displayInventory() {
        ScreenBuilder screenBuilder = Screen.getInventoryWindowScreenBuilder();

        screenBuilder.eraseScreen();

        screenBuilder.setBackground(Color.RED)
                     .setForeground(Color.WHITE)
                     .moveCursorRight(1)
                     .append("Inventory:")
                     .resetTextAttributes()
                     .newLine();

        screenBuilder.append("player item-1 : 2").newLine()
                     .append("player item-2 : 5").newLine()
                     .append("player item-3 : 1").newLine();

        Screen.append(screenBuilder);
    }

    private static void displayBrief(List<BriefEntity> briefs) {
        ScreenBuilder screenBuilder = Screen.getBriefWindowScreenBuilder();

        screenBuilder.eraseScreen()
                     .setBackground(Color.RED)
                     .setForeground(Color.WHITE)
                     .moveCursorRight(1)
                     .append("Brief information:")
                     .resetTextAttributes()
                     .newLine();

        briefs.forEach(b -> screenBuilder.setForeground(Color.YELLOW).append(b.getText()).newLine());

        screenBuilder.setForeground(Color.WHITE)
                     .resetTextAttributes()
                     .newLine();

        Screen.append(screenBuilder);
    }
}