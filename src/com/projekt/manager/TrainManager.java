package com.projekt.manager;
import com.projekt.entity.CargoTrainFactory;
import com.projekt.entity.PassengerTrainFactory;
import com.projekt.entity.Train;
import com.projekt.entity.TrainFactory;

// Receiver class
public class TrainManager extends Manager<Train>{

    public TrainManager() {}

    @Override
    public void add(int idTrain, Train train) {
        this.getCollection().put(idTrain, train);
    }

    @Override
    public void print() {
            this.getCollection().forEach((idTicket, train) -> System.out.println(train.toString()));
        }

    @Override
    public void update(int idTrain, Train train) {
            this.getCollection().replace(idTrain, train);
        }

    @Override
    public void delete(int idTrain) {
            this.getCollection().remove(idTrain);
        }

    //todo: more commands
}
