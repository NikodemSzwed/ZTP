package com.ztp.ztpproject.models;
import com.ztp.ztpproject.prototype.Template;
import com.ztp.ztpproject.flyweight.*;
import com.ztp.ztpproject.memento.NoteCaretaker;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class User {
    private String name;
    private RoleList role;
    private List<Template> taskTemplateList;
    private List<Template> noteTemplateList;
    private List<NoteCaretaker> notesList;
    private List<Task> taskList;
    private TagFactory myTags;
    private CategoryFactoryProxy categoryFactoryProxy;

    public User(String name, RoleList role) {
        this.name = name;
        this.role = role;
        this.taskTemplateList = new ArrayList<>();
        this.noteTemplateList = new ArrayList<>();
        this.notesList = new ArrayList<>();
        this.taskList = new ArrayList<>();
        this.myTags = new TagFactory();
        this.categoryFactoryProxy = new CategoryFactoryProxy(this);
    }

    /**
     * Adds a new task to the user's task list with the specified name, content, priority, and deadline.
     * The task is associated with categories that correspond to the provided category keys.
     * If a category cannot be retrieved due to insufficient permissions, the task is not added, 
     * and a message is printed indicating the lack of privileges.
     *
     * @param name the name of the task
     * @param content the content or description of the task
     * @param priority the priority level of the task
     * @param deadline the deadline for task completion
     */
    public void addTask(String name, String content, int priority, Date deadline, List<String> categoriesKeys) {
        List<Category> categories = new ArrayList<>();
        for (String key : categoriesKeys) {
            Category c = categoryFactoryProxy.getState(key);
            if(c==null){
                System.out.println("Nie masz uprawnień do dodania kategorii.");
                return;
            }
            categories.add(c);
        }
        Task task = new Task(name, content, false, priority, deadline, categories);
        taskList.add(task);
    }

    /**
     * Adds a new task to the user's task list by cloning a custom prototype
     * from the provided template with the specified name and content.
     * The cloned task is then added to the user's task list.
     * @param template the template to clone
     * @param name the name of the new task
     * @param content the content or description of the new task
     */
    public void addTaskFromTemplate(Template template, String name, String content) {
        Task task = (Task) template.CloneCustomPrototype(name, content);
        taskList.add(task);
    }

    /**
     * Adds a new task to the user's task list by cloning a custom prototype
     * from a template in the user's task template list at the specified index
     * with the specified name and content.
     * The cloned task is then added to the user's task list.
     * @param templateIndex the index of the template in the task template list
     * @param name the name of the new task
     * @param content the content or description of the new task
     */
    public void addTaskFromTemplate(int templateIndex, String name, String content) {
        if(taskTemplateList.isEmpty() || templateIndex >= taskTemplateList.size() || templateIndex < 0){
            System.out.println("Nie ma takiego szablonu.");
            return;
        }
        addTaskFromTemplate(taskTemplateList.get(templateIndex), name, content);
    }

    /**
     * Adds a new note to the user's notes list with the specified name, content, 
     * and tags. The tags are retrieved using the provided keys.
     *
     * @param name the name of the note
     * @param content the content or description of the note
     * @param tagsKeys a list of keys representing the tags to be associated with the note
     */
    public void addNote(String name, String content, List<String> tagsKeys) {
        List<Tag> tags = new ArrayList<>();
        for (String key : tagsKeys) {
            tags.add(myTags.getState(key));
        }
        NoteCaretaker noteCt = new NoteCaretaker(new Note(name, content, tags));
        notesList.add(noteCt);
    }

    /**
     * Adds a new note to the user's notes list by cloning a custom prototype
     * from the provided template with the specified name and content.
     * The cloned note is then added to the user's notes list.
     * 
     * @param template the template to clone
     * @param name the name of the new note
     * @param content the content or description of the new note
     */
    public void addNoteFromTemplate(Template template, String name, String content) {
        Note note = (Note) template.CloneCustomPrototype(name, content);
        notesList.add(new NoteCaretaker(note));
    }

    /**
     * Adds a new note to the user's notes list by cloning a custom prototype
     * from a template in the user's note template list at the specified index
     * with the specified name and content.
     * The cloned note is then added to the user's notes list.
     * If the template index is invalid, a message is printed indicating the absence
     * of the template and the operation is aborted.
     *
     * @param templateIndex the index of the template in the note template list
     * @param name the name of the new note
     * @param content the content or description of the new note
     */
    public void addNoteFromTemplate(int templateIndex, String name, String content) {
        if(noteTemplateList.isEmpty() || templateIndex >= noteTemplateList.size() || templateIndex < 0){
            System.out.println("Nie ma takiego szablonu.");
            return;
        }
        addNoteFromTemplate(noteTemplateList.get(templateIndex), name, content);
    }

    /**
     * Saves the provided object as a template. If the object is a Task, it is added
     * to the user's task template list. If the object is a Note, it is added to the
     * user's note template list.
     * 
     * @param o the object to be saved as a template
     */
    public void saveAsTemplate(Object o) {
        if (o instanceof Task) {
            taskTemplateList.add(new Template((Task) o));
        } else if (o instanceof Note) {
            noteTemplateList.add(new Template((Note) o));
        }
    }

    // Getters and Setters
    public RoleList getRole() {
        return role;
    }

    public void setRole(RoleList role) {
        this.role = role;
        this.categoryFactoryProxy = new CategoryFactoryProxy(this);
    }

    public String getName() {
        return name;
    }

    public List<Template> getTaskTemplateList() {
        return taskTemplateList;
    }

    public List<Template> getNoteTemplateList() {
        return noteTemplateList;
    }

    public List<NoteCaretaker> getNotesList() {
        return notesList;
    }

    public NoteCaretaker getNoteCareTaker(int index) {
        return notesList.get(index);
    }
    
    public Note.ReadOnlyNote getNote(int index) {
        return notesList.get(index).getReadOnlyOriginator();
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public Task getTask(int index) {
        return taskList.get(index);
    }

    public TagFactory getTagFactory() {
        return myTags;
    }
}