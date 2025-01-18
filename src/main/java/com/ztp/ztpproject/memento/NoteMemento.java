package com.ztp.ztpproject.memento;
import com.ztp.ztpproject.models.Note;
import java.util.Date;

public final class NoteMemento {
    private final Note historyEntry;
    private final Date date;

    public NoteMemento(Note note) {
        this.historyEntry = new Note(note);
        this.date = new Date();
    }

    public Note getMemento() {
        return historyEntry;
    }

    public Date getMementoDate() {
        return date;
    }

    @Override
    public String toString() {
        return "NoteMemento{" +
                "historyEntry=" + historyEntry +
                ", date=" + date +
                '}';
    }
}   