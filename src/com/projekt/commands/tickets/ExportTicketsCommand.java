package com.projekt.commands.tickets;

import com.projekt.commands.Command;
import com.projekt.manager.TicketManager;

public class ExportTicketsCommand implements Command {
    private final TicketManager ticketManager;

    public ExportTicketsCommand(TicketManager ticketManager) {
        this.ticketManager = ticketManager;
    }

    @Override
    public void execute() {
        this.ticketManager.export();
    }

    @Override
    public void undo() {
        // undoable
    }
}
