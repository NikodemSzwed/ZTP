package com.ztp.ztpproject.prototype;

/**
 * The Template class serves as a blueprint for creating instances of elements
 * derived from the ElementPrototype. It encapsulates a prototype instance and
 * provides methods to clone this prototype, either using its default state or
 * by customizing its name and content.
 *
 * This class is useful in scenarios where you need to create copies of a given
 * prototype while optionally modifying some of its properties.
 *
 * @version 1.0
 */
public class Template {

    private final ElementPrototype prototype;

    public Template(ElementPrototype prototype) {
        this.prototype = prototype;
    }

    /**
     * Clones the default prototype instance, returning a new instance with the
     * same state as the default prototype.
     *
     * @return a clone of the default prototype instance
     */
    public ElementPrototype CloneDefaultPrototype() {
        return prototype.clone();
    }

    /**
     * Clones the default prototype instance, customizing its name and content
     * properties before returning the new instance. This method is useful when
     * you need to create a modified clone of the prototype instance.
     *
     * @param name the name to set for the cloned prototype
     * @param content the content to set for the cloned prototype
     * @return a clone of the default prototype instance, with the specified
     * name and content
     */
    public ElementPrototype CloneCustomPrototype(String name, String content) {
        ElementPrototype elementPrototype = prototype.clone();
        elementPrototype.setName(name);
        elementPrototype.setContent(content);
        return elementPrototype;
    }
}
