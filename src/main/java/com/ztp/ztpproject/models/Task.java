package com.ztp.ztpproject.models;

import com.ztp.ztpproject.flyweight.*;
import com.ztp.ztpproject.prototype.ElementPrototype;
import java.util.Date;
import java.util.List;

/**
 * The Task class represents a task with a name, content, completion status,
 * priority, deadline, and associated categories. It extends the
 * ElementPrototype class, allowing it to be cloned.
 *
 * This class provides functionality to clone itself, retrieve its name, and
 * display its details. Instances of this class can be created with specified
 * attributes such as name, content, priority, deadline, and categories.
 *
 * The class also provides a constructor to create a copy of an existing Task.
 *
 * @version 1.0
 */
public class Task extends ElementPrototype {

    private boolean isDone;
    private int priority;
    private Date deadline;
    private List<Category> categories;

    public Task(String name, String content, boolean isDone, int priority, Date deadline, List<Category> categories) {
        super(name, content);
        this.isDone = isDone;
        this.priority = priority<0?0:priority;
        this.deadline = deadline;
        this.categories = categories;
    }

    public Task(Task copy) {
        super(copy.name, copy.content);
        this.isDone = copy.isDone;
        this.priority = copy.priority;
        this.deadline = copy.deadline;
        this.categories = copy.categories != null ? List.copyOf(copy.categories) : null;
    }

    @Override
    public ElementPrototype clone() {
        return new Task(this);
    }

    public boolean isDone() {
        return isDone;
    }

    public int getPriority() {
        return priority;
    }

    public Date getDeadline() {
        return deadline;
    }

    public List<Category> getCategories() {
        return categories;
    }

    @Override
    public void showDetails() {
        System.out.println("Nazwa zadania: " + name);
        System.out.println("Treść: " + content);
        System.out.println("Ukończone: " + (isDone ? "Tak" : "Nie"));
        System.out.println("Priorytet: " + priority);
        System.out.println("Deadline: " + deadline);
        System.out.println("Kategorie: " + categories);
    }

    @Override
    public String toString() {
        return "Task{"
                + "name='" + name + '\''
                + ", content='" + content + '\''
                + ", isDone=" + isDone
                + ", priority=" + priority
                + ", deadline=" + deadline
                + ", categories=" + categories
                + '}';
    }
}
