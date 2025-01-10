package com.ztp.ztpproject.builder;
import com.ztp.ztpproject.models.Task;
import java.util.Date;
import java.util.List;

public class RaportDirector {
    private IBuilder builder;
    private String defaultExportPath;

    public RaportDirector(IBuilder builder, String defaultExportPath) {
        this.builder = builder;
        this.defaultExportPath = defaultExportPath;
    }

    public void generateRaport(List<Task> tasksList, Date timeFrom, Date timeTo, String exportPath) {
        if (exportPath == null || exportPath.isEmpty()) {
            exportPath = defaultExportPath;
        }

        builder.addTitle(timeFrom, timeTo);

        for (Task task : tasksList) {
            if (!task.GetDeadline().before(timeFrom) && !task.GetDeadline().after(timeTo)) {
                builder.addTask(task);
            }
        }

        builder.addSummary();
        builder.build(exportPath);
    }
}
