package com.rpg.app.loader;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import com.rpg.app.entity.BriefEntity;
import com.rpg.app.entity.GameObject;
import com.rpg.app.entity.SceneEntity;
import com.rpg.app.loader.entity.BriefLoader;
import com.rpg.app.loader.entity.GameObjectLoader;
import com.rpg.app.loader.entity.SceneLoader;

import static com.rpg.app.loader.GameLoaderUtils.COMMENT_PREFIX;

/**
 * Created by Valeriy Shtanko on 2017-Nov-18, 12:47
 */
public class GameLoader {
    public static final String SAVED_PLAYER_FILE         = "saved-player";
    public static final String SAVED_ACTIVE_OBJECTS_FILE = "saved-active-objects";
    public static final String SAVED_ACTIVE_SCENE_FILE   = "saved-active-scene";
    public static final String SAVED_SCENES_FILE         = "saved-scenes";

    public static List<BriefEntity> loadBriefsFromFile (String fileName) throws GameLoaderException {
        List<BriefEntity> result = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;

            // reading file file line by line
            while ((line = br.readLine()) != null) {
                line = line.trim();

                if (!isDataRow(line)) {
                    continue;
                }

                result.add(BriefLoader.fromString(line));
            }
        }
        catch(Exception e) {
            throw new GameLoaderException(String.format("Cannot load scenario file '%s'", fileName), e);
        }

        return result;
    }

    public static List<SceneEntity> loadScenesFromFile(String fileName)  throws GameLoaderException {
        List<SceneEntity> result = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;

            // reading file file line by line
            while ((line = br.readLine()) != null) {
                line = line.trim();

                if (!isDataRow(line)) {
                    continue;
                }

                result.add(SceneLoader.fromString(line));
            }
        }
        catch(Exception e) {
            throw new GameLoaderException(String.format("Cannot load scene(s)scenario file '%s'", fileName), e);
        }

        return result;
    }

    public static SceneEntity loadSceneFromFile(String fileName)  throws GameLoaderException {
        List<SceneEntity> result = loadScenesFromFile(fileName);

        if (result.size() > 0) {
            return result.get(0);
        } else {
            return null;
        }
    }

    public static void saveSceneToFile(SceneEntity scene, String fileName) throws GameLoaderException {
        List<SceneEntity> scenes = new ArrayList<>();
        scenes.add(scene);

        saveScenesToFile(scenes, fileName);
    }

    public static void saveScenesToFile(List<SceneEntity> scenes, String fileName) throws GameLoaderException {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {

            for(SceneEntity sceneEntity: scenes) {

                bw.write(SceneLoader.toString(sceneEntity));
                bw.newLine();
            }
        }
        catch(Exception e) {
            throw new GameLoaderException(String.format("Cannot save game scene(s) to file '%s'", fileName), e);
        }
    }

    public static void saveGameObjectToFile(GameObject gameObject, String fileName)  throws GameLoaderException {
        List<GameObject> gameObjects = new ArrayList<>();
        gameObjects.add(gameObject);

        saveGameObjectsToFile(gameObjects, fileName);
    }

    public static void saveGameObjectsToFile(List<GameObject> gameObjects, String fileName) throws GameLoaderException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {

            for(GameObject gameObject: gameObjects) {

                bw.write(GameObjectLoader.toString(gameObject));
                bw.newLine();
            }
        }
        catch(Exception e) {
            throw new GameLoaderException(String.format("Cannot save game object(s) to file '%s'", fileName), e);
        }
    }

    public static List<GameObject> loadGameObjectsFromFile(String fileName) throws GameLoaderException {
        List<GameObject> result = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;

            // reading file file line by line
            while ((line = br.readLine()) != null) {
                line = line.trim();

                if (!isDataRow(line)) {
                    continue;
                }

                result.add(GameObjectLoader.fromString(line));
            }
        }
        catch(Exception e) {
            throw new GameLoaderException(String.format("Cannot load game object(s) from file '%s'", fileName), e);
        }

        return result;
    }

    public static GameObject loadGameObjectFromFile(String fileName) throws GameLoaderException {
        List<GameObject> gameObjects = loadGameObjectsFromFile(fileName);

        if (gameObjects.size() > 0) {
            return gameObjects.get(0);
        } else {
            return null;
        }
    }

    private static boolean isDataRow(String row) {
        // skip empty lines
        if (row.isEmpty()) {
            return false;
        }

        // Comments
        if (row.startsWith(COMMENT_PREFIX)) {
            return false;
        }

        return true;
    }
}
