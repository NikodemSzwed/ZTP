package com.ztp.ztpproject.memento;

/**
 * Interface representing a caretaker for managing mementos.
 *
 * This interface provides methods to add a memento from the originator, restore
 * the originator's state from the most recent memento, restore from a specific
 * memento by index, and retrieve the current state of the originator.
 *
 * @param <T> the type of the originator
 */
public interface ICaretaker<T> {

    public void addMementoFromOriginator();

    public void restoreFromLastMemento();

    public void restoreFromMemento(int index);

    public T getOriginator();
}
