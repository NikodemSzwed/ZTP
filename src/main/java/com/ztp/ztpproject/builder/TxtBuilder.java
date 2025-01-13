package com.ztp.ztpproject.builder;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import com.ztp.ztpproject.models.Task;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;

public class TxtBuilder extends AbstractBuilder {
    private StringBuilder content;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    public TxtBuilder() {
        this.content = new StringBuilder();
    }

    public void addTitle(Date timeFrom, Date timeTo) {
        String formattedTimeFrom = dateFormat.format(timeFrom);
        String formattedTimeTo = dateFormat.format(timeTo);

        content.append("+" + "-".repeat(42) + "+")
            .append("\n")
            .append("| Raport zadań od " + formattedTimeFrom + " do " + formattedTimeTo + " |")
            .append("\n")
            .append("+" + "-".repeat(42) + "+")
            .append("\n\n");
    }

    public void addTask(Task task) {
        String formattedDeadline = dateFormat.format(task.getDeadline());

        content.append("Nazwa zadania: " + task.getName())
            .append("\n")
            .append("Ukończone: " + (task.isDone() ? "Tak" : "Nie"))
            .append("\n")
            .append("Priorytet: " + task.getPriority())
            .append("\n")
            .append("Deadline: " + formattedDeadline)
            .append("\n")
            .append("-".repeat(30))
            .append("\n");
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

        content.append("\n")
            .append("+" + lineBuilder.toString() + "+")
            .append("\n")
            .append("| " + String.format("%-" + summaryLines + "s |", "Podsumowanie raportu") + " ")
            .append("\n")
            .append("|" + lineBuilder.toString() + "|")
            .append("\n")
            .append("| " + String.format("%-" + summaryLines + "s |", "Liczba zadań: " + tasks.size()) + " ")
            .append("\n")
            .append("| " + String.format("%-" + summaryLines + "s |", "Liczba zadań zrealizowanych: " + doneTasks) + " ")
            .append("\n")
            .append("| " + String.format("%-" + summaryLines + "s |", "Liczba zadań niezrealizowanych: " + pendingTasks) + " ")
            .append("\n")
            .append("+" + lineBuilder.toString() + "+")
            .append("\n");
    }

    public void build(String exportPath) {
        String correctExportPath = ensureCorrectExtension(exportPath, ".txt");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(correctExportPath, false))) {
            if (content != null) {
                writer.write(content.toString());
                writer.close();
                System.out.println("Raport zapisany do pliku: " + correctExportPath);
            } else {
                System.out.println("Brak zawartości do zapisania.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}