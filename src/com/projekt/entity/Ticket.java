package com.projekt.entity;

public class Ticket {
    private Train train;
    private User user;
    private int ticketId;
    private int seatNumber;
    private double price;

    public Ticket(Train train, User user, int ticketId, int seatNumber, double price) {
        this.train = train;
        this.user = user;
        this.ticketId = ticketId;
        this.seatNumber = seatNumber;
        this.price = price;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
