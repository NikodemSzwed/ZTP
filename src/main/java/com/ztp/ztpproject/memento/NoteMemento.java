package com.ztp.ztpproject.memento;

public class NoteMemento {
    private final String content;

    public NoteMemento(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
