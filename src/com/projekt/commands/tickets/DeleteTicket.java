package com.projekt.commands.tickets;

import com.projekt.commands.Command;
import com.projekt.entity.Ticket;
import com.projekt.manager.TicketManager;

public class DeleteTicket implements Command {
    private final TicketManager ticketManager;
    private final int idTicket;
    private final Ticket previousTicketState;

    public DeleteTicket(TicketManager ticketManager, int idTicket) {
        this.ticketManager = ticketManager;
        this.idTicket = idTicket;
        this.previousTicketState = ticketManager.getCollection().get(idTicket);
    }

    @Override
    public void execute() {
        this.ticketManager.delete(idTicket);
    }

    @Override
    public void undo() {
        this.ticketManager.add(previousTicketState.getTicketId(), previousTicketState);
    }
}
