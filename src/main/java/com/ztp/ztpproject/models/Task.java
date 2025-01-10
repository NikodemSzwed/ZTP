package com.ztp.ztpproject.models;
import java.util.Date;

public class Task {
    private String name;
    private String content;
    private boolean isDone;
    private int priority;
    private Date deadline;
    // do dodania lista kategorii

    public Task(String name, String content, boolean isDone, int priority, Date deadline) {
        this.name = name;
        this.content = content;
        this.isDone = isDone;
        this.priority = priority;
        this.deadline = deadline;
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

    public void showDetails() {
        System.out.println("Task: " + name);
        System.out.println("Content: " + content);
        System.out.println("Completed: " + (isDone ? "Yes" : "No"));
        System.out.println("Priority: " + priority);
        System.out.println("Deadline: " + deadline);
    }
}