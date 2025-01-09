package com.ztp.ztpproject.builder;

public class TxtBuilder implements IBuilder {
    public void addTitle(String title){};
    public void addTask(String task){};
    public void addSummary(){};
    public void build(String exportPath){
        System.out.println("Zapisano raport do pliku: " + exportPath);
    };
}
