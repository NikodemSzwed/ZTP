package com.ztp.ztpproject.prototype;

public abstract class ElementPrototype {
    protected String name;
    protected String content;

    public ElementPrototype(String name, String content) {
        this.name = name;
        this.content = content;
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