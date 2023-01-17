package com.projekt;

import com.projekt.commands.tickets.ExportTicketsCommand;
import com.projekt.commands.tickets.PrintTickets;
import com.projekt.commands.trains.PrintTrains;
import com.projekt.manager.TicketManager;
import com.projekt.manager.TrainManager;
import com.projekt.strategy.PDFTicketExportStrategy;
import com.projekt.strategy.PNGTicketExportStrategy;

import java.util.Locale;
import java.util.Scanner;

public class menuTicket {
    private final SystemInvoker system;
    private final TicketManager ticketManager;
    Scanner input = new Scanner(System.in);
    String option;
    boolean quit = true;

    public menuTicket(SystemInvoker system, TicketManager ticketManager) {
        this.system = system;
        this.ticketManager = ticketManager;
    }

    public void show() {
        quit = true;
        while(quit) {
            System.out.println("Choose option: ");
            System.out.println("(EPDF) Export tickets to PDF");
            System.out.println("(EPNG) Export tickets to PNG");
            System.out.println("(P) Print tickets");
            System.out.println("(S) Synchronize");
            System.out.println("(Q)uit");
            option = input.nextLine().toUpperCase(Locale.ROOT);

            switch (option) {
                case "EPDF" -> exportToPDF();
                case "EPNG" -> exportToPNG();
                case "P" -> print();
                case "S" -> synchronize();
                case "Q" -> quit();
                default -> System.out.println("Invalid option!");
            }
            input.nextLine();
        }
    }

    private void exportToPDF() {
        this.ticketManager.getTicketExporter().setExportStrategy(new PDFTicketExportStrategy());
        this.system.executeCommand(new ExportTicketsCommand(this.ticketManager));
        System.out.println("Exported to PDF!");
    }
    private void exportToPNG() {
        this.ticketManager.getTicketExporter().setExportStrategy(new PNGTicketExportStrategy());
        this.system.executeCommand(new ExportTicketsCommand(this.ticketManager));
        System.out.println("Exported to PNG!");
    }

    private void print() {
        this.system.print(new PrintTickets(this.ticketManager));
        System.out.println("END... PRESS ENTER!");
    }

    private void synchronize() {
        this.ticketManager.synchronize();
        System.out.println("Synchronized with database!");
    }

    private void quit() {
        System.out.println("Train manager closing... Press ENTER");
        this.quit = false;
    }
}
