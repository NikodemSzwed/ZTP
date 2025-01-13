package com.ztp.ztpproject.command;

import com.ztp.ztpproject.models.Note;

public class AppendCommand implements Command {
    private Note note;
    private String content;

    public AppendCommand(Note note, String content) {
        this.note = note;
        this.content = content;
    }

    @Override
    public void execute() {
        note.append(content);
    }

    @Override
    public void undo() {
        note.delete(content.length());
    }
}