package model.ticketsandpasses;

import java.util.EventObject;

/**
 *
 * @author Ana
 */
public class PurchaseTicketEvent extends EventObject{

    private Ticket ticket;

    public PurchaseTicketEvent(Object source, Ticket ticket) {
        super(source);
        
        this.ticket = ticket;
    }

    public int getPassPrice(String passType) {
        return ticket.getPriceForType(passType);
    }
    
    public double calcOnePassPriceWithTaxes(String passType){
        return ticket.calcPriceWithTaxes(passType);
    }
}
