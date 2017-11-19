package com.rpg.app.entity;

import java.util.List;

import com.rpg.app.property.GameProperty;

/**
 * Created by Valeriy Shtanko on 2017-Nov-18, 12:15
 */
public interface GameObject {

    String getId();

    List<GameProperty> getProperties();
    void setProperties(List<GameProperty> properties);

    BriefEntity applyCommand(String command);
}
