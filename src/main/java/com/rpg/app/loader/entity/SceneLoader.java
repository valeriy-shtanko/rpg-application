package com.rpg.app.loader.entity;

import java.util.Arrays;
import java.util.List;

import com.rpg.app.entity.GameObject;
import com.rpg.app.entity.SceneEntity;
import com.rpg.app.entity.impl.SceneEntityImpl;
import com.rpg.app.loader.GameLoaderException;

import static com.rpg.app.loader.GameLoaderUtils.SCENE_ITEM_DELIMETER;

/**
 * Created by Valeriy Shtanko on 2017-Nov-19, 16:51
 */
public class SceneLoader {

    public static String toString(SceneEntity entity) throws GameLoaderException {
        StringBuilder builder = new StringBuilder(1024);

        builder.append(entity.getId()).append(SCENE_ITEM_DELIMETER);

        for(GameObject gameObject : entity.getGameObjects()) {
            builder.append(GameObjectLoader.toString(gameObject))
                   .append(SCENE_ITEM_DELIMETER);
        }

        return builder.toString();
    }

    public static SceneEntity fromString(String str) throws GameLoaderException {
        List<String> items = Arrays.asList(str.split(String.format("\\s*%s\\s*", SCENE_ITEM_DELIMETER)));

        if (items.size() < 2) {
            throw new GameLoaderException("Cannot load SceneEntity from string '%s'.", str);
        }

        SceneEntity entity = new SceneEntityImpl(items.get(0).trim());

        for (int i = 1; i < items.size(); i++) {
            entity.getGameObjects().add(GameObjectLoader.fromString(items.get(i)));
        }

        return entity;
    }
}
