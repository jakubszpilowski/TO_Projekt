package com.projekt.manager;

import com.projekt.DAO.Dao;
import com.projekt.DAO.TicketDao;
import com.projekt.TicketExporter;
import com.projekt.entity.Ticket;
import com.projekt.entity.Train;
import com.projekt.strategy.PDFTicketExportStrategy;
import com.projekt.strategy.TicketExportStrategy;

import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

// Receiver class
public class TicketManager extends Manager<Ticket> {
    private final TicketExporter ticketExporter;
    private final Dao<Ticket> ticketDao;

    public TicketManager(Dao<Ticket> ticketDao) {
        this.ticketDao = ticketDao;
        this.ticketExporter = TicketExporter.getInstance(new PDFTicketExportStrategy()); // DEFAULT: PDF
    }

    public TicketExporter getTicketExporter() {
        return ticketExporter;
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

    public void export() {
        this.getCollection().forEach((idTicket, ticket) -> ticketExporter.exportTicket(ticket, "ticket_" + idTicket));
    }

    public void synchronize() {
        TreeMap<Integer, Ticket> map = this.ticketDao.getAll().stream()
                .collect(Collectors.toMap(Ticket::getTicketId, Function.identity(), (o1, o2) -> o1, TreeMap::new));
        this.setCollection(map);
    }
}
