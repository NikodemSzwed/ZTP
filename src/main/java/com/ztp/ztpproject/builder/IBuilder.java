package com.ztp.ztpproject.builder;

import com.ztp.ztpproject.models.Task;
import java.util.Date;
import java.util.List;

/**
 * The IBuilder interface provides methods for building a report. The report can
 * include a title, tasks, and a summary. Implementations are responsible for
 * defining how these components are added and how the final report is
 * constructed and exported to a specified path.
 *
 * @version 1.0
 */
public interface IBuilder {

    void addTitle(Date timeFrom, Date timeTo);

    void addTask(Task task);

    void addSummary(List<Task> tasks);

    void build(String exportPath);
}
