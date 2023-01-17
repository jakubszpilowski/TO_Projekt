package com.projekt.commands.trains;

import com.projekt.commands.Command;
import com.projekt.entity.Train;
import com.projekt.manager.TrainManager;

public class UpdateTrain implements Command {
    private final TrainManager trainManager;
    private final Train train;
    private final Train previousTrainState;

    public UpdateTrain(TrainManager trainManager, Train train) {
        this.trainManager = trainManager;
        this.train = train;
        this.previousTrainState = trainManager.getCollection().get(train.getTrainId());
    }

    @Override
    public void execute() {
        this.trainManager.update(train.getTrainId(), train);
    }

    @Override
    public void undo() {
        this.trainManager.update(previousTrainState.getTrainId(), previousTrainState);
    }
}
