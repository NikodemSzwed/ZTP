package com.ztp.ztpproject;

import com.ztp.ztpproject.flyweight.*;
import java.util.Calendar;
import java.util.Date;

public class Main {

    public static void main(String[] args) {
        // Poniżej kod do przetestowania budowania raportów
        // Zostawiam jakby ktoś sobie chciał zobaczyć
        Calendar calendar = Calendar.getInstance();
        calendar.set(2025, Calendar.JANUARY, 10);
        Date date = calendar.getTime();

        /*
        Task task1 = new Task("Zadanie 1", "Sprzedaj 10 Iqosów", true, 1, date);
        calendar.set(2025, Calendar.JANUARY, 12);
        date = calendar.getTime();
        Task task2 = new Task("Zadanie 2", "Zjedz kebaba", false, 2, date);
        calendar.set(2025, Calendar.JANUARY, 16);
        date = calendar.getTime();
        Task task3 = new Task("Zadanie 3", "Kup bułki", false, 3, date);
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
        CategoryFactory categoryFactory = new CategoryFactory();
        Category workCategory1 = categoryFactory.getState("Work");
        Category workCategory2 = categoryFactory.getState("Work");
        System.out.println("Czy Kategoria 'Work' jest współdzielona: " + (workCategory1 == workCategory2)); // true
        System.out.println("Nieistniejąca Kategoria: " + categoryFactory.getStateDontAdd("Nonexistent")); // null
        System.out.println("Wszystkie Kategorie: " + categoryFactory.getAllStates());
    }
}
