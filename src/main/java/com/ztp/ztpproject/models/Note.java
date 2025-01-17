package com.ztp.ztpproject.models;

import com.ztp.ztpproject.flyweight.Tag;
import com.ztp.ztpproject.prototype.ElementPrototype;
import java.util.List;

/**
 * The Note class represents a note with a name, content, and associated tags.
 * It extends the ElementPrototype class, allowing it to be cloned. This class
 * provides functionality to clone itself and display its details.
 *
 * Instances of this class can be created using a list of tags, and can be
 * cloned to create a new note with the same properties. It also provides a
 * method to display the details of the note including its name, content, and
 * tags.
 *
 *
 * @version 1.0
 */
public class Note extends ElementPrototype {

    private List<Tag> tags;

    public Note(String name, String content, List<Tag> tags) {
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
