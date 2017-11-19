package com.rpg.app.core;

import java.util.ArrayList;
import java.util.List;

import com.rpg.app.entity.BriefEntity;
import com.rpg.app.entity.GameObject;
import com.rpg.app.property.GameProperty;

/**
 * Created by Valeriy Shtanko on 2017-Nov-18, 13:07
 */
public class Player implements GameObject {
    private String id;
    private List<GameProperty> properties = new ArrayList<>();

    public static final String UPGRADE_HEALTH   = "upgrade-health";
    public static final String UPGRADE_STRENGTH = "upgrade-strength";

    public Player(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public List<GameProperty> getProperties() {
        return properties;
    }

    @Override
    public void setProperties(List<GameProperty> properties) {
        this.properties = properties;
    }

    @Override
    public BriefEntity applyCommand(String command) {
        return null;
    }
}
