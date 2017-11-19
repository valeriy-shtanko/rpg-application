package com.rpg.app.property.impl;

import com.rpg.app.property.GameProperty;
import com.rpg.app.property.PropertyType;
import com.rpg.app.property.PropertyValue;

/**
 * Created by Valeriy Shtanko on 2017-Nov-18, 13:40
 */
public class NameProperty implements GameProperty {
    private String name;


    public NameProperty(String name) {
        this.name = name;
    }

    @Override
    public PropertyType getType() {
        return PropertyType.NAME;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public PropertyValue getValue() {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        NameProperty that = (NameProperty) o;

        if (PropertyType.NAME != that.getType()) {
            return false;
        }
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        int result = PropertyType.NAME.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }
}