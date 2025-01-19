package com.ztp.ztpproject.memento;

import com.ztp.ztpproject.models.Note;
import com.ztp.ztpproject.models.Note.ReadOnlyNote;

import java.util.ArrayList;

/**
 * Class responsible for managing the state of a Note object.
 *
 * This class provides methods to capture the current state of a Note object as
 * a NoteMemento and add it to a list of mementos, as well as methods to restore
 * the state of the Note object from a memento at a specified index in the list
 * of mementos. It also provides a method to get the current state of the Note
 * object as a read-only object.
 *
 * @see NoteMemento
 * @see Note
 */
public class NoteCaretaker implements ICaretaker<Note> {

    private Note note;
    private ArrayList<NoteMemento> history;

    public NoteCaretaker(Note note) {
        this.note = note;
        this.history = new ArrayList<NoteMemento>();
    }

    /**
     * Pobiera zapis historii o wskazanym indeksie.
     *
     * @param index indeks zapisu historii
     * @return zapis historii o wskazanym indeksie
     * @throws IndexOutOfBoundsException jeśli indeks jest spoza zakresu
     */
    public NoteMemento getNoteMemento(int index) {
        if (history.size() == 0 || index >= history.size() || index < 0) {
            System.out.println("Brak wpisu z tym indeksem w historii.");
            throw new IndexOutOfBoundsException();
        }
        return history.get(index);
    }

    /**
     * Adds a new memento of the current state of the note to the history list.
     * This method captures the current state of the note and saves it as a
     * memento, which is then added to the history for future restoration.
     */
    public void addMementoFromOriginator() {
        this.history.add(note.saveToMemento());
    }

    /**
     * Restores the state of the note from a memento at the specified index in
     * the history list. If the index is out of range, the method does nothing.
     *
     * @param index the index of the memento to restore from
     */
    public void restoreFromMemento(int index) {
        if (history.size() == 0 || index >= history.size() || index < 0) {
            System.out.println("Brak wpisu z tym indeksem w historii.");
            return;
        }
        note.restoreFromMemento(history.get(index));
    }

    /**
     * Restores the state of the note from the most recent memento in the
     * history list. If there are no mementos in the history, this method
     * outputs a message indicating that there is no history to restore and does
     * nothing.
     */
    public void restoreFromLastMemento() {
        if (history.size() == 0) {
            System.out.println("Brak zapisów historii do przywrócenia.");
            return;
        }
        note.restoreFromMemento(history.get(history.size() - 1));
    }

    /**
     * Gets the current state of the note as a Note object. This method saves
     * the current state of the note as a memento and adds it to the history
     * before returning the note.
     *
     * @return the current state of the note
     */
    public Note getOriginator() {
        addMementoFromOriginator();
        return note;
    }

    /**
     * Returns a read-only version of the current state of the note. This method
     * returns a read-only version of the note, which can be used to safely
     * access the note's properties without modifying the note.
     *
     * @return a read-only version of the current state of the note
     */
    public ReadOnlyNote getReadOnlyOriginator() {
        return note.getReadOnlyNote();
    }

    @Override
    public String toString() {
        return "NoteCaretaker{"
                + "note=" + note
                + ", history=" + history
                + '}';
    }
}
