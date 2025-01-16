package com.ztp.ztpproject.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * Factory for creating and managing Flyweight Tag objects.
 */
public class TagFactory {

    private final Map<String, Tag> cache = new HashMap<>();

    /**
     * Retrieves an existing Tag or creates a new one if it doesn't exist.
     *
     * @param repeatingState The shared state for the Tag.
     * @return The Flyweight Tag instance.
     */
    public Tag getTag(String repeatingState) {
        return cache.computeIfAbsent(repeatingState, Tag::new);
    }

    /**
     * Retrieves an existing Tag without creating a new one if it doesn't exist.
     *
     * @param repeatingState The shared state for the Tag.
     * @return The Flyweight Tag instance or null if it doesn't exist.
     */
    public Tag getTagDontAdd(String repeatingState) {
        return cache.get(repeatingState);
    }

    /**
     * Returns the total number of managed Tags in the factory.
     *
     * @return The size of the Tag cache.
     */
    public int getCacheSize() {
        return cache.size();
    }
}
