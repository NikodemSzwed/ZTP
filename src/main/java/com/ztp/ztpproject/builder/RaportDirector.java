package com.ztp.ztpproject.builder;

import com.ztp.ztpproject.models.Task;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The RaportDirector class is responsible for directing the construction of
 * reports. It acts as a director in the builder pattern, using an IBuilder
 * implementation to construct and export reports in different formats, such as
 * TXT and PDF.
 *
 * The class provides methods to generate reports based on a list of tasks and a
 * specified time range. It supports exporting the report to a specified path or
 * to a default export path.
 *
 * The class is designed to be flexible and extensible, allowing for different
 * builder implementations to be used for generating reports in various formats.
 *
 * @version 1.0
 */
public class RaportDirector {

    private IBuilder builder;
    private String defaultExportPath;

    public RaportDirector(String defaultExportPath) {
        this.defaultExportPath = defaultExportPath;
    }

    /**
     * Generates a report in TXT format for the specified tasks list within the
     * given time range and exports it to the specified path.
     *
     * @param tasksList the list of tasks to include in the report
     * @param timeFrom the start date of the report time range
     * @param timeTo the end date of the report time range
     * @param exportPath the path where the report should be exported
     */
    public void generateRaportTxt(List<Task> tasksList, Date timeFrom, Date timeTo, String exportPath) {
        builder = new TxtBuilder();
        generateRaport(tasksList, timeFrom, timeTo, exportPath);
    }

    /**
     * Generates a report in TXT format for the specified tasks list within the
     * given time range and exports it to the default export path.
     *
     * @param tasksList the list of tasks to include in the report
     * @param timeFrom the start date of the report time range
     * @param timeTo the end date of the report time range
     */
    public void generateRaportTxt(List<Task> tasksList, Date timeFrom, Date timeTo) {
        builder = new TxtBuilder();
        generateRaport(tasksList, timeFrom, timeTo);
    }

    /**
     * Generates a report in PDF format for the specified tasks list within the
     * given time range and exports it to the specified path.
     *
     * @param tasksList the list of tasks to include in the report
     * @param timeFrom the start date of the report time range
     * @param timeTo the end date of the report time range
     * @param exportPath the path where the report should be exported
     */
    public void generateRaportPdf(List<Task> tasksList, Date timeFrom, Date timeTo, String exportPath) {
        builder = new PdfBuilder();
        generateRaport(tasksList, timeFrom, timeTo, exportPath);
    }

    /**
     * Generates a report in PDF format for the specified tasks list within the
     * given time range and exports it to the default export path.
     *
     * @param tasksList the list of tasks to include in the report
     * @param timeFrom the start date of the report time range
     * @param timeTo the end date of the report time range
     */
    public void generateRaportPdf(List<Task> tasksList, Date timeFrom, Date timeTo) {
        builder = new PdfBuilder();
        generateRaport(tasksList, timeFrom, timeTo);
    }

    /**
     * Generates a report for the specified tasks list within the given time
     * range and exports it to the specified path.
     *
     * @param tasksList the list of tasks to include in the report
     * @param timeFrom the start date of the report time range
     * @param timeTo the end date of the report time range
     * @param exportPath the path where the report should be exported
     */
    private void generateRaport(List<Task> tasksList, Date timeFrom, Date timeTo, String exportPath) {
        builder.addTitle(timeFrom, timeTo);

        // Filtering the list of tasks to include only those with deadlines within the specified time range
        List<Task> filteredTasksList = tasksList.stream()
                .filter(task -> !task.getDeadline().before(timeFrom) && !task.getDeadline().after(timeTo))
                .collect(Collectors.toList());

        for (Task task : filteredTasksList) {
            builder.addTask(task);
        }

        builder.addSummary(filteredTasksList);
        builder.build(exportPath);
    }

    /**
     * Generates a report for the specified tasks list within the given time
     * range and exports it to the default export path.
     *
     * @param tasksList the list of tasks to include in the report
     * @param timeFrom the start date of the report time range
     * @param timeTo the end date of the report time range
     */
    private void generateRaport(List<Task> tasksList, Date timeFrom, Date timeTo) {
        generateRaport(tasksList, timeFrom, timeTo, defaultExportPath);
    }
}
