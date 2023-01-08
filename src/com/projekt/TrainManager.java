package com.projekt;

// Invoker class
public class TrainManager {
    private Command command;

    public TrainManager() {
    }

    public void executeCommand(Command command) {
        command.execute();
    }
}
