package com.rpg.app.core;

import java.util.ArrayList;
import java.util.List;

import com.rpg.app.entity.SceneEntity;

/**
 * Created by Valeriy Shtanko on 2017-Nov-18, 14:00
 */
public class SceneBank {
    private List<SceneEntity> entities = new ArrayList<>();

    public SceneBank() {
    }

    public void setScenes(List<SceneEntity> entities) {
        this.entities = entities;
    }

    public SceneEntity getScene(String sceneId) throws Exception {
        SceneEntity scene = entities.stream()
                                  .filter(s -> s.getId().equals(sceneId))
                                  .findFirst()
                                  .orElseThrow(() -> new Exception("Scene not found."));

        return scene;
    }
}
