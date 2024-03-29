package com.projekt.commands.tickets;

import com.projekt.commands.Command;
import com.projekt.entity.Ticket;
import com.projekt.manager.TicketManager;

public class CreateTicket implements Command {
    private final TicketManager ticketManager;
    private final Ticket ticket;

    public CreateTicket(TicketManager ticketManager, Ticket ticket) {
        this.ticketManager = ticketManager;
        this.ticket = ticket;
    }

    @Override
    public void execute() {
        this.ticketManager.add(ticket.getTicketId(), ticket);
    }

    @Override
    public void undo() {
        this.ticketManager.delete(ticket.getTicketId());
    }
}
