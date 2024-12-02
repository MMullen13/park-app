package model.ticketing.ticket;

import model.ticketing.ticket.Ticket;
import java.util.ArrayList;
import java.util.List;
import model.ticketing.ObserverIF;
import model.ticketing.ObserverIF;

/**
 *
 * @author Ana
 */
public class TicketSubject {
    private final List<ObserverIF> observers = new ArrayList<>();
    private final Ticket ticket; // Reference to Ticket model

    
    public TicketSubject(Ticket ticket) {
        this.ticket = ticket;
    }

    public void addObserver(ObserverIF observer) {
        observers.add(observer);
    }

    public void removeObserver(ObserverIF observer) {
        observers.remove(observer);
    }

    public void notifyObservers(String message) {
        for (ObserverIF observer : observers) {
            observer.update(message);
        }
    }

    // Add tickets and notify observers
    public void addTickets(String type, int quantity) {
        ticket.addTickets(type, quantity);
        notifyObservers(quantity + " " + type + " ticket(s) added. Total: " + getTicketCount(type));
    }

    // Retrieve ticket counts by type
    public int getTicketCount(String type) {
        return switch (type.toLowerCase()) {
            case "child" -> ticket.getChildTickets();
            case "adult" -> ticket.getAdultTickets();
            case "senior" -> ticket.getSeniorTickets();
            default -> 0;
        };
    }
}
