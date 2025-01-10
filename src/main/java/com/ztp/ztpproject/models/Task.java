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

    public String GetName() {
        return name;
    }

    public String GetContent() {
        return content;
    }

    public boolean IsDone() {
        return isDone;
    }

    public int GetPriority() {
        return priority;
    }

    public Date GetDeadline() {
        return deadline;
    }

    public void ShowDetails() {
        System.out.println("Task: " + name);
        System.out.println("Content: " + content);
        System.out.println("Completed: " + (isDone ? "Yes" : "No"));
        System.out.println("Priority: " + priority);
        System.out.println("Deadline: " + deadline);
    }
}