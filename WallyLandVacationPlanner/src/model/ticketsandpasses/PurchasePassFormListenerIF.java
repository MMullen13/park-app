package model.ticketsandpasses;

import java.util.EventListener;

/**
 *
 * @author Ana
 */

public interface PurchasePassFormListenerIF extends EventListener {

    public void formEventOccured(PurchasePassEvent e);
    
    public void formEventOccured(PurchaseTicketEvent e);
}