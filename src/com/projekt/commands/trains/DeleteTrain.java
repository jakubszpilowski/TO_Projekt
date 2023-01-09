package com.projekt.commands.trains;

import com.projekt.commands.Command;
import com.projekt.entity.Train;

import java.util.TreeMap;


public class DeleteTrain implements Command {
    private Train backup;
    private final int id;
    private final TreeMap<Integer, Train> trainCollection;

    public DeleteTrain(int id, TreeMap<Integer, Train> trainCollection) {
        this.id = id;
        this.trainCollection = trainCollection;
    }

    @Override
    public void execute() {
        backup = trainCollection.remove(id);
    }

    @Override
    public void undo() {
        trainCollection.put(id, backup);
    }
}
