package com.projekt;

import com.projekt.commands.Command;
import java.util.Stack;

public class SystemInvoker {
    private static final Stack<Command> UndoCommands = new Stack<Command>();
    private static final Stack<Command> RedoCommands = new Stack<Command>();

    public SystemInvoker() {
    }

    public void executeCommand(Command command) {
        command.execute();
        UndoCommands.push(command);
    }

    // todo: znalezc logiczny blad w funkcjach undo/redo (cos ze stosem) sprawdzic ze komedna print nie laduje na stosie
    public void undo() {
        if (!UndoCommands.empty()) {
            Command command = UndoCommands.pop();
            RedoCommands.push(command);
            command.undo();
        }
    }

    public void redo() {
        if (!RedoCommands.empty()) {
            Command command = RedoCommands.pop();
            command.execute();
            UndoCommands.push(command);
        }
    }
}