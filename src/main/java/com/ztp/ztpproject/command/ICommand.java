package com.ztp.ztpproject.command;

import com.ztp.ztpproject.memento.ICaretaker;

/**
 * Interface representing a command that can be executed and undone.
 *
 * Each command needs to have a caretaker associated with it, which is used to
 * store and retrieve the memento object representing the state of the
 * originator.
 *
 * @param <T> the type of the originator
 */
public interface ICommand<T> {

    void setCaretaker(ICaretaker<T> caretaker);

    void execute();

    void undo();
}
