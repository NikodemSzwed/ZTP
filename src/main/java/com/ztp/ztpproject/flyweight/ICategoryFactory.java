package com.ztp.ztpproject.flyweight;

import java.util.Map;

/**
 * Interface defining methods for managing and retrieving shared Flyweight
 * instances.
 */
public interface ICategoryFactory {

    /**
     * Retrieves or creates a Flyweight instance for the given category.
     *
     * @param category The key representing the category.
     * @return The shared Flyweight instance.
     */
    Object getCategory(String category);

    /**
     * Returns a map of all currently managed Flyweight instances.
     *
     * @return Map of categories and their corresponding Flyweight instances.
     */
    Map<String, Object> getAllCategories();
}
