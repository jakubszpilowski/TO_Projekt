package com.projekt.manager;

import com.projekt.commands.Command;

import java.util.Collection;
import java.util.Stack;

public abstract class Manager {
    private static final Stack<Command> undoCommands = new Stack<Command>();;
    private static final Stack<Command> redoCommands = new Stack<Command>();

    public void executeCommand(Command command) {
        command.execute();
        undoCommands.push(command);
    }

    public void undo() {
        if (!undoCommands.isEmpty()) {
            Command command = undoCommands.pop();
            redoCommands.push(command);
            command.undo();
        }
    }

    public void redo() {
        if (!redoCommands.isEmpty()) {
            Command command = redoCommands.pop();
            command.execute();
            undoCommands.push(command);
        }
    }

    public abstract void print();
}
