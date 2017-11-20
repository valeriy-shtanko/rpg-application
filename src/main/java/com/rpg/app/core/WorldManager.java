package com.rpg.app.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.rpg.app.entity.BriefEntity;
import com.rpg.app.entity.FeedBackEntity;
import com.rpg.app.entity.GameObject;
import com.rpg.app.entity.SceneEntity;
import com.rpg.app.entity.impl.GameObjectImpl;
import com.rpg.app.property.GameProperty;
import com.rpg.app.property.PropertyType;
import com.rpg.app.property.PropertyValue;
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
        player.getProperties().add(new ValueProperty(GameProperty.EXPERIENCE_PR0PARTY, 18));

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

    public SceneBank getScenes() {
        return sceneBank;
    }

    public SceneEntity getActiveScene() {
        return activeScene;
    }

    public void setActiveScene(SceneEntity activeScene) {
        this.activeScene = activeScene;
    }

    public List<GameObject> getActiveObjects() {
        return activeObjects;
    }

    public void moveToScene(String sceneId) throws Exception {
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

        List<BriefEntity> feedBacResult = applyFeedBack(result);
        result.addAll(feedBacResult);

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
    private List<BriefEntity> applyFeedBack(List<BriefEntity> briefs)  throws Exception {
        List<BriefEntity> result = Collections.emptyList();

        for(BriefEntity entity : briefs) {
            for(FeedBackEntity feedBackEntity : entity.getFeedback()) {
                switch(feedBackEntity.getCommand().toUpperCase()) {
                    case "ADD":
                        feedBackADD(getActiveGameObject(feedBackEntity.getTargetId()), feedBackEntity);
                        break;
                    case "KILL":
                        feedBackKILL(feedBackEntity.getTargetId());
                        break;
                    case "OFF":
                        feedBackOFF(getActiveGameObject(feedBackEntity.getTargetId()), feedBackEntity);
                        break;
                    case "SET":
                        feedBackSET(getActiveGameObject(feedBackEntity.getTargetId()), feedBackEntity);
                        break;
                    case "PLUS":
                        result = feedBackPLUS(getActiveGameObject(feedBackEntity.getTargetId()), feedBackEntity);
                        break;
                    case "MAKE":
                        feedBackMAKE(feedBackEntity);
                        break;
                }
            }
        }

        return result;
    }

    private void applyMove(GameProperty gameProperty) throws Exception {
        boolean isWantedObject = false;
        String sceneId = "";

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
                        sceneId = property.getValue().asString();
                        break;
                    }
                }
            }

            if (!sceneId.isEmpty()) {
                break;
            }
        }

        if (!sceneId.isEmpty()) {
            moveToScene(sceneId);
        }
    }

    private List<BriefEntity> doApply(String command, GameProperty gameProperty) throws Exception {
        List<BriefEntity> result = new ArrayList<>();
        boolean isWantedObject = false;

        for(GameObject gameObject : activeObjects) {
            for(GameProperty property : gameObject.getProperties()) {
                if(/*property.getType() == PropertyType.NAME && */property.getName().equals(gameProperty.getName())) {
                    isWantedObject = true;
                    break;
                }
            }

            if (isWantedObject) {
                BriefEntity briefEntity = briefBank.findBrief(command, gameProperty);

                if (briefEntity != null) {
                    result.add(briefEntity);
                }

                isWantedObject = false;
            }
        }

        return result;
    }


    private GameObject getActiveGameObject(String id) {
        return activeObjects.stream()
                            .filter(o -> o.getId().equalsIgnoreCase(id))
                            .findFirst().orElse(null);
    }

    private GameObject getActiveSceneObject(String id) {
        return activeScene.getGameObjects().stream()
                          .filter(o -> o.getId().equalsIgnoreCase(id))
                          .findFirst().orElse(null);
    }


    private void feedBackADD (GameObject gameObject, FeedBackEntity feedBackEntity) {
        if(gameObject == null) {
            return;
        }

        // FIXME: Move creation of property in another place
        switch(feedBackEntity.getType()) {
            case NAME :
                gameObject.getProperties().add(new NameProperty(feedBackEntity.getName()));
                break;
            case VALUE:
                gameObject.getProperties().add(new ValueProperty(feedBackEntity.getName(), feedBackEntity.getValue()));
                break;
        }
    }

    private void feedBackOFF(GameObject gameObject, FeedBackEntity feedBackEntity) {
        if(gameObject == null) {
            return;
        }

        GameProperty property = gameObject.getProperties().stream()
                                          .filter(p -> p.getName().equalsIgnoreCase(feedBackEntity.getName()))
                                          .findAny()
                                          .orElse(null);

        if (property != null) {
            gameObject.getProperties().remove(property);
        }
    }

    private void feedBackKILL(String objectId) {
        GameObject gameObject = getActiveGameObject(objectId);

        if (gameObject != null) {
            activeObjects.remove(gameObject);
        }

        gameObject = getActiveSceneObject(objectId);

        if (gameObject != null) {
           activeScene.getGameObjects().remove(gameObject);
        }
    }

    private void feedBackSET(GameObject gameObject, FeedBackEntity feedBackEntity) {
        if(gameObject == null) {
            return;
        }

        GameProperty property = gameObject.getProperties().stream()
                                          .filter(p -> p.getName().equalsIgnoreCase(feedBackEntity.getName()))
                                          .findAny()
                                          .orElse(null);

        if (property != null) {
            property.setValue(new PropertyValue(feedBackEntity.getValue()));
        }
    }

    private List<BriefEntity> feedBackPLUS(GameObject gameObject, FeedBackEntity feedBackEntity) throws Exception {
        if(gameObject == null) {
            return Collections.emptyList();
        }

        List<BriefEntity> result = Collections.emptyList();

        GameProperty property = gameObject.getProperties().stream()
                                          .filter(p -> p.getName().equalsIgnoreCase(feedBackEntity.getName()))
                                          .findAny()
                                          .orElse(null);

        if (property != null) {
            property.setValue(new PropertyValue( property.getValue().asInteger() + feedBackEntity.getValue()));

            if (property.getValue().asInteger() <= 0) {
                result = applyCommand("DEPLETE", property);
            }
        }

        return result;
    }

    private void feedBackMAKE(FeedBackEntity feedBackEntity) {
        GameObject gameObject = activeScene.getGameObjects().stream()
                                           .filter(o -> o.getId().equalsIgnoreCase(feedBackEntity.getTargetId()))
                                           .findAny()
                                           .orElse(null);

        if(gameObject == null) {
            GameObject newGameObject = new GameObjectImpl(feedBackEntity.getTargetId());  // FIXME: move object creation in another place

            activeScene.getGameObjects().add(newGameObject);
            activeObjects.add(newGameObject);
        }
    }
}