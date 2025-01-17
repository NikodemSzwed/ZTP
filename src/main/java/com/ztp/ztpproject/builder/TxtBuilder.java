package com.ztp.ztpproject.builder;

import com.ztp.ztpproject.models.Task;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * TxtBuilder is responsible for constructing reports in TXT format. It provides
 * methods to add a title, tasks, and a summary to the report. The constructed
 * report can then be exported to a specified path.
 *
 * It extends the AbstractBuilder class and implements the IBuilder interface,
 * encapsulating the logic for building and exporting reports in a text format.
 *
 * @version 1.0
 */
public class TxtBuilder extends AbstractBuilder {

    private StringBuilder content;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    public TxtBuilder() {
        this.content = new StringBuilder();
    }

    /**
     * Adds a title to the report, containing the start and end time of the
     * report. The title is formatted as a table with a single row and a single
     * column, containing the text "Raport zadań od <start time> do <end time>".
     *
     * @param timeFrom start time of the report
     * @param timeTo end time of the report
     */
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

    /**
     * Adds a task to the report. The task is represented as a table with 4 rows
     * and a single column, containing the name of the task, whether it is
     * completed or not, its priority, and its deadline.
     *
     * @param task the task to be added to the report
     */
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

    /**
     * Adds a summary to the report, detailing the total number of tasks, the
     * number of completed tasks, and the number of pending tasks.
     *
     * The summary is formatted as a table with 4 rows and a single column,
     * containing the total number of tasks, the number of completed tasks, and
     * the number of pending tasks. The summary is separated from the rest of
     * the report by a line of dashes.
     *
     * @param tasks the list of tasks to be summarized
     */
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

    /**
     * Builds the report in TXT format and saves it to the specified path.
     *
     * @param exportPath path where the report should be saved
     */
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
