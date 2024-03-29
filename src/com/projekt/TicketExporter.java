package com.projekt;

import com.projekt.entity.Ticket;
import com.projekt.strategy.TicketExportStrategy;

// Class responsible for exporting tickets using a specific strategy
public class TicketExporter {
    private static TicketExporter instance;
    // The current export strategy being used
    private TicketExportStrategy exportStrategy;

    // Constructor that initializes the exporter with a specific export strategy
    private TicketExporter(TicketExportStrategy exportStrategy) {
        this.exportStrategy = exportStrategy;
    }

    public static TicketExporter getInstance(TicketExportStrategy exportStrategy) {
        if (instance == null) {
            instance = new TicketExporter(exportStrategy);
        }
        return instance;
    }

    // Method for changing the export strategy
    public void setExportStrategy(TicketExportStrategy exportStrategy) {
        this.exportStrategy = exportStrategy;
    }

    // Method for exporting a ticket using the current export strategy
    public void exportTicket(Ticket ticket, String filename) {
        exportStrategy.export(ticket, filename);
    }
}