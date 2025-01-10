package com.ztp.ztpproject.builder;
import java.util.Date;
import com.ztp.ztpproject.models.Task;

public class TxtBuilder implements IBuilder {
    public void addTitle(Date timeFrom, Date timeTo){};
    public void addTask(Task task){};
    public void addSummary(){};
    public void build(String exportPath){
        System.out.println("Zapisano raport do pliku: " + exportPath);
    };
}
