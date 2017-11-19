package com.rpg.app.loader.entity;

import java.util.Arrays;
import java.util.List;

import com.rpg.app.entity.BriefEntity;
import com.rpg.app.entity.impl.BriefEntityImpl;
import com.rpg.app.loader.GameLoaderException;

import static com.rpg.app.loader.GameLoaderUtils.ITEM_SEPARATOR;


/**
 * Created by Valeriy Shtanko on 2017-Nov-19, 12:50
 */
public class BriefLoader {
    public static String toString(BriefEntity briefEntity) throws GameLoaderException {
        StringBuilder builder = new StringBuilder(1024);

        builder.append(briefEntity.getCommand()).append(ITEM_SEPARATOR);

        builder.append(GamePropertyLoader.toString(briefEntity.getProperty())).append(ITEM_SEPARATOR);

        builder.append(briefEntity.getText()).append(ITEM_SEPARATOR);
        builder.append(briefEntity.getFeedback()).append(ITEM_SEPARATOR);

        return builder.toString();
    }

    public static BriefEntity fromString(String str) throws GameLoaderException {

        List<String> items = Arrays.asList(str.split("\\s*\\|\\s*"));

        if(items.size() < 2) {
            throw new GameLoaderException("Cannot load BriefEntity from string '%s'.", str);
        }

        return new BriefEntityImpl(items.get(0),
                                   GamePropertyLoader.fromString(items.get(1)),
                                   items.size() > 2 ? items.get(2) : "",
                                   items.size() > 3 ? items.get(3) : "");
    }
}
