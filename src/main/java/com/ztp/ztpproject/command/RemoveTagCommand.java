package com.ztp.ztpproject.command;

import com.ztp.ztpproject.flyweight.TagFactory;

/**
 * A command to remove a tag from a note.
 *
 * This class extends the {@link AddTagCommand} and overrides the execute and
 * undo methods to provide the necessary functionality. The execute method
 * removes the tag from the note and the undo method re-adds the tag.
 *
 * @version 1.0
 */
public class RemoveTagCommand extends AddTagCommand {

    public RemoveTagCommand(TagFactory tagFactory, String tagToRemove) {
        super(tagFactory, tagToRemove);
    }

    /**
     * Executes the command by removing the specified tag from the note.
     *
     * @implNote This method undoes the addition of the tag, effectively
     * removing it from the note.
     */
    @Override
    public void execute() {
        super.undo();
    }

    /**
     * Re-executes the addition of the tag that was removed by the execute
     * method, effectively restoring the tag to the note.
     */
    @Override
    public void undo() {
        super.execute();
    }

}
