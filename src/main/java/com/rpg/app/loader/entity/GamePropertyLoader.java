package com.rpg.app.loader.entity;

import java.util.Arrays;
import java.util.List;

import com.rpg.app.loader.GameLoaderException;
import com.rpg.app.property.GameProperty;
import com.rpg.app.property.impl.InventoryProperty;
import com.rpg.app.property.impl.NameProperty;
import com.rpg.app.property.PropertyType;
import com.rpg.app.property.impl.ReferenceProperty;
import com.rpg.app.property.impl.ValueProperty;

import static com.rpg.app.loader.GameLoaderUtils.PROPERTY_SEPARATOR;
import static com.rpg.app.loader.GameLoaderUtils.VALUE_SEPARATOR;

/**
 * Created by Valeriy Shtanko on 2017-Nov-19, 12:51
 */
public class GamePropertyLoader {
    public static String toString(GameProperty gameProperty) throws GameLoaderException {
        StringBuilder builder = new StringBuilder(128);

        // Type
        builder.append(gameProperty.getType().name())
               .append(PROPERTY_SEPARATOR);

        // Name an value
        switch(gameProperty.getType()) {
            case NAME:
                builder.append(gameProperty.getName()).append(";");
                builder.append(PROPERTY_SEPARATOR); // no value
                break;

            case VALUE:
                builder.append(gameProperty.getName()).append(PROPERTY_SEPARATOR);
                builder.append(gameProperty.getValue().asInteger()).append(PROPERTY_SEPARATOR);
                break;

            case REFERENCE:
                builder.append(PROPERTY_SEPARATOR); // no name
                builder.append(gameProperty.getValue().asString()).append(PROPERTY_SEPARATOR);
                break;

            case INVENTORY:
                builder.append(PROPERTY_SEPARATOR); // no name

                builder.append(String.join(",", gameProperty.getValue().asList()))
                       .append(PROPERTY_SEPARATOR);
                break;

            default :
                throw new GameLoaderException("Unknown game property type '%s'", gameProperty.getType());
        }

        return builder.toString();
    }

    public static GameProperty fromString(String str) throws GameLoaderException {
        GameProperty property;

        List<String> items = Arrays.asList(str.split(String.format("\\s*%s\\s*", PROPERTY_SEPARATOR)));

        // Type
        PropertyType propertyType = PropertyType.valueOf(items.get(0).trim());

        // Name an value
        switch(propertyType) {
            case NAME:
                if(items.size() < 2) {
                    throw new GameLoaderException("Cannot load NameProperty from string '%s'.", str);
                }

                property = new NameProperty(items.get(1).trim());
                break;

            case VALUE:
                if(items.size() < 3) {
                    throw new GameLoaderException("Cannot load ValueProperty from string '%s'.", str);
                }

                property = new ValueProperty(items.get(1).trim(), Integer.parseInt(items.get(2).trim()));
                break;

            case REFERENCE:
                if(items.size() < 3) {
                    throw new GameLoaderException("Cannot load ReferenceProperty from string '%s'.", str);
                }

                property = new ReferenceProperty(items.get(2).trim());
                break;

            case INVENTORY:
                if(items.size() < 3) {
                    throw new GameLoaderException("Cannot load InventoryProperty from string '%s'.", str);
                }

                List<String> ids = Arrays.asList(items.get(2).split(String.format("\\s*%s\\s*", VALUE_SEPARATOR)));
                property = new InventoryProperty(ids);
                break;

            default :
                throw new GameLoaderException("Unknown game property type '%s'", propertyType);
        }

        return property;
    }

}
