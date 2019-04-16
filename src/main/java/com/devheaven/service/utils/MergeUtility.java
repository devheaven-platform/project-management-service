package com.devheaven.service.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Set;

/**
 * A utility class for merging the properties of two classes.
 */
public class MergeUtility {

    private final static Logger LOG = LoggerFactory.getLogger(MergeUtility.class);

    /**
     * Merges the properties of two classes. The first object
     * gets priority, the second object will only be picked if
     * the property of the first object is null.
     *
     * @param first  the priority object.
     * @param second the second object.
     * @param <T>    the type of the objects.
     * @return a new object with the merged properties or null
     * if an error occurred.
     */
    public static <T> T merge(T first, T second) {
        try {
            // Get the class
            Class<?> firstClass = first.getClass();

            // Get the fields
            Field[] fields = firstClass.getDeclaredFields();

            Object returnValue = firstClass.getConstructor().newInstance();
            for (Field field : fields) {
                field.setAccessible(true);

                Object value1 = field.get(first);
                Object value2 = field.get(second);

                if (value1 instanceof Set && value2 instanceof Set) {
                    Object value = ((Set) value1).size() > ((Set) value2).size() ? value1 : value2;
                    field.set(returnValue, value);
                } else if (value1 instanceof List && value2 instanceof List) {
                    Object value = ((List) value1).size() > ((List) value2).size() ? value1 : value2;
                    field.set(returnValue, value);
                } else {
                    Object value = (value1 != null) ? value1 : value2;
                    field.set(returnValue, value);
                }
            }
            return (T) returnValue;
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | InstantiationException ex) {
            LOG.error("An error occurred while merging objects", ex);
            return null;
        }
    }

}
