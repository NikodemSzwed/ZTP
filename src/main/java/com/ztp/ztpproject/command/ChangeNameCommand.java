package com.ztp.ztpproject.command;

import com.ztp.ztpproject.models.Note;

/**
 * Class representing a command to change the name of a note.
 *
 * This class extends the AbstractCommand and overrides the execute and undo
 * methods to provide the necessary functionality.
 *
 * When the execute method is called, it changes the name of the note to the new
 * name provided in the constructor. The old name is saved in a field so that it
 * can be restored when the undo method is called.
 *
 * When the undo method is called, it restores the old name of the note.
 *
 * @version 1.0
 */
public class ChangeNameCommand extends AbstractCommand<Note> {

    private String newName;
    private String oldName;

    public ChangeNameCommand(String newName) {
        this.newName = newName;
    }

    /**
     * Executes the command to change the name of the note.
     *
     * This method retrieves the current note from the caretaker, saves its
     * current name to the oldName field, and then updates the note's name to
     * the new name provided in the constructor.
     */

    @Override
    public void execute() {
        Note temp = super.caretaker.getOriginator();
        oldName = temp.getName();
        temp.setName(newName);
    }

    /**
     * Restores the name of the note to its previous state.
     *
     * This method retrieves the current note from the caretaker and sets its
     * name back to the old name that was saved before the execute method was
     * called.
     */
    @Override
    public void undo() {
        super.caretaker.getOriginator().setName(oldName);
    }

}
