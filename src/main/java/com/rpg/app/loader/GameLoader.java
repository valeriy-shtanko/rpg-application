package com.rpg.app.loader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.rpg.app.entity.BriefEntity;
import com.rpg.app.entity.SceneEntity;
import com.rpg.app.loader.entity.BriefLoader;
import com.rpg.app.loader.entity.SceneLoader;

import static com.rpg.app.loader.GameLoaderUtils.COMMENT_PREFIX;

/**
 * Created by Valeriy Shtanko on 2017-Nov-18, 12:47
 */
public class GameLoader {

    public static List<BriefEntity> loadBriefsFromFile (String fileName) throws GameLoaderException {
        List<BriefEntity> result = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;

            // reading file file line by line
            while ((line = br.readLine()) != null) {
                line = line.trim();

                // skip empty lines
                if (line.isEmpty()) {
                    continue;
                }

                // Comments
                if (line.startsWith(COMMENT_PREFIX)) {
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

                // skip empty lines
                if (line.isEmpty()) {
                    continue;
                }

                // Comments
                if (line.startsWith(COMMENT_PREFIX)) {
                    continue;
                }

                result.add(SceneLoader.fromString(line));
            }
        }
        catch(Exception e) {
            throw new GameLoaderException(String.format("Cannot load scenario file '%s'", fileName), e);
        }

        return result;
    }

}
