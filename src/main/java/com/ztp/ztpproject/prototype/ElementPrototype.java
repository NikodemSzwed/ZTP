package com.ztp.ztpproject.prototype;

/**
 * Abstract class representing a prototype of an element that can be cloned.
 * This class provides a common interface for cloning, setting and retrieving
 * the name and content of an element.
 *
 * This class is used as a base class for concrete prototype classes that
 * represent different types of elements.
 *
 * @version 1.0
 */
public abstract class ElementPrototype {

    protected String name;
    protected String content;

    public ElementPrototype(String name, String content) {
        this.name = name;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public abstract void showDetails();

    public abstract ElementPrototype clone();
}
