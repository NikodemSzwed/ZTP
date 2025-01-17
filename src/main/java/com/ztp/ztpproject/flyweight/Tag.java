package com.ztp.ztpproject.flyweight;

/**
 * Flyweight class representing a Tag. This class encapsulates shared
 * (repeating) state. The Flyweight pattern is a structural design pattern that
 * provides a way to save memory by reusing parts of an object. This class
 * specifically represents a Tag in the context of a task management system.
 * Tags are used to group tasks together and provide a way to track and manage
 * tasks.
 *
 */
public class Tag {

    private final String repeatingState;

    /**
     * Constructor for Tag.
     *
     * @param repeatingState The shared state for the Tag.
     */
    public Tag(String repeatingState) {
        if (repeatingState == null || repeatingState.isEmpty()) {
            throw new IllegalArgumentException("Repeating state cannot be null or empty");
        }
        this.repeatingState = repeatingState;
    }

    /**
     * Retrieves the repeating state of this Tag.
     *
     * @return The repeating state.
     */
    public String getRepeatingState() {
        return repeatingState;
    }

    // Below are some overridden helping methods to work on the object. 
    // They maybe adjusted in the future.
    @Override
    public String toString() {
        return "Tag{"
                + "repeatingState='" + repeatingState + '\''
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Tag tag = (Tag) o;
        return repeatingState.equals(tag.getRepeatingState());
    }

    @Override
    public int hashCode() {
        return repeatingState.hashCode();
    }
}
