package com.rpg.app.loader;

import java.util.Arrays;

import org.junit.Test;

import com.rpg.app.entity.BriefEntity;
import com.rpg.app.entity.FeedBackEntity;
import com.rpg.app.entity.GameObject;
import com.rpg.app.entity.SceneEntity;
import com.rpg.app.loader.entity.BriefLoader;
import com.rpg.app.loader.entity.FeedBackEntityLoader;
import com.rpg.app.loader.entity.GameObjectLoader;
import com.rpg.app.loader.entity.GamePropertyLoader;
import com.rpg.app.loader.entity.SceneLoader;
import com.rpg.app.property.GameProperty;
import com.rpg.app.property.PropertyType;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

/**
 * Created by Valeriy Shtanko on 2017-Nov-16, 16:45
 */
public class GameLoaderlTest {
    @Test
    public void testLoadAndSaveNameProperty() throws GameLoaderException {
        String propertyString = PropertyType.NAME + ";" + "name;" + ";";

        // Load
        GameProperty property = GamePropertyLoader.fromString(propertyString);

        assertEquals(property.getType(), PropertyType.NAME);
        assertEquals(property.getName(), "name");
        assertNull(property.getValue());

        // Save
        assertEquals(propertyString, GamePropertyLoader.toString(property));
    }

    @Test
    public void testLoadAndSaveValueProperty() throws GameLoaderException {
        String propertyString = PropertyType.VALUE + ";" + "name;" + "111;";

        // Load
        GameProperty property = GamePropertyLoader.fromString(propertyString);

        assertEquals(property.getType(), PropertyType.VALUE);
        assertEquals(property.getName(), "name");
        assertEquals(property.getValue().asInteger(), 111);

        // Save
        assertEquals(propertyString, GamePropertyLoader.toString(property));
    }

    @Test
    public void testLoadAndSaveReferenceProperty() throws GameLoaderException {
        String propertyString = PropertyType.REFERENCE + ";" + ";" + "id;";

        // Load
        GameProperty property = GamePropertyLoader.fromString(propertyString);

        assertEquals(property.getType(), PropertyType.REFERENCE);
        assertNull(property.getName());
        assertEquals(property.getValue().asString(), "id");

        // Save
        assertEquals(propertyString, GamePropertyLoader.toString(property));
    }

    @Test
    public void testLoadAndSaveInventoryProperty() throws GameLoaderException {
        String propertyString = PropertyType.INVENTORY + ";" + ";" + "oid-1,oid-2,oid-3;";

        // Load
        GameProperty property = GamePropertyLoader.fromString(propertyString);

        assertEquals(property.getType(), PropertyType.INVENTORY);
        assertNull(property.getName());
        assertEquals(property.getValue().asList(), Arrays.asList("oid-1", "oid-2", "oid-3"));

        // Save
        assertEquals(propertyString, GamePropertyLoader.toString(property));
    }

    @Test
    public void testLoadAndSaveBriefEntity() throws GameLoaderException {
        String propertyString = PropertyType.VALUE + ";" + "name;" + "111;";
        String briefString = "command|" + propertyString + "|text|001;TAKE;NAME;name-1;null|002;GIVE;VALUE;name-2;123";

        // Load
        BriefEntity entity = BriefLoader.fromString(briefString);

        assertEquals(entity.getCommand(), "command");

        assertEquals(entity.getProperty().getType(), PropertyType.VALUE);
        assertEquals(entity.getProperty().getName(), "name");
        assertEquals(entity.getProperty().getValue().asInteger(), 111);

        assertEquals(entity.getText(), "text");
        assertEquals(2, entity.getFeedback().size());

        // Save
        // assertEquals(briefString, BriefLoader.toString(entity)); TODO
    }

    @Test
    public void testLoadAndSaveGameObject() throws GameLoaderException {
        String gameObjectString = "A1|NAME;property-1;;|VALUE;property-2;111;|REFERENCE;;id-xxx;|INVENTORY;;id-1,id-2,id-3;|";

        // Load
        GameObject gameObject = GameObjectLoader.fromString(gameObjectString);

        assertEquals("A1", gameObject.getId());
        assertEquals(4, gameObject.getProperties().size());

        // Save
        assertEquals(gameObjectString, GameObjectLoader.toString(gameObject));
    }

    @Test
    public void testLoadAndSaveScene() throws GameLoaderException {
        String sceneString = "001@A1|NAME;property-1;;|VALUE;property-2;111;|@A2|REFERENCE;;id-xxx;|INVENTORY;;id-1,id-2,id-3;|@";

        // Load
        SceneEntity scene = SceneLoader.fromString(sceneString);

        assertEquals("001", scene.getId());
        assertEquals(2, scene.getGameObjects().size());

        GameObject gameObject = scene.getGameObjects().get(0);
        assertEquals("A1", gameObject.getId());
        assertEquals(2, gameObject.getProperties().size());

        gameObject = scene.getGameObjects().get(1);
        assertEquals("A2", gameObject.getId());
        assertEquals(2, gameObject.getProperties().size());

        // Save
        assertEquals(sceneString, SceneLoader.toString(scene));
    }

    @Test
    public void testLoadFeedback()  {
        String feedbackStr1 = "001;TAKE;NAME;name-1;123";

        FeedBackEntity feedback = FeedBackEntityLoader.fromString(feedbackStr1);

        assertEquals("001", feedback.getTargetId());
        assertEquals("TAKE", feedback.getCommand());
        assertEquals(FeedBackEntity.FeedBackType.NAME, feedback.getType());
        assertEquals("name-1", feedback.getName());
        assertEquals(0, feedback.getValue());

        feedbackStr1 = "001;TAKE;VALUE;name-1;123";
        feedback = FeedBackEntityLoader.fromString(feedbackStr1);

        assertEquals(123, feedback.getValue());
    }
}