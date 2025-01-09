package com.ztp.ztpproject;
import com.ztp.ztpproject.builder.IBuilder;
import com.ztp.ztpproject.builder.TxtBuilder;


public class Main {
    public static void main(String[] args) {
        // System.out.println("Hello, Java!");
        IBuilder builder = new TxtBuilder();
        builder.addTitle("Raport");
        builder.addTask("Task 1");
        builder.addTask("Task 2");
        builder.addTask("Task 3");
        builder.addSummary();
        builder.build("raport.txt");
    }
}
