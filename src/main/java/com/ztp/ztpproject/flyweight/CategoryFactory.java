package com.ztp.ztpproject.flyweight;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Factory for creating and managing Flyweight Category objects.
 *
 * This factory is responsible for creating and managing Flyweight Category
 * objects. It ensures that only one Category object with the same state exists.
 * If a request for a Category with the same state is received, the factory
 * returns the existing Category object instead of creating a new one.
 */
public class CategoryFactory implements IFlyweightFactory<Category> {

    private final Map<String, Category> cache = new HashMap<>();
    private static CategoryFactory instance;

    /**
     * Retrieves an existing Category or creates a new one if it doesn't exist.
     *
     * @param repeatingState The shared state for the Category.
     * @return The Flyweight Category instance.
     */
    @Override
    public Category getState(String repeatingState) {
        return cache.computeIfAbsent(repeatingState, Category::new);
    }

    /**
     * Retrieves an existing Category without adding a new one if it doesn't
     * exist.
     *
     * @param repeatingState The shared state for the Category.
     * @return The Flyweight Category instance or null if it doesn't exist.
     */
    @Override
    public Category getStateDontAdd(String repeatingState) {
        return cache.get(repeatingState);
    }

    /**
     * Returns a map of all currently managed Flyweight instances.
     *
     * @return Map of categories and their corresponding Flyweight instances.
     */
    @Override
    public Map<String, Category> getAllStates() {
        return Collections.unmodifiableMap(cache);
    }

    /**
     * Returns the single instance of CategoryFactory.
     *
     * @return the single instance of CategoryFactory.
     */
    public static CategoryFactory getInstance() {
        if (instance == null) {
            instance = new CategoryFactory();
        }
        return instance;
    }
}
