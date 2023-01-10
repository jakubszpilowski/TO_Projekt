package com.projekt.manager;

import com.projekt.TicketExporter;
import com.projekt.entity.Ticket;
import com.projekt.strategy.TicketExportStrategy;

// Receiver class
public class TicketManager extends Manager<Ticket> {
    private final TicketExporter ticketExporter;

    public TicketManager(TicketExporter ticketExporter) {
        this.ticketExporter = ticketExporter;
    }

    @Override
    public void add(int idTicket, Ticket ticket) {
        this.getCollection().put(idTicket, ticket);
    }

    @Override
    public void print() {
        this.getCollection().forEach((idTicket, ticket) -> System.out.println(ticket.toString()));
    }

    @Override
    public void update(int idTicket, Ticket ticket) {
        this.getCollection().replace(idTicket, ticket);
    }

    @Override
    public void delete(int idTicket) {
        this.getCollection().remove(idTicket);
    }

    public void export(TicketExportStrategy ticketExportStrategy) {
        this.ticketExporter.setExportStrategy(ticketExportStrategy);
        this.getCollection().forEach((idTicket, ticket) -> ticketExporter.exportTicket(ticket, "ticket_" + idTicket));
    }
}
