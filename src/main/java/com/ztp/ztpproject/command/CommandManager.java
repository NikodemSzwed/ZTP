package com.ztp.ztpproject.command;

import java.util.Stack;

import com.ztp.ztpproject.memento.ICaretaker;

public class CommandManager<T> {
    private Stack<ICommand<T>> history = new Stack<>();
    private Stack<ICommand<T>> redoHistory = new Stack<>();
    private ICaretaker<T> caretaker;
    
    public CommandManager(ICaretaker<T> caretaker){
        this.caretaker = caretaker;
    }

    /**
     * Execute the given command.
     * This method first clears the redo history,
     * then sets the caretaker of the command to the caretaker of this manager,
     * executes the command, and finally pushes the command to the history stack.
     *
     * @param command the command to be executed
     */
    public void executeCommand(ICommand<T> command) {
        redoHistory.clear();
        command.setCaretaker(caretaker);
        command.execute();
        history.push(command);
    }

    /**
     * Reverts the last executed command.
     * This method checks if there is any command in the history stack.
     * If there is, it pops the command from the history stack, 
     * pushes it to the redoHistory stack, and then calls the undo method on it.
     */
    public void undoCommand() {
        if(!history.isEmpty()) {
            ICommand<T> command = history.pop();
            redoHistory.push(command);
            command.undo();
        }
    }

    /**
     * Re-executes the last reverted command.
     * This method checks if there is any command in the redoHistory stack.
     * If there is, it pops the command from the redoHistory stack, and then calls the execute method on it.
     */
    public void redoCommand() {
        if(!redoHistory.isEmpty()) {
            ICommand<T> command = redoHistory.pop();
            command.execute();
        }
    }

    /**
     * Rolls back all changes.
     * This method undoes all commands in the history stack.
     */
    public void rollbackAllChanges() {
        while(!history.isEmpty()) {
            undoCommand();
        }
    }
}
