package com.ztp.ztpproject.flyweight;

/**
 * Flyweight class representing a Tag. Encapsulates shared (repeating) state.
 */
public class Tag {

    private final String repeatingState; // Shared immutable state

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
        return repeatingState.equals(tag.repeatingState);
    }

    @Override
    public int hashCode() {
        return repeatingState.hashCode();
    }
}
