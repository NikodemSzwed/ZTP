package com.ztp.ztpproject.command;
import com.ztp.ztpproject.models.Note;

public class ChangeNameCommand extends AbstractCommand<Note> {
    private String newName;
    private String oldName;

    public ChangeNameCommand(String newName) {
        this.newName = newName;
    }
    @Override
    public void execute() {
        Note temp = super.caretaker.getOriginator();
        oldName = temp.getName();
        temp.setName(newName);
    }

    @Override
    public void undo() {
        super.caretaker.getOriginator().setName(oldName);
    }
    
}
