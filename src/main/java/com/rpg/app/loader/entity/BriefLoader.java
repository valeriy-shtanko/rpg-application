package com.rpg.app.loader.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.rpg.app.entity.BriefEntity;
import com.rpg.app.entity.FeedBackEntity;
import com.rpg.app.entity.impl.BriefEntityImpl;
import com.rpg.app.loader.GameLoaderException;
import com.rpg.app.loader.GameLoaderUtils;
import com.rpg.app.property.GameProperty;

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
        //builder.append(briefEntity.getFeedback()).append(ITEM_SEPARATOR); // TODO

        return builder.toString();
    }

    public static BriefEntity fromString(String str) throws GameLoaderException {
        List<String> items = Arrays.asList(str.split("\\s*\\|\\s*"));

        String command = items.get(0).trim();
        GameProperty property = GamePropertyLoader.fromString(items.get(1));
        List<FeedBackEntity> feedBack = new ArrayList<>();
        String text = "";

        if (items.size() > 2) {
            text = GameLoaderUtils.readString(items.get(2), "").trim();

            if (items.size() > 3) {
                for (int i = 3; i < items.size(); i++) {
                    feedBack.add(FeedBackEntityLoader.fromString(items.get(i)));
                }
            }
        }

        return new BriefEntityImpl(command, property, text, feedBack);
    }
}