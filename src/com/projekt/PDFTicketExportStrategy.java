package com.projekt;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import com.projekt.entity.Ticket;

// Concrete strategy for exporting tickets in PDF format
public class PDFTicketExportStrategy implements TicketExportStrategy {
    public void export(Ticket ticket, String filename) {
        // Implementation for exporting a ticket in PDF format
        try {
            // create a new PDF document
            Document document = new Document();
            // create a file for saving the document
            PdfWriter.getInstance(document, new FileOutputStream(filename));
            // open the document for editing
            document.open();

            // add a header to the document
            document.add(new Paragraph("Train ticket"));
            // add a line break
            document.add(new Paragraph(" "));

            // create a table with ticket information
            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100);
            table.addCell(" Train name");
            table.addCell(ticket.getTrain().getTrainType());
            table.addCell(" Departure station");
            table.addCell(ticket.getTrain().getFrom());
            table.addCell(" Arrival station");
            table.addCell(ticket.getTrain().getTo());
            table.addCell(" Departure time");
            table.addCell(ticket.getTrain().getDepartureTime().toString());
            table.addCell(" Arrival time");
            table.addCell(ticket.getTrain().getArrivalTime().toString());
            table.addCell(" Ticket number");
            table.addCell(ticket.getTicketId() + "");
            table.addCell(" Price");
            table.addCell(ticket.getPrice() + " PLN");

            // add the table to the document
            document.add(table);

            // close the document
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
