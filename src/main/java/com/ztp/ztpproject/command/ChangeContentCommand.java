package com.ztp.ztpproject.command;
import com.ztp.ztpproject.models.Note;

public class ChangeContentCommand extends AbstractCommand<Note> {
    private String newContent;
    private String oldContent;

    public ChangeContentCommand(String newContent) {
        this.newContent = newContent;
    }
    @Override
    public void execute() {
        Note temp = super.caretaker.getOriginator();
        oldContent = temp.getContent();
        temp.setContent(newContent);
    }

    @Override
    public void undo() {
        super.caretaker.getOriginator().setContent(oldContent);
    }
    
}