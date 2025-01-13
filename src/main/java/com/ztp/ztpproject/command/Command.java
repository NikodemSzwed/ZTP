package com.ztp.ztpproject.command;

public interface Command {
    void execute();
    void undo();
}