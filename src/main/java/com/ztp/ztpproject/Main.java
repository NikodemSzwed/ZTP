package com.ztp.ztpproject;
import com.ztp.ztpproject.builder.RaportDirector;
import com.ztp.ztpproject.models.Note;
import com.ztp.ztpproject.models.Task;
import com.ztp.ztpproject.prototype.Template;

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
        Task customTask = (Task) taskTemplate.createElementPrototype("Custom task", "Custom task content");
        customTask.showDetails();

        Template noteTemplate = new Template(notePrototype);
        Note customNote = (Note) noteTemplate.createElementPrototype("Custom note", "Custom note content");
        customNote.showDetails();
    }
}
