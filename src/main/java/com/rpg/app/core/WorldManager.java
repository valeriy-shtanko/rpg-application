package com.rpg.app.core;

import java.util.ArrayList;
import java.util.List;

import com.rpg.app.entity.BriefEntity;
import com.rpg.app.entity.GameObject;
import com.rpg.app.entity.SceneEntity;
import com.rpg.app.property.GameProperty;
import com.rpg.app.property.PropertyType;
import com.rpg.app.property.impl.NameProperty;
import com.rpg.app.property.impl.ValueProperty;

/**
 * Created by Valeriy Shtanko on 2017-Nov-18, 13:06
 */
public class WorldManager {
    private SceneBank sceneBank = new SceneBank();
    private BriefBank briefBank = new BriefBank();

    private Player player;

    private SceneEntity activeScene;
    private List<GameObject> activeObjects  = new ArrayList<>();

    public WorldManager() {
        player = new Player("000");

        player.getProperties().add(new ValueProperty(GameProperty.HEALTH_PROPERTY, 1));
        player.getProperties().add(new ValueProperty(GameProperty.STRENGTH_PR0PERTY, 1));
        player.getProperties().add(new ValueProperty(GameProperty.EXPIRIENCE_PR0PERTY, 18));
        player.getProperties().add(new NameProperty(Player.UPGRADE_HEALTH));
        player.getProperties().add(new NameProperty(Player.UPGRADE_STRENGTH));
    }

    public void init(List<BriefEntity> briefs, List<SceneEntity> scenes) {
        briefBank.setBriefs(briefs);
        sceneBank.setScenes(scenes);
    }

    public Player getPlayer() {
        return player;
    }

    public void moveToScene(String sceneId)  throws Exception {
        activeScene = sceneBank.getScene(sceneId);

        activeObjects.clear();
        activeObjects.add(player);
        activeObjects.addAll(activeScene.getGameObjects());
    }

    public List<BriefEntity> applyCommand(String command, GameProperty gameProperty) throws Exception {
        List<BriefEntity> result = new ArrayList<>();

        if (gameProperty == null) {
            for(GameObject gameObject : activeObjects) {
                for(GameProperty property : gameObject.getProperties()) {
                    BriefEntity briefEntity = briefBank.findBrief(command, property);

                    if (briefEntity != null) {
                        result.add(briefEntity);
                    }
                }
            }
        } else {
            // This section should be very generic, but some specific cases possible
            if("GO".equals(command)) {
                applyMove(gameProperty);
                result = applyCommand("INIT", null);
            } else {
                result = doApply(command, gameProperty);
            }
        }

        return result;
    }

    public List<BriefEntity> invokeOptions() {
        List<BriefEntity> result = new ArrayList<>();

        for(GameObject gameObject : activeObjects) {
            for(GameProperty property : gameObject.getProperties()) {
                if (property.getType() == PropertyType.NAME) {
                    result.addAll(briefBank.findBriefs(property));
                }
            }
        }

        return result;
    }

    public List<BriefEntity> refresh() throws Exception {
        return applyCommand("INIT", null);
    }

    //---------------------------------------------------------------------------------------------
    // Private methods
    //
    private void applyMove(GameProperty gameProperty) throws Exception {
        boolean isWantedObject = false;

        for(GameObject gameObject : activeObjects) {
            for(GameProperty property : gameObject.getProperties()) {
                if(property.getType() == PropertyType.NAME && property.getName().equals(gameProperty.getName())) {
                    isWantedObject = true;
                    break;
                }
            }

            if (isWantedObject) {
                for(GameProperty property : gameObject.getProperties()) {
                    if(property.getType() == PropertyType.REFERENCE) {
                        moveToScene(property.getValue().asString());
                    }
                }
            }
        }
    }

    private List<BriefEntity> doApply(String command, GameProperty gameProperty) throws Exception {
        List<BriefEntity> result = new ArrayList<>();
        boolean isWantedObject = false;

        for(GameObject gameObject : activeObjects) {
            for(GameProperty property : gameObject.getProperties()) {
                if(property.getType() == PropertyType.NAME && property.getName().equals(gameProperty.getName())) {
                    isWantedObject = true;
                    break;
                }
            }

            if (isWantedObject) {
                BriefEntity briefEntity = briefBank.findBrief(command, gameProperty);

                if (briefEntity != null) {
                    result.add(briefEntity);
                }
            }
        }

        return result;
    }
}