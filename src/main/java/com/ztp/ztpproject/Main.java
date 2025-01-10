package com.ztp.ztpproject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.ztp.ztpproject.builder.RaportDirector;
import com.ztp.ztpproject.models.Task;
import java.util.Calendar;

public class Main {
    public static void main(String[] args) {
        // System.out.println("Hello, Java!");

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

        RaportDirector director = new RaportDirector("raport.txt");
        calendar.set(2025, Calendar.JANUARY, 8);
        date = calendar.getTime();
        calendar.set(2025, Calendar.JANUARY, 15);
        Date date2 = calendar.getTime();
        director.generateRaportTxt(tasks, date, date2);
    }
}
