package com.ztp.ztpproject.flyweight;

/**
 * Flyweight class representing a Category. This class encapsulates shared
 * (repeating) state.
 */
public class Category {

    private final String repeatingState; // Shared immutable state

    /**
     * Constructor for Category.
     *
     * @param repeatingState The shared state for the Category.
     */
    public Category(String repeatingState) {
        if (repeatingState == null || repeatingState.isEmpty()) {
            throw new IllegalArgumentException("Repeating state cannot be null or empty");
        }
        this.repeatingState = repeatingState;
    }

    /**
     * Retrieves the repeating state of this Category.
     *
     * @return The repeating state.
     */
    public String getRepeatingState() {
        return repeatingState;
    }

    // Below are someBelow are some overridden helping methods to work on the object. 
    // They maybe adjusted in the future.
    @Override
    public String toString() {
        return "Category{"
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
        Category category = (Category) o;
        return repeatingState.equals(category.repeatingState);
    }

    @Override
    public int hashCode() {
        return repeatingState.hashCode();
    }
}
