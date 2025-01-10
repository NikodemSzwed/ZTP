package com.ztp.ztpproject.builder;
import com.ztp.ztpproject.models.Task;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class RaportDirector {
    private IBuilder builder;
    private String defaultExportPath;

    public RaportDirector(String defaultExportPath) {
        this.defaultExportPath = defaultExportPath;
    }

    public void generateRaportTxt(List<Task> tasksList, Date timeFrom, Date timeTo, String exportPath) {
        builder = new TxtBuilder();
        generateRaport(tasksList, timeFrom, timeTo, exportPath);
    }

    public void generateRaportTxt(List<Task> tasksList, Date timeFrom, Date timeTo) {
        builder = new TxtBuilder();
        generateRaport(tasksList, timeFrom, timeTo);
    }

    // public void generateRaportPdf(List<Task> tasksList, Date timeFrom, Date timeTo, String exportPath) {
    //     builder = new PdfBuilder();
    //     generateRaport(tasksList, timeFrom, timeTo, exportPath);
    // }

    private void generateRaport(List<Task> tasksList, Date timeFrom, Date timeTo, String exportPath) {
        builder.initRaport(exportPath);
        builder.addTitle(timeFrom, timeTo);

        // Filtrowanie listy zadań w taki sposób, aby zostały tylko te z deadlinem w podanym przedziale czasowym
        List<Task> filteredTasksList = tasksList.stream()
            .filter(task -> !task.getDeadline().before(timeFrom) && !task.getDeadline().after(timeTo))
            .collect(Collectors.toList());

        for (Task task : filteredTasksList) {
            builder.addTask(task);
        }

        builder.addSummary(filteredTasksList);
        builder.build();
    }

    public void generateRaport(List<Task> tasksList, Date timeFrom, Date timeTo) {
        generateRaport(tasksList, timeFrom, timeTo, defaultExportPath);
    }
}
