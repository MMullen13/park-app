package model.ticketsandpasses;

import java.util.EventObject;

/**
 *
 * @author Ana
 */
public class PurchaseTicketsEvent extends EventObject{

    private Ticket ticket;

    public PurchaseTicketsEvent(Object source, Ticket ticket) {
        super(source);
        
        this.ticket = ticket;
    }

    public int getPassPrice(String ticketType) {
        return ticket.getPriceForType(ticketType);
    }
    
    public double calcOnePassPriceWithTaxes(String ticketType){
        return ticket.calcPriceWithTaxes(ticketType);
    }
}

