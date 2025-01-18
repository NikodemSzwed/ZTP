package com.ztp.ztpproject.command;

import com.ztp.ztpproject.memento.ICaretaker;

/**
 * Abstract class for all commands that need to have a caretaker.
 *
 * This class provides a default implementation for the setCaretaker method,
 * which is used to store the caretaker for the command.
 *
 * @param <T> the type of the originator
 */
public abstract class AbstractCommand<T> implements ICommand<T> {

    protected ICaretaker<T> caretaker;

    /**
     * Sets the caretaker for this command if it is not already set.
     *
     * @param caretaker the caretaker to be assigned
     */
    @Override
    public void setCaretaker(ICaretaker<T> caretaker) {
        if (this.caretaker == null) {
            this.caretaker = caretaker;
        }
    }
}
