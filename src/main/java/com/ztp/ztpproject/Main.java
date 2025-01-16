package com.ztp.ztpproject;
import com.ztp.ztpproject.builder.RaportDirector;
import com.ztp.ztpproject.models.Note;
import com.ztp.ztpproject.models.Task;
import com.ztp.ztpproject.prototype.Template;
import com.ztp.ztpproject.flyweight.*;

import java.util.Calendar;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // Poniżej kod do przetestowania budowania raportów
        // Zostawiam jakby ktoś sobie chciał zobaczyć
        Calendar calendar = Calendar.getInstance();
        calendar.set(2025, Calendar.JANUARY, 10);
        Date date = calendar.getTime();

        /*
        Task task1 = new Task("Zadanie 1", "Sprzedaj 10 Iqosów", true, 1, date, null);
        calendar.set(2025, Calendar.JANUARY, 12);
        date = calendar.getTime();
        Task task2 = new Task("Zadanie 2", "Zjedz kebaba", false, 2, date, null);
        calendar.set(2025, Calendar.JANUARY, 16);
        date = calendar.getTime();
        Task task3 = new Task("Zadanie 3", "Kup bułki", false, 3, date, null);
        List<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);
        

        RaportDirector director = new RaportDirector("raport");
        calendar.set(2025, Calendar.JANUARY, 8);
        date = calendar.getTime();
        calendar.set(2025, Calendar.JANUARY, 15);
        Date date2 = calendar.getTime();
        director.generateRaportTxt(tasks, date, date2);
        director.generateRaportPdf(tasks, date, date2);

        Task taskPrototype = new Task("Prototype task", "TestPrototype", false, 4, date, Arrays.asList("Test", "Test2", "Test3"));
        Note notePrototype = new Note("Prototype note", "TestNote", Arrays.asList("TestWażne"));

        Template taskTemplate = new Template(taskPrototype);
        Task customTask = (Task) taskTemplate.CloneCustomPrototype("Custom task", "Custom task content");
        customTask.showDetails();

        Template noteTemplate = new Template(notePrototype);
        Note customNote = (Note) noteTemplate.CloneCustomPrototype("Custom note", "Custom note content");
        customNote.showDetails();

        Task defaultTask = (Task) taskTemplate.CloneDefaultPrototype();
        defaultTask.showDetails();

        Note defaultNote = (Note) noteTemplate.CloneDefaultPrototype();
        defaultNote.showDetails();

         */
        // Testy dla Flyweight (Tag i Category)
        System.out.println("\n=== Test Flyweight ===");

        // Test TagFactory
        TagFactory tagFactory = new TagFactory();
        Tag urgentTag1 = tagFactory.getState("Urgent");
        Tag urgentTag2 = tagFactory.getState("Urgent");
        System.out.println("Czy Tag 'Urgent' jest współdzielony: " + (urgentTag1 == urgentTag2)); // true
        System.out.println("Nieistniejący Tag: " + tagFactory.getStateDontAdd("Optional")); // null
        System.out.println("Wszystkie tagi: " + tagFactory.getAllStates());

        // Test CategoryFactory
        CategoryFactory categoryFactory = CategoryFactory.getInstance();
        Category workCategory1 = categoryFactory.getState("Work");
        Category workCategory2 = categoryFactory.getState("Work");
        System.out.println("Czy Kategoria 'Work' jest współdzielona: " + (workCategory1 == workCategory2)); // true
        System.out.println("Nieistniejąca Kategoria: " + categoryFactory.getStateDontAdd("Nonexistent")); // null
        System.out.println("Wszystkie Kategorie: " + categoryFactory.getAllStates());
    }
}
