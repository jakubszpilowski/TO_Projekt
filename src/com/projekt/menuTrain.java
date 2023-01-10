package com.projekt;

import com.projekt.commands.trains.CreateTrain;
import com.projekt.commands.trains.DeleteTrain;
import com.projekt.commands.trains.PrintTrains;
import com.projekt.entity.CargoTrainFactory;
import com.projekt.entity.PassengerTrainFactory;
import com.projekt.entity.Train;
import com.projekt.entity.TrainFactory;
import com.projekt.manager.TrainManager;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class menuTrain {
    private final SystemInvoker system;
    private final TrainManager trainManager;

    Scanner input = new Scanner(System.in);
    String option;
    boolean quit = true;

    public menuTrain(SystemInvoker system, TrainManager trainManager) {
        this.system = system;
        this.trainManager = trainManager;
    }

    public void show() {
        quit = true;
        while(quit) {
            System.out.println("Choose option: ");
            System.out.println("(C)reate new train");
            System.out.println("(E)dit train");
            System.out.println("(D)elete train");
            System.out.println("(P)rint current schedule");
            System.out.println("(U)ndo last change");
            System.out.println("(R)redo last undo");
            System.out.println("(Q)uit");
            option = input.nextLine().toUpperCase(Locale.ROOT);

            switch (option) {
                case "C" -> create();
                case "E" -> edit();
                case "D" -> delete();
                case "P" -> print();
                case "U" -> undo();
                case "R" -> redo();
                case "Q" -> quit();
                default -> System.out.println("Invalid option!");
            }
            input.nextLine();
        }
    }

    private void create() {
        TrainFactory trainFactory;
        Train train;
        int id = this.trainManager.getCollection().size() + 1;

        System.out.println("Train creator!");
        System.out.println("Choose train type ([P]assenger/ [C]argo): ");
        Scanner in = new Scanner(System.in);
        String type = in.nextLine();

        switch (type) {
            case "P" -> {
                System.out.println("Passenger train creator");
                trainFactory = new PassengerTrainFactory();
                train = trainFactory.createTrain(id,getNumber(),getFrom(),getTo(),getDepartureTime(),getArrivalTime(),getPlatform(),getDate());
                system.executeCommand(new CreateTrain(this.trainManager, train));
                System.out.println("Train created!");
            }
            case "C" -> {
                System.out.println("Cargo train creator");
                trainFactory = new CargoTrainFactory();
                train = trainFactory.createTrain(id,getNumber(),getFrom(),getTo(),getDepartureTime(),getArrivalTime(),getPlatform(),getDate());
                system.executeCommand(new CreateTrain(this.trainManager, train));
                System.out.println("Train created!");
            }
            default -> System.out.println("Invalid type, try again!");
        }

    }

    private void edit(){};
    private void delete(){
        System.out.println("Insert ID: ");
        int id = input.nextInt();

        if(this.trainManager.getCollection().containsKey(id)){
            this.system.executeCommand(new DeleteTrain(this.trainManager, id));
            System.out.println("Train removed!");
        } else {
            System.out.println("Train does not exist!");
        }
    }

    private void print() {
        this.system.executeCommand(new PrintTrains(this.trainManager));
        System.out.println("END... PRESS ENTER!");
    }
    private void undo(){
        this.system.undo();
    }
    private void redo(){
        this.system.redo();
        System.out.println("Redo last command!");
    }

    private void quit() {
        System.out.println("Train manager closing... Press ENTER");
        this.quit = false;
    }


    // FUNCKJE POMOCNICZE
    private int getNumber(){
        System.out.println("Enter train number: ");
        Scanner in = new Scanner(System.in);
        return in.nextInt();
    }

    private LocalTime getArrivalTime(){
        System.out.println("Enter arrival time (format 'hh:mm:ss'): ");
        Scanner in = new Scanner(System.in);
        String timeString = in.nextLine();
        return LocalTime.parse(timeString);
    }

    private LocalTime getDepartureTime(){
        System.out.println("Enter departure time (format 'hh:mm:ss'): ");
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

    private String getFrom(){
        System.out.println("Enter starting destination (format 'from'): ");
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    private String getTo(){
        System.out.println("Enter final destination (format 'to'): ");
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

}
