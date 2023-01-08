package com.projekt.entity;

import java.time.LocalTime;
import java.util.Date;

public class CargoTrainFactory implements TrainFactory {
    @Override
    public Train createTrain(int trainId,
                             int trainNumber,
                             String from,
                             String to,
                             LocalTime departureTime,
                             LocalTime arrivalTime,
                             int platform,
                             Date date) {

        return new CargoTrain(trainId, trainNumber, from, to, departureTime,
                arrivalTime, platform, date);
    }
}
