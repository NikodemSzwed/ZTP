package com.ztp.ztpproject.builder;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import com.ztp.ztpproject.models.Task;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;

public class TxtBuilder implements IBuilder {
    private BufferedWriter writer;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    private String exportPath;

    public void initRaport(String exportPath) {
        try {
            this.exportPath = exportPath;
            writer = new BufferedWriter(new FileWriter(exportPath, false));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addTitle(Date timeFrom, Date timeTo) {
        try {
            String formattedTimeFrom = dateFormat.format(timeFrom);
            String formattedTimeTo = dateFormat.format(timeTo);

            writer.write("+" + new String(new char[42]).replace("\0", "-") + "+");
            writer.newLine();
            writer.write("| Raport zadań od " + formattedTimeFrom + " do " + formattedTimeTo + " |");
            writer.newLine();
            writer.write("+" + new String(new char[42]).replace("\0", "-") + "+");
            writer.newLine();
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addTask(Task task) {
        try {
            writer.write("Zadanie: " + task.getName());
            writer.newLine();
            writer.write("Ukończone: " + (task.isDone() ? "Tak" : "Nie"));
            writer.newLine();
            writer.write("Priorytet: " + task.getPriority());
            writer.newLine();
            String formattedDeadline = dateFormat.format(task.getDeadline());
            writer.write("Deadline: " + formattedDeadline);
            writer.newLine();
            writer.write(new String(new char[30]).replace("\0", "-"));
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addSummary(List<Task> tasks) {
        int doneTasks = 0;
        int pendingTasks = 0;

        for (Task task : tasks) {
            if (task.isDone()) {
                doneTasks++;
            } else {
                pendingTasks++;
            }
        }

        try {
            int summaryLines = 0;
            for (String line : Arrays.asList("Liczba zadań: " + tasks.size(),
                    "Liczba zadań zrealizowanych: " + doneTasks,
                    "Liczba zadań niezrealizowanych: " + pendingTasks)) {
                summaryLines = Math.max(summaryLines, line.length());
            }

            StringBuilder lineBuilder = new StringBuilder();
            for (int i = 0; i < summaryLines + 2; i++) {
                lineBuilder.append("-");
            }

            writer.newLine();
            writer.write("+" + lineBuilder.toString() + "+");
            writer.newLine();
            writer.write("| " + String.format("%-" + summaryLines + "s |", "Podsumowanie raportu") + " ");
            writer.newLine();
            writer.write("|" + lineBuilder.toString() + "|");
            writer.newLine();
            writer.write("| " + String.format("%-" + summaryLines + "s |", "Liczba zadań: " + tasks.size()) + " ");
            writer.newLine();
            writer.write("| " + String.format("%-" + summaryLines + "s |", "Liczba zadań zrealizowanych: " + doneTasks) + " ");
            writer.newLine();
            writer.write("| " + String.format("%-" + summaryLines + "s |", "Liczba zadań niezrealizowanych: " + pendingTasks) + " ");
            writer.newLine();
            writer.write("+" + lineBuilder.toString() + "+");
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void build() {
        try {
            if (writer != null) {
                writer.close();
                System.out.println("Raport zapisany do pliku: " + this.exportPath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}