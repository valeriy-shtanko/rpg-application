package com.rpg.app.entity.impl;

import com.rpg.app.entity.FeedBackEntity;

/**
 * Created by Valeriy Shtanko on 2017-Nov-20, 16:53
 */
public class FeedBackEntityImpl implements FeedBackEntity {
    String targetId;
    String command;
    String name;
    int value;

    FeedBackType type;

    public FeedBackEntityImpl(String targetId, String command, FeedBackType type, String name, int value) {
        this.targetId = targetId;
        this.command = command;
        this.name = name;
        this.value = value;
        this.type = type;
    }

    @Override
    public String getTargetId() {
        return targetId;
    }

    @Override
    public String getCommand() {
        return command;
    }

    @Override
    public FeedBackType getType() {
        return type;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getValue() {
        return value;
    }
}
