package com.ztp.ztpproject.command;
import com.ztp.ztpproject.memento.ICaretaker;

public interface ICommand<T> {
    void setCaretaker(ICaretaker<T> caretaker);
    void execute();
    void undo();
}