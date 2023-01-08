package com.projekt.entity;

import java.time.LocalTime;
import java.util.Date;

public class CargoTrain extends Train {
    public CargoTrain(int trainId,
                      int trainNumber,
                      String from,
                      String to,
                      LocalTime departureTime,
                      LocalTime arrivalTime,
                      int platform,
                      Date date) {
        trainType = "towarowy";
        numberOfSeats = 0;
        this.trainId = trainId;
        this.trainNumber = trainNumber;
        this.from = from;
        this.to = to;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.platform = platform;
        this.date = date;
    }
}
