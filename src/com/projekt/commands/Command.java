package com.projekt.commands;

// The base command interface
public interface Command {
    void execute();
    void undo();
}
