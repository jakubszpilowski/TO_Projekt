package com.projekt.commands.trains;

import com.projekt.commands.Command;
import com.projekt.entity.Train;

import java.util.List;

public class CreateTrain implements Command {
    private Train backup;
    List<Train> trainList;

    public CreateTrain(List<Train> trainList){
        this.trainList = trainList;
    }

    @Override
    public void execute() {

    }

    @Override
    public void undo() {

    }
}
