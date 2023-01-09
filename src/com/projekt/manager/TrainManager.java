package com.projekt.manager;

import com.projekt.commands.trains.CreateTrain;
import com.projekt.commands.trains.DeleteTrain;
import com.projekt.commands.trains.EditTrain;
import com.projekt.entity.Train;

import java.util.*;

// Invoker class
public class TrainManager extends Manager{
    private TreeMap<Integer, Train> trainCollection;

    public TrainManager() {
    }

    public TreeMap<Integer, Train> getTrainCollection(){
        return trainCollection;
    }

    public void setTrainCollection(TreeMap<Integer, Train> trainCollection){
        this.trainCollection = trainCollection;
    }

    public void print() {
        System.out.println(
                "trainId trainNumber trainType from to departureTime arrivalTime platform date numberOfSeats"
        );
        getTrainCollection().forEach((key, value) -> System.out.println(key + " " + value.printTrain()));
    }
}
