package com.projekt;

import com.projekt.DAO.TicketDao;
import com.projekt.DAO.TrainDao;
import com.projekt.manager.TicketManager;
import com.projekt.manager.TrainManager;
import java.util.Scanner;

public class LauncherFacade {
    SystemInvoker system = new SystemInvoker();
    TrainManager trainManager = new TrainManager(new TrainDao());
    menuTrain trainMenu = new menuTrain(system,trainManager);
    TicketManager ticketManager = new TicketManager(new TicketDao());
    menuTicket ticketMenu = new menuTicket(system, ticketManager);

    public void run() {
        Scanner input = new Scanner(System.in);
        String option;
        boolean running = true;

        while (running) {
            System.out.println("Admin panel: ");
            System.out.println("(Tr)ain - trains management");
            System.out.println("(Ti)cket - tickets management");
            System.out.println("(Q)uit - exit");
            option = input.nextLine();

            switch (option) {
                case "Tr" -> this.trainMenu.show();
                case "Ti" -> this.ticketMenu.show();
                case "Q" -> {running = false;}
                default -> System.out.println("Invalid option!");
            }
        }
    }
}
