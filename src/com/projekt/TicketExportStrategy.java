package com.projekt;

import com.projekt.entity.Ticket;

// Interface for ticket export strategies
public interface TicketExportStrategy {
    // Method for exporting a ticket in a specific format
    void export(Ticket ticket, String filename);
}
