package com.projekt;

import com.projekt.commands.trains.CreateTrain;
import com.projekt.commands.trains.DeleteTrain;
import com.projekt.commands.trains.PrintTrains;
import com.projekt.commands.trains.UpdateTrain;
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
import java.util.Objects;
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
            System.out.println("(S)Synchronize");
            System.out.println("(Q)uit");
            option = input.nextLine().toUpperCase(Locale.ROOT);

            switch (option) {
                case "C" -> create();
                case "E" -> edit();
                case "D" -> delete();
                case "P" -> print();
                case "U" -> undo();
                case "R" -> redo();
                case "S" -> synchronize();
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

    private void edit() {
        Scanner in = new Scanner(System.in);
        System.out.println("Insert train ID: ");
        int id = in.nextInt();

        if(this.trainManager.getCollection().containsKey(id)){
            TrainFactory trainFactory;
            String type = this.trainManager.getCollection().get(id).getTrainType();
            if (Objects.equals(type, "pasażerski")) {
                trainFactory = new PassengerTrainFactory();
            } else if (Objects.equals(type, "towarowy")) {
                trainFactory = new CargoTrainFactory();
            }
            else {
                System.out.println("Error!");
                return;
            }

            Train ogTrain = this.trainManager.getCollection().get(id);
            Train clonedTrain = trainFactory.createTrain(ogTrain.getTrainId(), ogTrain.getTrainNumber(), ogTrain.getFrom(), ogTrain.getTo(),
                    ogTrain.getDepartureTime(), ogTrain.getArrivalTime(), ogTrain.getPlatform(), ogTrain.getDate());

            System.out.println("Choose field which you want to edit: ");
            System.out.println("[D] Date");
            System.out.println("[F] From");
            System.out.println("[T] To");
            System.out.println("[DT] Departure time");
            System.out.println("[AT] Arrival time");
            System.out.println("[P] Platform");

            Scanner input = new Scanner(System.in);
            String option = input.nextLine();

            switch (option) {
                case "D" -> clonedTrain.setDate(getDate());
                case "F" -> clonedTrain.setFrom(getFrom());
                case "T" -> clonedTrain.setTo(getTo());
                case "DT" -> clonedTrain.setDepartureTime(getDepartureTime());
                case "AT" -> clonedTrain.setArrivalTime(getArrivalTime());
                case "PT" -> clonedTrain.setPlatform(getPlatform());
                default -> {
                    System.out.println("Invalid option, try again!");
                    return;
                }
            }
            this.system.executeCommand(new UpdateTrain(this.trainManager, clonedTrain));
        } else {
            System.out.println("Train does not exist!");
        }
    }
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
        this.system.print(new PrintTrains(this.trainManager));
        System.out.println("END... PRESS ENTER!");
    }
    private void undo(){
        this.system.undo();
    }
    private void redo(){
        this.system.redo();
        System.out.println("Redo last command!");
    }

    private void synchronize() {
        this.trainManager.synchronize();
        System.out.println("Synchronized with database!");
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
