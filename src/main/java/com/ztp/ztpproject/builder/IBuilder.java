package com.ztp.ztpproject.builder;
import java.util.Date;
import java.util.List;
import com.ztp.ztpproject.models.Task;

public interface IBuilder {
    void addTitle(Date timeFrom, Date timeTo);
    void addTask(Task task);
    void addSummary(List<Task> tasks);
    void build(String exportPath);
}