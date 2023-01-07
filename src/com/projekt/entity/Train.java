package com.projekt.entity;

import java.time.LocalTime;
import java.util.Date;

public class Train {
    private int trainId;
    private int trainNumber;
    private String trainType;
    private String from;
    private String to;
    private LocalTime departureTime;
    private LocalTime arrivalTime;
    private int platform;
    private Date date;
    private int numberOfSeats;

    public Train(int trainId, int trainNumber, String trainType, String from, String to, LocalTime departureTime,
                 LocalTime arrivalTime, int platform, Date date, int numberOfSeats) {
        this.trainId = trainId;
        this.trainNumber = trainNumber;
        this.trainType = trainType;
        this.from = from;
        this.to = to;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.platform = platform;
        this.date = date;
        this.numberOfSeats = numberOfSeats;
    }

    public int getTrainId() {
        return trainId;
    }

    public void setTrainId(int trainId) {
        this.trainId = trainId;
    }

    public int getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(int trainNumber) {
        this.trainNumber = trainNumber;
    }

    public String getTrainType() {
        return trainType;
    }

    public void setTrainType(String trainType) {
        this.trainType = trainType;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getPlatform() {
        return platform;
    }

    public void setPlatform(int platform) {
        this.platform = platform;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }
}
