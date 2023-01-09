package com.projekt;

import com.projekt.manager.Manager;
import com.projekt.manager.TicketManager;
import com.projekt.manager.TrainManager;

public class ManagerFasade {
    public Manager chooseManager(String manager){
        if(manager.equalsIgnoreCase("tr")){
            return new TrainManager();
        } else if (manager.equalsIgnoreCase("ti")) {
            return new TicketManager();
        } else {
            System.out.println("Wrong option provided!");
            return null;
        }
    }
}
