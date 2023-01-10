package com.projekt.commands.tickets;

import com.projekt.commands.Command;
import com.projekt.manager.TicketManager;

public class PrintTickets implements Command {
    private final TicketManager ticketManager;

    public PrintTickets(TicketManager ticketManager) {
        this.ticketManager = ticketManager;
    }

    @Override
    public void execute() {
        this.ticketManager.print();
    }

    @Override
    public void undo() {
        // undoable
    }
}
