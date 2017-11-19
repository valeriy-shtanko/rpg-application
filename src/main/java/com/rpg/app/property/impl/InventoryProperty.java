package com.rpg.app.property.impl;


import java.util.List;

import com.rpg.app.property.GameProperty;
import com.rpg.app.property.PropertyType;
import com.rpg.app.property.PropertyValue;

/**
 * Created by Valeriy Shtanko on 2017-Nov-18, 13:49
 */
public class InventoryProperty implements GameProperty {
    private PropertyValue value;

    public InventoryProperty(List<String> ids) {
        value = new PropertyValue(ids);
    }

    @Override
    public PropertyType getType() {
        return PropertyType.INVENTORY;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public PropertyValue getValue() {
        return value;
    }
}
