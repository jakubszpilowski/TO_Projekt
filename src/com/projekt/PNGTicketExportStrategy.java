package com.projekt;

import com.projekt.entity.Ticket;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

// Concrete strategy for exporting tickets in PNG format
public class PNGTicketExportStrategy implements TicketExportStrategy{
    // Function for exporting a Ticket object to a PNG file
    public void export(Ticket ticket, String filename) {
        // Set the size of the image
        int width = 500;
        int height = 200;

        // Create a buffered image
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // Get the graphics object for drawing on the image
        Graphics2D graphics = image.createGraphics();

        // Set the background color to white
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, width, height);

        // Set the font and color for drawing text
        graphics.setFont(new Font("Arial", Font.PLAIN, 16));
        graphics.setColor(Color.BLACK);

        // Draw the ticket information on the image
        graphics.drawString("Ticket Number: " + ticket.getTicketId(), 20, 30);
        graphics.drawString("Train Number: " + ticket.getTrain().getTrainNumber(), 20, 60);
        graphics.drawString("Departure: " + ticket.getTrain().getDepartureTime(), 20, 90);
        graphics.drawString("Arrival: " + ticket.getTrain().getArrivalTime(), 20, 120);


        // Save the image to a file
        File outputFile = new File(filename + ".png");
        try {
            ImageIO.write(image, "png", outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
