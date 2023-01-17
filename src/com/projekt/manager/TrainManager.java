package com.projekt.manager;
import com.projekt.DAO.Dao;
import com.projekt.entity.Train;

import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

// Receiver class
public class TrainManager extends Manager<Train>{
    private final Dao<Train> trainDao;
    public TrainManager(Dao<Train> trainDao) {
        this.trainDao = trainDao;
    }

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
            if(this.getCollection().containsKey(idTrain)) {
                this.getCollection().replace(idTrain, train);
            } else {
                add(idTrain, train);
            }
        }

    @Override
    public void delete(int idTrain) {
            this.getCollection().remove(idTrain);
        }

    public void synchronize() {
        TreeMap<Integer, Train> map = this.trainDao.getAll().stream()
                .collect(Collectors.toMap(Train::getTrainId, Function.identity(), (o1, o2) -> o1, TreeMap::new));
        this.setCollection(map);
    }
    //todo: more commands
}
