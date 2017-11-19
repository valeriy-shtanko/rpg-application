package com.rpg.app.core;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.rpg.app.entity.BriefEntity;
import com.rpg.app.property.GameProperty;

/**
 * Created by Valeriy Shtanko on 2017-Nov-18, 12:50
 */
public class BriefBank {
    private List<BriefEntity> entities = new ArrayList<>();

    public BriefBank() {

    }

    public void setBriefs(List<BriefEntity> entities) {
        this.entities = entities;
    }

    public BriefEntity findBrief(String command, GameProperty gameProperty) {
        return entities.stream()
                       .filter(b -> b.getCommand().equals(command) && b.getProperty().equals(gameProperty))
                       .findFirst()
                       .orElse(null);
    }

    public List<BriefEntity> findBriefs(GameProperty gameProperty) {
        return entities.stream()
                       .filter(b -> b.getProperty().equals(gameProperty))
                       .collect(Collectors.toList());
    }
}