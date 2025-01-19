package com.ztp.ztpproject.command;

import com.ztp.ztpproject.flyweight.TagFactory;
import com.ztp.ztpproject.models.Note;

/**
 * The AddTagCommand class represents a command to add a new tag to a note.
 *
 * This class extends the AbstractCommand class, specifically for handling
 * operations related to adding tags to notes. It utilizes the TagFactory to
 * create and manage Tag instances, ensuring that tags with the same state are
 * shared across different notes.
 *
 * The execute method adds a new tag to the note using the TagFactory, and the
 * undo method removes the previously added tag. The command pattern allows for
 * encapsulating these operations, making it easier to manage complex sequences
 * of actions.
 *
 * @version 1.0
 */
public class AddTagCommand extends AbstractCommand<Note> {

    private TagFactory tagFactory;
    private String newTag;

    public AddTagCommand(TagFactory tagFactory, String newTag) {
        this.tagFactory = tagFactory;
        this.newTag = newTag;
    }

    /**
     * Executes the command, adding a new tag to the note.
     *
     * @implNote This method uses the TagFactory to create a new Tag object and
     * adds it to the note.
     */
    @Override
    public void execute() {
        Note temp = super.caretaker.getOriginator();
        temp.addTag(tagFactory.getState(newTag));
    }

    /**
     * Undoes the command by removing the previously added tag from the note.
     *
     * @implNote This method uses the TagFactory to retrieve the Tag object
     * associated with the newTag and removes it from the note.
     */
    @Override
    public void undo() {
        super.caretaker.getOriginator().removeTag(tagFactory.getState(newTag));
    }

}
