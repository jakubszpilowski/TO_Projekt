package com.projekt.commands.trains;

import com.projekt.commands.Command;
import com.projekt.entity.*;
import com.projekt.manager.TrainManager;

public class CreateTrain implements Command {
    private final TrainManager trainManager;
    private final Train train;

    public CreateTrain(TrainManager trainManager, Train train) {
        this.trainManager = trainManager;
        this.train = train;
    }

    @Override
    public void execute() {
        this.trainManager.add(train.getTrainId(), train);
    }

    @Override
    public void undo() {
        this.trainManager.delete(train.getTrainId());
    }
}
