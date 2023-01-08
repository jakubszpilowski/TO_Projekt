package com.projekt.entity;

import java.time.LocalTime;
import java.util.Date;

public interface TrainFactory {
    Train createTrain(int trainId, int trainNumber, String from, String to, LocalTime departureTime,
                      LocalTime arrivalTime, int platform, Date date);
}
