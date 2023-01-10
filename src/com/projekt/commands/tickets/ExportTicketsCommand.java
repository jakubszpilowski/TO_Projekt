package com.projekt.commands.tickets;

import com.projekt.commands.Command;
import com.projekt.manager.TicketManager;
import com.projekt.strategy.PDFTicketExportStrategy;
import com.projekt.strategy.TicketExportStrategy;

public class ExportTicketsCommand implements Command {
    private final TicketManager ticketManager;
    private final TicketExportStrategy exportStrategy;

    public ExportTicketsCommand(TicketManager ticketManager) {
        this.ticketManager = ticketManager;
        this.exportStrategy = new PDFTicketExportStrategy(); // PDF - DEFAULT EXPORT STRATEGY
    }

    public ExportTicketsCommand(TicketManager ticketManager, TicketExportStrategy exportStrategy) {
        this.ticketManager = ticketManager;
        this.exportStrategy = exportStrategy;
    }

    @Override
    public void execute() {
        ticketManager.export(exportStrategy);
    }

    @Override
    public void undo() {
        // undoable
    }
}
