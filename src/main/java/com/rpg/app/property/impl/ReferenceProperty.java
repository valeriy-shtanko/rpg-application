package com.rpg.app.property.impl;

import com.rpg.app.property.GameProperty;
import com.rpg.app.property.PropertyType;
import com.rpg.app.property.PropertyValue;

/**
 * Created by Valeriy Shtanko on 2017-Nov-18, 13:41
 */
public class ReferenceProperty implements GameProperty {
    private PropertyValue value;

    @Override
    public PropertyType getType() {
        return PropertyType.REFERENCE;
    }

    public ReferenceProperty(String reference) {
        this.value = new PropertyValue(reference);
    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public PropertyValue getValue() {
        return value;
    }

}
