package com.rpg.app.entity.impl;

import java.util.ArrayList;
import java.util.List;

import com.rpg.app.entity.GameObject;
import com.rpg.app.entity.SceneEntity;

/**
 * Created by Valeriy Shtanko on 2017-Nov-18, 12:15
 */
public class SceneEntityImpl implements SceneEntity {
    private String id;
    private List<GameObject> gameObjects = new ArrayList<>();

    public SceneEntityImpl(String id) {
        this.id = id;
    }

    public SceneEntityImpl(String id, List<GameObject> gameObjects) {
        this.id = id;
        this.gameObjects = gameObjects;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public List<GameObject> getGameObjects() {
        return gameObjects;
    }

    @Override
    public void setGameObjects(List<GameObject> gameObjects) {
        this.gameObjects = gameObjects;
    }
}