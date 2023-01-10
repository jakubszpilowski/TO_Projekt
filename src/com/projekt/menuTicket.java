package com.projekt;

import com.projekt.manager.TicketManager;

public class menuTicket {
    private final SystemInvoker system;
    private final TicketManager ticketManager;

    public menuTicket(SystemInvoker system, TicketManager ticketManager) {
        this.system = system;
        this.ticketManager = ticketManager;
    }

    public void show() {}
}
