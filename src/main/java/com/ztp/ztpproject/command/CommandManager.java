package com.ztp.ztpproject.command;

import com.ztp.ztpproject.memento.NoteMemento;
import com.ztp.ztpproject.models.Note;

import java.util.Stack;

public class CommandManager {
    private Stack<Command> history = new Stack<>();
    private Stack<Command> redoHistory = new Stack<>();
    private Stack<NoteMemento> mementos = new Stack<>();

    public void executeCommand(Command command, Note note) {
        command.execute();
        history.push(command);
        redoHistory.clear(); // Clear redo history after a new action
        mementos.push(note.save());
    }

    public void undo(Note note) {
        if (!history.isEmpty()) {
            Command command = history.pop();
            command.undo();
            redoHistory.push(command);
            if (!mementos.isEmpty()) {
                note.restore(mementos.pop());
            }
        } else {
            System.out.println("No commands to undo.");
        }
    }

    public void redo(Note note) {
        if (!redoHistory.isEmpty()) {
            Command command = redoHistory.pop();
            command.execute();
            history.push(command);
            mementos.push(note.save());
        } else {
            System.out.println("No commands to redo.");
        }
    }

    public void showHistory() {
        if (mementos.isEmpty()) {
            System.out.println("No mementos.");
        } else {
            for (int i = 0; i < mementos.size(); i++) {
                System.out.println((i + 1) + ". " + mementos.get(i).getContent());
            }
        }
    }
}