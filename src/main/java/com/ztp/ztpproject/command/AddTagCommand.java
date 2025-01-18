package com.ztp.ztpproject.command;
import com.ztp.ztpproject.models.Note;
import com.ztp.ztpproject.flyweight.TagFactory;

public class AddTagCommand extends AbstractCommand<Note> {
    private TagFactory tagFactory;
    private String newTag;

    public AddTagCommand(TagFactory tagFactory,String newTag) {
        this.tagFactory = tagFactory;
        this.newTag = newTag;
    }
    
    /**
     * Executes the command, adding a new tag to the note.
     *
     * @implNote This method uses the TagFactory to create a new Tag object and adds it to the note.
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