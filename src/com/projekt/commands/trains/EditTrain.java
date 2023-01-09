package com.projekt.commands.trains;

import com.projekt.commands.Command;
import com.projekt.entity.Train;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Scanner;
import java.util.TreeMap;

public class EditTrain implements Command {
    private final int id;
    private Train backup;
    private final TreeMap<Integer, Train> trainCollection;

    public EditTrain(int id, TreeMap<Integer, Train> trainCollection) {
        this.id = id;
        this.trainCollection = trainCollection;
    }

    @Override
    public void execute() {
        backup = trainCollection.get(id);
        Train train = trainCollection.get(id);
        System.out.println(id + " " + train.printTrain());
        Scanner in = new Scanner(System.in);
        int op;

        while(true) {
            System.out.println("Choose parameter to edit: ");
            System.out.println("1. Time");
            System.out.println("2. Date");
            System.out.println("3. Platform");
            System.out.println("4. Route");
            op = in.nextInt();

            if (op == 1) {
                setTime(train);
                break;
            } else if (op == 2) {
                while (true) {
                    try {
                        setDate(train);
                        break;
                    } catch (ParseException ex) {
                        System.out.println("Try again!");
                    }
                }
                break;
            } else if (op == 3) {
                setPlatform(train);
                break;
            }  else if (op == 4) {
                setRoute(train);
                break;
            }else {
                System.out.println("Try again!");
            }
        }
    }

    @Override
    public void undo() {
        trainCollection.put(id, backup);
    }

    private void setTime(Train train) {
        System.out.println("Current time: \n" +
                "arrival: " + train.getArrivalTime() + "\n" + "departure: " + train.getDepartureTime() + "\n" +
                "Enter new time (format 'hh:mm:ss' 'hh:mm:ss'): ");
        Scanner in = new Scanner(System.in);
        String[] newTime;
        newTime = in.nextLine().split(" ");
        train.setArrivalTime(LocalTime.parse(newTime[0]));
        train.setDepartureTime(LocalTime.parse(newTime[1]));
    }

    private void setPlatform(Train train) {
        Scanner in = new Scanner(System.in);
        int op;
        System.out.println("Current platform number: " + train.getPlatform() +"\n" +
                "Enter new platform number: ");
        op = in.nextInt();
        train.setPlatform(op);
    }

    private void setDate(Train train) throws ParseException {
        System.out.println("Current date: " + train.getDate() + "\n" +
                "Enter new date (format 'yyyy-MM-dd'): ");
        Scanner in = new Scanner(System.in);
        String newDate = in.nextLine();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        train.setDate(df.parse(newDate));
    }

    private void setRoute(Train train) {
        System.out.println("Current route: \n" +
                "from: " + train.getFrom() + "\n" + "to: " + train.getTo() + "\n" +
                "Enter new route (format 'from' 'to'): ");
        Scanner in = new Scanner(System.in);
        String[] newRoute;
        newRoute = in.nextLine().split(" ");
        train.setFrom(newRoute[0]);
        train.setTo(newRoute[1]);
    }
}
