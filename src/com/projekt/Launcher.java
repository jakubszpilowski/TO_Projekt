package com.projekt;

import java.util.Scanner;

public class Launcher {
    public void run(){
        Scanner input = new Scanner(System.in);
        boolean quit = true;
        String option;
        ManagerFasade managerFasade = new ManagerFasade();

        while(quit){
            System.out.println("Admin panel: ");
            System.out.println("(Tr)ain - trains management");
            System.out.println("(Ti)cket - tickets management");
            System.out.println("(Q)uit - exit");
            option = input.nextLine();

            if (option.equalsIgnoreCase("q")){
                System.out.println("App closing now!");
                quit = false;
            } else {
                managerFasade.chooseManager(option);
            }
        }
    }
}
