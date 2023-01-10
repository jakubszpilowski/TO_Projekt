package com.projekt.commands.tickets;

import com.projekt.commands.Command;
import com.projekt.entity.Ticket;
import com.projekt.manager.TicketManager;

public class UpdateTicket implements Command {
    private final TicketManager ticketManager;
    private final Ticket ticket;
    private final Ticket previousTicketState;

    public UpdateTicket(TicketManager ticketManager, Ticket ticket) {
        this.ticketManager = ticketManager;
        this.ticket = ticket;
        this.previousTicketState = ticketManager.getCollection().get(ticket.getTicketId());
    }

    @Override
    public void execute() {
        this.ticketManager.update(ticket.getTicketId(), ticket);
    }

    @Override
    public void undo() {
        this.ticketManager.delete(ticket.getTicketId());
        this.ticketManager.update(previousTicketState.getTicketId(), previousTicketState);
    }
}
