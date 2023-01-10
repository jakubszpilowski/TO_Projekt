package com.projekt.commands.trains;

import com.projekt.commands.Command;
import com.projekt.manager.TrainManager;

public class PrintTrains implements Command {
    private final TrainManager trainManager;

    public PrintTrains(TrainManager trainManager) {
        this.trainManager = trainManager;
    }

    @Override
    public void execute() {
        this.trainManager.print();
    }

    @Override
    public void undo() {
        // undoable
    }
}
