package com.rpg.app.loader.entity;


import java.util.Arrays;
import java.util.List;

import com.rpg.app.entity.FeedBackEntity;
import com.rpg.app.entity.impl.FeedBackEntityImpl;
import com.rpg.app.loader.GameLoaderUtils;

import static com.rpg.app.loader.GameLoaderUtils.PROPERTY_SEPARATOR;

/**
 * Created by Valeriy Shtanko on 2017-Nov-20, 17:06
 */
public class FeedBackEntityLoader {
    public static FeedBackEntity fromString(String str) {
        List<String> items = Arrays.asList(str.split(String.format("\\s*%s\\s*", PROPERTY_SEPARATOR)));

        String targetId = items.get(0).trim();
        String command = items.get(1).trim();
        FeedBackEntity.FeedBackType type = FeedBackEntity.FeedBackType.valueOf(items.get(2));
        String name = items.get(3).trim();

        int value = 0;

        if (items.size() > 4) {
            if (GameLoaderUtils.isNumeric(items.get(4)) && type == FeedBackEntity.FeedBackType.VALUE) {
                value = Integer.parseInt(items.get(4));
            }
        }

        return new FeedBackEntityImpl(targetId, command, type, name, value);
    }
}
