package com.projekt.commands.trains;

import com.projekt.commands.Command;
import com.projekt.entity.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.Scanner;
import java.util.TreeMap;

public class CreateTrain implements Command {
    private Train backup;
    private int id;
    private final TreeMap<Integer, Train> trainCollection;

    public CreateTrain(TreeMap<Integer, Train> trainCollection){
        this.trainCollection = trainCollection;
    }

    @Override
    public void execute() {
        if (backup != null) {
            trainCollection.put(id, backup);
        } else {
            while (true) {
                System.out.println("Choose train type ([P]assenger/ [C]argo): ");
                Scanner in = new Scanner(System.in);
                String type = in.nextLine();

                if (type.equalsIgnoreCase("p")) {
                    this.id = trainCollection.size() + 1;
                    createPassenger(id);
                    break;
                } else if (type.equalsIgnoreCase("c")) {
                    this.id = trainCollection.size() + 1;
                    createCargo(id);
                    break;
                } else {
                    System.out.println("Invalid type, try again!");
                }
            }
        }
    }

    @Override
    public void undo() {
        backup = trainCollection.remove(id);
    }

    private void createPassenger(int id) {
        System.out.println("Passenger train creator");
        trainCollection.put(id,
                new PassengerTrainFactory().createTrain(
                                id,
                                getNumber(),
                                getRoute(1),
                                getRoute(2),
                                getTime(1),
                                getTime(2),
                                getPlatform(),
                                getDate()
                ));
    }

    private void createCargo(int id) {
        System.out.println("Cargo train creator");
        trainCollection.put(id,
                new CargoTrainFactory().createTrain(
                        id,
                        getNumber(),
                        getRoute(1),
                        getRoute(2),
                        getTime(1),
                        getTime(2),
                        getPlatform(),
                        getDate()
                ));
    }

    private int getNumber(){
        System.out.println("Enter train number: ");
        Scanner in = new Scanner(System.in);
        return in.nextInt();
    }

    private LocalTime getTime(int i){
        if(i == 1){
            System.out.println("Enter arrival time (format 'hh:mm:ss'): ");
        } else {
            System.out.println("Enter departure time (format 'hh:mm:ss'): ");
        }
        Scanner in = new Scanner(System.in);
        String timeString = in.nextLine();
        return LocalTime.parse(timeString);
    }

    private Date getDate() {
        while(true){
            try {
                System.out.println("Enter date (format 'yyyy-MM-dd'): ");
                Scanner in = new Scanner(System.in);
                String date = in.nextLine();
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                return df.parse(date);
            } catch (ParseException ex) {
                System.out.println("Try again!");
            }
        }
    }

    private int getPlatform(){
        System.out.println("Enter platform number: ");
        Scanner in = new Scanner(System.in);
        return in.nextInt();
    }

    private String getRoute(int i){
        if(i == 1){
            System.out.println("Enter starting destination (format 'from'): ");
        } else {
            System.out.println("Enter final destination (format 'to'): ");
        }
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }
}
