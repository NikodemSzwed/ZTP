package com.ztp.ztpproject.models;

import com.ztp.ztpproject.flyweight.*;
import java.util.Date;

public class Task {

    private String name;
    private String content;
    private boolean isDone;
    private int priority;
    private Date deadline;
    private Category[] categories;
    // do dodania lista kategorii

    public Task(String name, String content, boolean isDone, int priority, Date deadline, Category[] categories) {
        this.name = name;
        this.content = content;
        this.isDone = isDone;
        this.priority = priority;
        this.deadline = deadline;
        this.categories = categories;
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

    public Category[] getCategories() {
        return categories;
    }

    public void showDetails() {
        System.out.println("Nazwa zadania: " + name);
        System.out.println("Treść: " + content);
        System.out.println("Ukończone: " + (isDone ? "Tak" : "Nie"));
        System.out.println("Priorytet: " + priority);
        System.out.println("Deadline: " + deadline);
        System.out.println("Kategorie: " + categories);
    }
}
