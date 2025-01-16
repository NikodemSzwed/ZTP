package com.ztp.ztpproject;

import com.ztp.ztpproject.builder.RaportDirector;
import com.ztp.ztpproject.flyweight.*;
import com.ztp.ztpproject.models.Task;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // Poniżej kod do przetestowania budowania raportów
        // Zostawiam jakby ktoś sobie chciał zobaczyć
        Calendar calendar = Calendar.getInstance();
        calendar.set(2025, Calendar.JANUARY, 10);
        Date date = calendar.getTime();

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

        // Testy dla Flyweight (Tag i Category)
        System.out.println("\n=== Test Flyweight ===");

        // Test TagFactory
        TagFactory tagFactory = new TagFactory();
        Tag urgentTag1 = tagFactory.getTag("Urgent");
        Tag urgentTag2 = tagFactory.getTag("Urgent");
        System.out.println("Czy Tag 'Urgent' jest współdzielony: " + (urgentTag1 == urgentTag2)); // true
        System.out.println("Nieistniejący Tag: " + tagFactory.getTagDontAdd("Optional")); // null

        // Test CategoryFactory
        CategoryFactory categoryFactory = new CategoryFactory();
        Category workCategory1 = categoryFactory.getCategory("Work");
        Category workCategory2 = categoryFactory.getCategory("Work");
        System.out.println("Czy Kategoria 'Work' jest współdzielona: " + (workCategory1 == workCategory2)); // true
        System.out.println("Nieistniejąca Kategoria: " + categoryFactory.getCategoryDontAdd("Nonexistent")); // null
    }
}
