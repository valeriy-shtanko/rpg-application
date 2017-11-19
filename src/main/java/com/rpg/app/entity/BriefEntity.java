package com.rpg.app.entity;

import com.rpg.app.property.GameProperty;

/**
 * Created by Valeriy Shtanko on 2017-Nov-05, 20:45
 */
public interface BriefEntity {

    String getCommand();
    String getText();

    GameProperty getProperty();

    String getFeedback();
}
