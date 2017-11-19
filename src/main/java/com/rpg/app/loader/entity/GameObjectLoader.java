package com.rpg.app.loader.entity;

import java.util.Arrays;
import java.util.List;

import com.rpg.app.entity.GameObject;
import com.rpg.app.entity.impl.GameObjectImpl;
import com.rpg.app.loader.GameLoaderException;
import com.rpg.app.property.GameProperty;

import static com.rpg.app.loader.GameLoaderUtils.ITEM_SEPARATOR;
import static com.rpg.app.loader.GameLoaderUtils.PROPERTIES_SEPARATOR;

/**
 * Created by Valeriy Shtanko on 2017-Nov-19, 14:02
 */
public class GameObjectLoader {
    public static String toString(GameObject gameObject) throws GameLoaderException {
        StringBuilder builder = new StringBuilder(1024);

        builder.append(gameObject.getId()).append(ITEM_SEPARATOR);

        for (GameProperty property : gameObject.getProperties()) {
            builder.append(GamePropertyLoader.toString(property))
                   .append(ITEM_SEPARATOR);
        }

        return builder.toString();
    }

    public static GameObject fromString(String str) throws GameLoaderException {
        List<String> items = Arrays.asList(str.split("\\s*\\|\\s*"));

        if (items.size() < 2) {
            throw new GameLoaderException("Cannot load GameObject from string '%s'.", str);
        }

        GameObject gameObject = new GameObjectImpl(items.get(0));

        for (int i = 1; i < items.size(); i++) {
            gameObject.getProperties().add(GamePropertyLoader.fromString(items.get(i)));
        }

        return gameObject;
    }

}
