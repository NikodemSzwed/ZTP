package com.ztp.ztpproject.models;
import com.ztp.ztpproject.prototype.ElementPrototype;
import java.util.List;

public class Note extends ElementPrototype{

    private List<String> tags;

    public Note(String name, String content, List<String> tags) {
        super(name, content);
        this.tags = tags;
    }

    public Note(Note copy) {
        super(copy.name, copy.content);
        this.tags = copy.tags != null ? List.copyOf(copy.tags) : null;
    }
    @Override
    public void showDetails() {
        System.out.println("Nazwa notatki: " + name);
        System.out.println("Treść: " + content);
        System.out.println("Tagi: " + tags);
    }

    @Override
    public ElementPrototype clone() {
        return new Note(this);
    }
}