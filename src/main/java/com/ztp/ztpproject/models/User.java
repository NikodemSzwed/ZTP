package com.ztp.ztpproject.models;

import com.ztp.ztpproject.flyweight.*;
import com.ztp.ztpproject.memento.NoteCaretaker;
import com.ztp.ztpproject.prototype.Template;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * Class representing a user in the system.
 *
 * A user has a name, role, set of task templates, set of note templates, list
 * of notes, list of tasks, set of tags, and a category factory proxy.
 *
 * This class provides methods to add tasks and notes to the user's lists, set
 * the user's role, and retrieve the user's tags, task templates, and note
 * templates.
 *
 * @version 1.0
 */
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
     * Adds a new task to the user's task list with the specified name, content,
     * priority, and deadline. The task is associated with categories that
     * correspond to the provided category keys. If a category cannot be
     * retrieved due to insufficient permissions, the task is not added, and a
     * message is printed indicating the lack of privileges.
     *
     * @param name the name of the task
     * @param content the content or description of the task
     * @param priority the priority level of the task
     * @param deadline the deadline for task completion
     * @param categoriesKeys a list of keys representing the categories to be
     * associated with the task
     */
    public void addTask(String name, String content, int priority, Date deadline, List<String> categoriesKeys) {
        List<Category> categories = new ArrayList<>();
        for (String key : categoriesKeys) {
            Category c = categoryFactoryProxy.getState(key);
            if (c == null) {
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
     * from the provided template with the specified name and content. The
     * cloned task is then added to the user's task list.
     *
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
     * with the specified name and content. The cloned task is then added to the
     * user's task list.
     *
     * @param templateIndex the index of the template in the task template list
     * @param name the name of the new task
     * @param content the content or description of the new task
     */
    public void addTaskFromTemplate(int templateIndex, String name, String content) {
        if (taskTemplateList.isEmpty() || templateIndex >= taskTemplateList.size() || templateIndex < 0) {
            System.out.println("Nie ma takiego szablonu.");
            return;
        }
        addTaskFromTemplate(taskTemplateList.get(templateIndex), name, content);
    }

    /**
     * Adds a new note to the user's notes list with the specified name,
     * content, and tags. The tags are retrieved using the provided keys.
     *
     * @param name the name of the note
     * @param content the content or description of the note
     * @param tagsKeys a list of keys representing the tags to be associated
     * with the note
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
     * from the provided template with the specified name and content. The
     * cloned note is then added to the user's notes list.
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
     * with the specified name and content. The cloned note is then added to the
     * user's notes list. If the template index is invalid, a message is printed
     * indicating the absence of the template and the operation is aborted.
     *
     * @param templateIndex the index of the template in the note template list
     * @param name the name of the new note
     * @param content the content or description of the new note
     */
    public void addNoteFromTemplate(int templateIndex, String name, String content) {
        if (noteTemplateList.isEmpty() || templateIndex >= noteTemplateList.size() || templateIndex < 0) {
            System.out.println("Nie ma takiego szablonu.");
            return;
        }
        addNoteFromTemplate(noteTemplateList.get(templateIndex), name, content);
    }

    /**
     * Saves the provided object as a template. If the object is a Task, it is
     * added to the user's task template list. If the object is a Note, it is
     * added to the user's note template list.
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

    public List<Task> getFilteredTasks(String nameLike, String contentLike) {
        return getFilteredTasks(nameLike, contentLike, -1, null, null, null);
    }

    public List<Task> getFilteredTasks(List<String> categoriesKeys) {
        return getFilteredTasks(null, null, -1, null, null, categoriesKeys);
    }

    public List<Task> getFilteredTasks(Date deadlineFrom, Date deadlineTo) {
        return getFilteredTasks(null, null, -1, deadlineFrom, deadlineTo, null);
    }

    public List<Task> getFilteredTasks(int priority) {
        return getFilteredTasks(null, null, priority, null, null, null);
    }

    /**
     * Filters and retrieves tasks from the user's task list based on the
     * specified criteria including name, content, priority, deadline range, and
     * categories.
     *
     * If any of the filter parameters is null or set to the default, it is
     * ignored in the filtering process. The filtered tasks are sorted by
     * priority if a specific priority is set, otherwise by deadline if a
     * deadline range is specified.
     *
     * @param nameLike the substring to match in the task's name (null to
     * ignore)
     * @param contentLike the substring to match in the task's content (null to
     * ignore)
     * @param priority the priority level to filter by (-1 to ignore)
     * @param deadlineFrom the start date for the deadline range filter (null to
     * ignore)
     * @param deadlineTo the end date for the deadline range filter (null to
     * ignore)
     * @param categoriesKeys the list of category keys to filter by (null to
     * ignore)
     * @return a list of tasks that match the filtering criteria
     */
    public List<Task> getFilteredTasks(String nameLike, String contentLike, int priority, Date deadlineFrom, Date deadlineTo, List<String> categoriesKeys) {
        List<Task> filteredTasks = new ArrayList<>();
        for (Task task : taskList) {
            if ((nameLike == null || task.getName().contains(nameLike))
                    && (contentLike == null || task.getContent().contains(contentLike))
                    && (priority == -1 || task.getPriority() == priority)
                    && (deadlineFrom == null || (task.getDeadline().after(deadlineFrom) || task.getDeadline().equals(deadlineFrom)))
                    && (deadlineTo == null || (task.getDeadline().before(deadlineTo) || task.getDeadline().equals(deadlineTo)))
                    && (categoriesKeys == null || task.getCategories().stream().anyMatch(c -> categoriesKeys.contains(c.getRepeatingState())))) {
                filteredTasks.add(task);
            }
        }
        if (priority != -1) {
            filteredTasks.sort(Comparator.comparing(Task::getPriority));
        } else if (deadlineTo != null) {
            filteredTasks.sort(Comparator.comparing(Task::getDeadline));
        }
        return filteredTasks;
    }

    public List<Note.ReadOnlyNote> getFilteredNotes(String nameLike, String contentLike) {
        return getFilteredNotes(nameLike, contentLike, null);
    }

    public List<Note.ReadOnlyNote> getFilteredNotes(List<String> tagsKeys) {
        return getFilteredNotes(null, null, tagsKeys);
    }

    /**
     * Filters notes in the user's notes list to only include notes whose name,
     * content, and tags match the specified parameters.
     *
     * @param nameLike the name of the note to filter by
     * @param contentLike the content of the note to filter by
     * @param tagsKeys the tags to filter by
     * @return a list of filtered notes
     */
    public List<Note.ReadOnlyNote> getFilteredNotes(String nameLike, String contentLike, List<String> tagsKeys) {
        List<Note.ReadOnlyNote> filteredNotes = new ArrayList<>();
        for (NoteCaretaker noteCaretaker : notesList) {
            Note.ReadOnlyNote note = noteCaretaker.getReadOnlyOriginator();
            if (nameLike == null || (note.getName().contains(nameLike))
                    && (contentLike == null || note.getContent().contains(contentLike))
                    && (tagsKeys == null || note.getTags().stream().anyMatch(t -> tagsKeys.contains(t.getRepeatingState())))) {
                filteredNotes.add(note);
            }
        }
        return filteredNotes;
    }

    public TagFactory getTagFactory() {
        return myTags;
    }
}
