package com.ztp.ztpproject.flyweight;

import java.util.Map;

/**
 * Interface defining methods for managing and retrieving shared Flyweight
 * instances.
 */
public interface IFlyweightFactory<T> {

    /**
     * Retrieves or creates a Flyweight instance for the given State.
     *
     * @param repeatingState The key representing the repeating state.
     * @return The shared Flyweight instance.
     */
    T getState(String repeatingState);

    /**
     * Retrieves an existing Flyweight instance without creating a new one if it
     * doesn't exist.
     *
     * @param repeatingState The key representing the repeating state.
     * @return The shared Flyweight instance or null if it doesn't exist.
     */
    T getStateDontAdd(String repeatingState);

    /**
     * Returns a map of all currently managed Flyweight instances.
     *
     * @return Map of categories and their corresponding Flyweight instances.
     */
    Map<String, T> getAllStates();
}
