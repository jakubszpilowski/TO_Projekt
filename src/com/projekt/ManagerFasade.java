package com.projekt;

import com.projekt.manager.TicketManager;
import com.projekt.manager.TrainManager;

public class ManagerFasade {
    public void chooseManager(String manager){
        if(manager.equalsIgnoreCase("tr")){
            TrainManager trainManager = new TrainManager();
            trainManager.execute();
        } else if (manager.equalsIgnoreCase("ti")) {
            TicketManager ticketManager = new TicketManager();
            ticketManager.execute();
        } else {
            System.out.println("Wrong option provided!");
        }
    }
}
