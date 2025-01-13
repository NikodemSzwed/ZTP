package com.ztp.ztpproject.models;

import com.ztp.ztpproject.memento.*;



public class Note {
    private StringBuilder content = new StringBuilder();

    public void append(String text) {
        content.append(text);
    }

    public void delete(int length) {
        content.delete(content.length() - length, content.length());
    }

    public String getContent() {
        return content.toString();
    }

    public NoteMemento save() {
        return new NoteMemento(content.toString());
    }

    public void restore(NoteMemento memento) {
        content = new StringBuilder(memento.getContent());
    }
}