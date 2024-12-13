package model.ticketsandpasses;

import java.util.EventObject;

/**
 * Event class representing the purchase of a ticket.
 * This class extends {@link EventObject} and contains information about the ticket being purchased.
 * It provides methods to get the price and calculate the price with taxes for the ticket.
 * 
 * @author Ana
 */
public class PurchaseTicketEvent extends EventObject {

    // The ticket associated with the purchase event
    private Ticket ticket;

    /**
     * Constructs a PurchaseTicketEvent with the specified source and ticket.
     * 
     * @param source The source of the event (e.g., the form or listener that triggered the event).
     * @param ticket The ticket being purchased.
     */
    public PurchaseTicketEvent(Object source, Ticket ticket) {
        super(source);
        this.ticket = ticket;
    }

    /**
     * Gets the price for a specific ticket type.
     * 
     * @param passType The type of the ticket (e.g., "child", "adult", "senior").
     * @return The price for the given ticket type as an integer.
     */
    public int getPassPrice(String passType) {
        return ticket.getPriceForType(passType);
    }

    /**
     * Calculates the price of the ticket with taxes for a specific ticket type.
     * 
     * @param passType The type of the ticket (e.g., "child", "adult", "senior").
     * @return The calculated price including taxes as a double.
     */
    public double calcOnePassPriceWithTaxes(String passType) {
        return ticket.calcPriceWithTaxes(passType);
    }
}
