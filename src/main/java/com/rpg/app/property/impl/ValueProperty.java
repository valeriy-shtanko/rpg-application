package com.rpg.app.property.impl;

import com.rpg.app.property.GameProperty;
import com.rpg.app.property.PropertyType;
import com.rpg.app.property.PropertyValue;

/**
 * Created by Valeriy Shtanko on 2017-Nov-18, 13:40
 */
public class ValueProperty implements GameProperty {
    private String name;
    private PropertyValue value;

    @Override
    public PropertyType getType() {
        return PropertyType.VALUE;
    }

    public ValueProperty(String name, int value) {
        this.name = name;
        this.value = new PropertyValue(value);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public PropertyValue getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ValueProperty)) {
            return false;
        }

        ValueProperty that = (ValueProperty) o;

        if (!name.equals(that.name)) {
            return false;
        }

        if (!value.equals(that.value)) {
            return false;
        }

        return PropertyType.VALUE == that.getType();
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();

        result = 31 * result + value.hashCode();
        result = 31 * result + PropertyType.VALUE.hashCode();

        return result;
    }
}
