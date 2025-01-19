package com.ztp.ztpproject.command;

import com.ztp.ztpproject.models.Note;

/**
 * Class representing a command that changes the content of a note.
 *
 * This class extends the {@link AbstractCommand} class and implements the
 * {@link ICommand} interface. The execute method sets the content of the note
 * to the specified string and the undo method sets the content back to its
 * previous state.
 *
 */
public class ChangeContentCommand extends AbstractCommand<Note> {

    private String newContent;
    private String oldContent;

    public ChangeContentCommand(String newContent) {
        this.newContent = newContent;
    }

    /**
     * Sets the content of the note to the specified string.
     *
     * This method first retrieves the note from the caretaker, then saves its
     * current content in the oldContent field, and finally sets the content of
     * the note to the specified string.
     */
    @Override
    public void execute() {
        Note temp = super.caretaker.getOriginator();
        oldContent = temp.getContent();
        temp.setContent(newContent);
    }

    /**
     * Reverts the change in the content of the note to the previous state.
     *
     * This method retrieves the note from the caretaker and sets its content to
     * the content previously saved in the oldContent field.
     */
    @Override
    public void undo() {
        super.caretaker.getOriginator().setContent(oldContent);
    }

}
