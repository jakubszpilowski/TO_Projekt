package com.projekt.commands;

import com.projekt.TicketExporter;
import com.projekt.commands.Command;
import com.projekt.entity.Ticket;

import java.util.List;

public class ExportTicketsCommand implements Command {
    private final List<Ticket> ticketsToExport;
    private final TicketExporter ticketExporterReceiver;

    public ExportTicketsCommand(List<Ticket> ticketsToExport, TicketExporter ticketExporterReceiver) {
        this.ticketsToExport = ticketsToExport;
        this.ticketExporterReceiver = ticketExporterReceiver;
    }

    @Override
    public void execute() {
        ticketsToExport.forEach(ticket -> ticketExporterReceiver.exportTicket(ticket, "ticket_"
                + ticket.getTicketId()));
    }

    @Override
    public void undo() {

    }
}
