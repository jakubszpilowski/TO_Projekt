package com.projekt.commands.trains;

import com.projekt.commands.Command;
import com.projekt.entity.Train;

import java.util.List;

public class DeleteTrain implements Command {
    private List<Train> trainList;

    public DeleteTrain(List<Train> trainList) {
        this.trainList = trainList;
    }

    @Override
    public void execute() {

    }

    @Override
    public void undo() {

    }
}
