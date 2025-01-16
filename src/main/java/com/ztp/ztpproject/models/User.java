package com.ztp.ztpproject.models;
import com.ztp.ztpproject.prototype.Template;
import com.ztp.ztpproject.flyweight.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class User {
    private RoleList role;
    private List<Template> taskTemplateList;
    private List<Template> noteTemplateList;
    private List<Note> notesList;
    private List<Task> taskList;
    private TagFactory myTags;

    public User(RoleList role) {
        this.role = role;
        this.taskTemplateList = new ArrayList<>();
        this.noteTemplateList = new ArrayList<>();
        this.notesList = new ArrayList<>();
        this.taskList = new ArrayList<>();
        this.myTags = new TagFactory();
    }

    public void addTask(String name, String content, int priority, Date deadline, List<String> categoriesKeys) {
        List<Category> categories = new ArrayList<>();
        for (String key : categoriesKeys) {
            categories.add(CategoryFactory.getInstance().getState(key));
        }
        Task task = new Task(name, content, false, priority, deadline, categories);
        taskList.add(task);
    }

    public void addTaskFromTemplate(Template template, String name, String content) {
        Task task = (Task) template.CloneCustomPrototype(name, content);
        taskList.add(task);
    }

    public void addNote(String name, String content, List<String> tagsKeys) {
        List<Tag> tags = new ArrayList<>();
        for (String key : tagsKeys) {
            tags.add(myTags.getState(key));
        }
        Note note = new Note(name, content, tags);
        notesList.add(note);
    }

    public void addNoteFromTemplate(Template template, String name, String content) {
        Note note = (Note) template.CloneCustomPrototype(name, content);
        notesList.add(note);
    }

    // Getters and Setters
    public RoleList getRole() {
        return role;
    }

    public void setRole(RoleList role) {
        this.role = role;
    }

    public List<Template> getTaskTemplateList() {
        return taskTemplateList;
    }

    public List<Template> getNoteTemplateList() {
        return noteTemplateList;
    }

    public List<Note> getNotesList() {
        return notesList;
    }

    public List<Task> getTaskList() {
        return taskList;
    }
}