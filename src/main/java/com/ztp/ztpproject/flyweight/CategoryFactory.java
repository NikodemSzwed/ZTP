package com.ztp.ztpproject.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * Factory for creating and managing Flyweight Category objects.
 */
public class CategoryFactory {

    private final Map<String, Category> cache = new HashMap<>();

    /**
     * Retrieves an existing Category or creates a new one if it doesn't exist.
     *
     * @param repeatingState The shared state for the Category.
     * @return The Flyweight Category instance.
     */
    public Category getCategory(String repeatingState) {
        return cache.computeIfAbsent(repeatingState, Category::new);
    }

    /**
     * Retrieves an existing Category without adding a new one if it doesn't
     * exist.
     *
     * @param repeatingState The shared state for the Category.
     * @return The Flyweight Category instance or null if it doesn't exist.
     */
    public Category getCategoryDontAdd(String repeatingState) {
        return cache.get(repeatingState);
    }
}
