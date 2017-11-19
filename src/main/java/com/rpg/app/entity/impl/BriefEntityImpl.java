package com.rpg.app.entity.impl;

import com.rpg.app.entity.BriefEntity;
import com.rpg.app.property.GameProperty;

/**
 * Created by Valeriy Shtanko on 2017-Nov-05, 20:45
 */
public class BriefEntityImpl implements BriefEntity {
    private String command;
    private String text;
    private String feedback;
    private GameProperty property;

    public BriefEntityImpl(String command, GameProperty property, String text, String feedback) {
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
    public String getFeedback() {
        return feedback;
    }
}
