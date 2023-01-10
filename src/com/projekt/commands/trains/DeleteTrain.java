package com.projekt.commands.trains;

import com.projekt.commands.Command;
import com.projekt.entity.Train;
import com.projekt.manager.TrainManager;

public class DeleteTrain implements Command {
    private final TrainManager trainManager;
    private final int idTrain;
    private final Train previousTrainState;

    public DeleteTrain(TrainManager trainManager, int idTrain) {
        this.trainManager = trainManager;
        this.idTrain = idTrain;
        this.previousTrainState = trainManager.getCollection().get(idTrain);
    }

    @Override
    public void execute() {
        this.trainManager.delete(idTrain);
    }

    @Override
    public void undo() {
        this.trainManager.add(previousTrainState.getTrainId(), previousTrainState);
    }
}
