package com.ztp.ztpproject.flyweight;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Factory for creating and managing Flyweight Tag objects.
 *
 * This factory is responsible for creating and managing Flyweight Tag objects.
 * It ensures that only one Tag object with the same state exists. If a request
 * for a Tag with the same state is received, the factory returns the existing
 * Tag object instead of creating a new one.
 *
 * @version 1.0
 */
public class TagFactory implements IFlyweightFactory<Tag> {

    private final Map<String, Tag> cache = new HashMap<>();

    /**
     * Retrieves an existing Tag or creates a new one if it doesn't exist.
     *
     * @param repeatingState The shared state for the Tag.
     * @return The Flyweight Tag instance.
     */
    @Override
    public Tag getState(String repeatingState) {
        return cache.computeIfAbsent(repeatingState, Tag::new);
    }

    /**
     * Retrieves an existing Tag without creating a new one if it doesn't exist.
     *
     * @param repeatingState The shared state for the Tag.
     * @return The Flyweight Tag instance or null if it doesn't exist.
     */
    @Override
    public Tag getStateDontAdd(String repeatingState) {
        return cache.get(repeatingState);
    }

    /**
     * Returns a map of all currently managed Flyweight instances.
     *
     * @return Map of categories and their corresponding Flyweight instances.
     */
    @Override
    public Map<String, Tag> getAllStates() {
        return Collections.unmodifiableMap(cache);
    }
}
