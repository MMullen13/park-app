package model.ticketsandpasses;

import java.util.EventListener;

/**
 * Interface for listeners to handle events related to purchasing passes and tickets.
 * This interface defines methods to handle events when a pass or ticket purchase form is submitted.
 * Implementing classes should define how to handle the events for pass and ticket purchases.
 * 
 * @author Ana
 */
public interface PurchasePassFormListenerIF extends EventListener {

    /**
     * Handles the event when a purchase pass form is submitted.
     * 
     * @param e The {@link PurchasePassEvent} containing the details of the pass purchase event.
     */
    public void formEventOccured(PurchasePassEvent e);

    /**
     * Handles the event when a purchase ticket form is submitted.
     * 
     * @param e The {@link PurchaseTicketEvent} containing the details of the ticket purchase event.
     */
    public void formEventOccured(PurchaseTicketEvent e);
}
