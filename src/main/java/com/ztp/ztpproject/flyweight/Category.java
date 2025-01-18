package com.ztp.ztpproject.flyweight;

/**
 * Flyweight class representing a Category. This class encapsulates shared
 * (repeating) state. The Flyweight pattern is a structural design pattern that
 * provides a way to save memory by reusing parts of an object. This class
 * specifically represents a Category in the context of a task management
 * system. Categories are used to group tasks together and provide a way to
 * track and manage tasks.
 *
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

    // Below are some overridden helping methods to work on the object. 
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
        return repeatingState.equals(category.getRepeatingState());
    }

    @Override
    public int hashCode() {
        return repeatingState.hashCode();
    }
}
