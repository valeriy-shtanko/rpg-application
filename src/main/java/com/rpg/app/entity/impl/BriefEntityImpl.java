package com.rpg.app.entity.impl;

import java.util.List;

import com.rpg.app.entity.BriefEntity;
import com.rpg.app.entity.FeedBackEntity;
import com.rpg.app.property.GameProperty;

/**
 * Created by Valeriy Shtanko on 2017-Nov-05, 20:45
 */
public class BriefEntityImpl implements BriefEntity {
    private String command;
    private String text;
    private GameProperty property;
    List<FeedBackEntity> feedback;

    public BriefEntityImpl(String command, GameProperty property, String text, List<FeedBackEntity> feedback) {
        this.text = text;
        this.command = command;
        this.property = property;
        this.feedback = feedback;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public String getCommand() {
        return command;
    }

    @Override
    public GameProperty getProperty() {
        return property;
    }

    @Override
    public List<FeedBackEntity> getFeedback() {
        return feedback;
    }
}
