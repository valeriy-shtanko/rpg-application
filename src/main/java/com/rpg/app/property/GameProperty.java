package com.rpg.app.property;

/**
 * Created by Valeriy Shtanko on 2017-Nov-18, 12:27
 */
public interface GameProperty {
    String HEALTH_PROPERTY     = "health";
    String STRENGTH_PR0PERTY   = "strength";
    String EXPERIENCE_PR0PARTY = "experience";

    PropertyType getType();

    default String getName() { return ""; }

    default PropertyValue getValue() { return null; }
    default void setValue(PropertyValue value) { }
}
