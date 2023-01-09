package com.projekt.manager;

import com.projekt.commands.Command;
import com.projekt.commands.trains.CreateTrain;
import com.projekt.commands.trains.DeleteTrain;
import com.projekt.commands.trains.EditTrain;
import com.projekt.entity.Train;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

// Invoker class
public class TrainManager {
    private final List<Train> trains = new ArrayList<>();
    private final Stack<Command> stack = new Stack<>();
    private final Stack<Command> undoStack = new Stack<>();

    public TrainManager() {
    }

    public void execute(){
        /*TODO
            ściągnąć listę pociągów z bazy
        * */

        boolean quit = true;
        Scanner input = new Scanner(System.in);
        String option;

        while(quit){
            System.out.println("Choose option: ");
            System.out.println("(C)reate new train");
            System.out.println("(E)dit train");
            System.out.println("(D)elete train");
            System.out.println("(P)rint current schedule");
            System.out.println("(Q)uit");
            if(!stack.isEmpty()){
                System.out.println("(U)ndo last change");
            }
            if(!undoStack.isEmpty()){
                System.out.println("(R)edo last change");
            }

            option = input.nextLine();

            switch (option) {
                case "C" -> {
                    undoStack.clear();
                    stack.push(new CreateTrain(trains));
                    stack.peek().execute();
                }
                case "E" -> {
                    undoStack.clear();
                    stack.push(new EditTrain(trains));
                    stack.peek().execute();
                }
                case "D" -> {
                    undoStack.clear();
                    stack.push(new DeleteTrain(trains));
                    stack.peek().execute();
                }
                case "P" -> {
                    System.out.println(
                            "trainId trainNumber trainType from to departureTime arrivalTime platform date numberOfSeats"
                    );
                    for(Train t: trains) {
                        t.printTrain();
                    }
                }
                case "U" -> {
                    if(!stack.isEmpty()) {
                        stack.peek().undo();
                        undoStack.push(stack.pop());
                    }
                }
                case "R" -> {
                    if(!undoStack.isEmpty()) {
                        undoStack.peek().undo();
                        stack.push(undoStack.pop());
                    }
                }
                case "Q" -> {
                    System.out.println("Train manager closing...");
                    quit = false;
                }
            }
        }
    }
}
