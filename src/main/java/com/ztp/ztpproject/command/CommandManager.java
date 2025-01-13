package com.ztp.ztpproject.command;

import com.ztp.ztpproject.memento.NoteMemento;
import com.ztp.ztpproject.models.Note;

import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class CommandManager {
    private Stack<Command> history = new Stack<>();
    private Stack<Command> redoHistory = new Stack<>();
    private List<NoteMemento> mementos = new ArrayList<>();

    public void executeCommand(Command command, Note note) {
        command.execute();
        history.push(command);
        redoHistory.clear(); // Clear redo history after a new action
        mementos.add(note.save());
    }

    public void undo(Note note) {
        if (!history.isEmpty()) {
            Command command = history.pop();
            command.undo();
            redoHistory.push(command);
            // Add current state to mementos after undo
            mementos.add(note.save());
        } else {
            System.out.println("No commands to undo.");
        }
    }

    public void redo(Note note) {
        if (!redoHistory.isEmpty()) {
            Command command = redoHistory.pop();
            command.execute();
            history.push(command);
            mementos.add(note.save());
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

    public void restoreToState(Note note, int index) {
        if (index >= 1 && index <= mementos.size()) {
            note.restore(mementos.get(index - 1));
            System.out.println("Restored to state " + index);
            // Save the restored state as a new memento
            mementos.add(note.save());
        } else {
            System.out.println("Invalid state index.");
        }
    }

    public void clearMementos() {
        mementos.clear();
        System.out.println("Mementos cleared.");
    }
}