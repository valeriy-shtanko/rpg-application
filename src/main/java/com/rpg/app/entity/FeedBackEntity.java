package com.rpg.app.entity;

/**
 * Created by Valeriy Shtanko on 2017-Nov-20, 16:49
 */
public interface FeedBackEntity {

    String getTargetId();
    String getCommand();
    FeedBackType getType();

    String getName();
    int getValue();

    public enum FeedBackType {
        NAME,
        VALUE
    }
}
