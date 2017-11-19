package com.rpg.app.property;

/**
 * Created by Valeriy Shtanko on 2017-Nov-18, 12:27
 */
public interface GameProperty {
    String HEALTH_PROPERTY     = "health";
    String STRENGTH_PR0PERTY   = "strengh";
    String EXPIRIENCE_PR0PERTY = "expirience";

    PropertyType getType();

    default String getName() { return ""; }

    default PropertyValue getValue() { return null; }
}
