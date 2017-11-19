package com.rpg.app.entity;

import java.util.List;


/**
 * Created by Valeriy Shtanko on 2017-Nov-18, 12:15
 */
public interface SceneEntity {
    String getId();

    List<GameObject> getGameObjects();
    void setGameObjects(List<GameObject> gameObjects);
}
