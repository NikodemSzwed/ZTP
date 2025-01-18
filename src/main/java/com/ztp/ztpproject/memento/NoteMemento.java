package com.ztp.ztpproject.memento;

import com.ztp.ztpproject.models.Note;
import java.util.Date;

/**
 * A NoteMemento is an immutable object that represents the state of a note at
 * some point in time. It stores the state of the note and the date when this
 * state was saved. This class is used by the NoteCaretaker to store and
 * retrieve the state of notes.
 */
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
        return "NoteMemento{"
                + "historyEntry=" + historyEntry
                + ", date=" + date
                + '}';
    }
}
