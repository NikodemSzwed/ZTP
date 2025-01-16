// Placeholder for Template.java

package com.ztp.ztpproject.prototype;

public class Template {
    private final ElementPrototype prototype;

    public Template(ElementPrototype prototype) {
        this.prototype = prototype;
    }

    public ElementPrototype CloneDefaultPrototype() {
        return prototype.clone();
    }

    public ElementPrototype CloneCustomPrototype(String name, String content) {
        ElementPrototype elementPrototype = prototype.clone();
        elementPrototype.setName(name);
        elementPrototype.setContent(content);
        return elementPrototype;
    }
}