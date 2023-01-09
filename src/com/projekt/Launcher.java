package com.projekt;

import com.projekt.commands.trains.CreateTrain;
import com.projekt.commands.trains.DeleteTrain;
import com.projekt.commands.trains.EditTrain;
import com.projekt.entity.Train;
import com.projekt.manager.Manager;
import com.projekt.manager.TrainManager;

import java.util.Locale;
import java.util.Scanner;
import java.util.TreeMap;

public class Launcher {
    public void run(){
        Scanner input = new Scanner(System.in);
        String option;
        ManagerFasade managerFasade = new ManagerFasade();

        while(true){
            Manager manager;
            System.out.println("Admin panel: ");
            System.out.println("(Tr)ain - trains management");
            System.out.println("(Ti)cket - tickets management");
            System.out.println("(Q)uit - exit");
            option = input.nextLine();

            if (option.equalsIgnoreCase("q")){
                System.out.println("App closing now!");
                break;
            } else {
                manager = managerFasade.chooseManager(option);
            }

            if(manager == null) {
                System.out.println("Try again!");
            } else if (manager.getClass() == TrainManager.class) {
                menuTrain((TrainManager) manager);
            } else  {
                menuTicket(manager);
            }
        }
    }

    private void menuTrain(TrainManager manager){
         /*TODO
            ściągnąć listę pociągów z bazy
        * */
        TreeMap<Integer, Train> trainCollection = new TreeMap<>();
        manager.setTrainCollection(trainCollection);
        Scanner input = new Scanner(System.in);
        int id;
        boolean quit = true;
        String option;

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
                case "C" -> manager.executeCommand(new CreateTrain(trainCollection));
                case "E" -> {
                    System.out.println("Insert id: ");
                    id = input.nextInt();
                    if(trainCollection.containsKey(id)){
                        manager.executeCommand(new EditTrain(id, trainCollection));
                    } else {
                        System.out.println("Train does not exist!");
                    }
                }
                case "D" -> {
                    System.out.println("Insert id: ");
                    id = input.nextInt();
                    if(trainCollection.containsKey(id)){
                        manager.executeCommand(new DeleteTrain(id, trainCollection));
                    } else {
                        System.out.println("Train does not exist!");
                    }
                }
                case "P" -> manager.print();
                case "U" -> manager.undo();
                case "R" -> manager.redo();
                case "Q" -> {
                    System.out.println("Train manager closing...");
                    quit = false;
                }
                default -> System.out.println("Invalid option!");
            }
            input.nextLine();
        }
    }

    private void menuTicket(Manager manager){

    }
}
