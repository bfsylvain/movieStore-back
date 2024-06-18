package com.moviestore.checkpoint.util;

import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Component
public class Patcher {
    public <T> void elementPatcher(T existingElement, T incompleteElement) throws IllegalAccessException {

        Class<?> elementClass = existingElement.getClass();
        Field[] fields = elementClass.getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);

            Object value = field.get(incompleteElement);
            if(value != null) {
                field.set(existingElement, value);
            }

            field.setAccessible(false);
        }

    }
}
