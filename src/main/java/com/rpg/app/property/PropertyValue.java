package com.rpg.app.property;

import java.util.List;

/**
 * Created by Valeriy Shtanko on 2017-Nov-19, 01:05
 */
public class PropertyValue {
    private Object value;

    public PropertyValue(Object value) {
        this.value = value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String asString() {
        return (String) value;
    }

    public int asInteger() {
        return (Integer) value;
    }

    @SuppressWarnings("unchecked")
    public List<String> asList() {
        return (List<String>) value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof PropertyValue)) {
            return false;
        }

        PropertyValue that = (PropertyValue) o;

        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
