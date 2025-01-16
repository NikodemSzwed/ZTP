package com.ztp.ztpproject.models;
import com.ztp.ztpproject.prototype.ElementPrototype;
import com.ztp.ztpproject.flyweight.*;
import java.util.Date;
import java.util.List;

public class Task extends ElementPrototype {
    private boolean isDone;
    private int priority;
    private Date deadline;
    private List<Category> categories;

    public Task(String name, String content, boolean isDone, int priority, Date deadline, List<Category> categories) {
        super(name, content);
        this.isDone = isDone;
        this.priority = priority;
        this.deadline = deadline;
        this.categories = categories;
    }

    public Task(Task copy){
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
    

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
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
}
