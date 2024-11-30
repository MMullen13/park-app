package model.ticketing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Ana
 */
public class TicketSubject {
    private final List<Observer> observers = new ArrayList<>();
    private final Map<String, Integer> tickets = new HashMap<>(); // Stores tickets

    
    public void addObservers(Observer observer){
        observers.add(observer);
    }
    
    public void removeObservers(Observer observer){
        observers.remove(observer);
    }
    
    public void notifyObservers(String message){
        for(Observer observer:  observers){
            observer.update(message);
        }
    }

    public void clearTickets() {
        tickets.clear(); // Clear the map
        notifyObservers("All tickets have been cleared."); // Notify observers
    }
    
    // Get a copy of tickets
    public Map<String, Integer> getTickets() {
        return new HashMap<>(tickets); // Return a shallow copy
    }

    // Add or update a ticket
    public void addOrUpdateTicket(String ticketName, int quantity) {
        if (quantity > 0) {
            tickets.put(ticketName, quantity);
            notifyObservers("Ticket updated: " + ticketName + " -> " + quantity);
        } else {
            tickets.remove(ticketName);
            notifyObservers("Ticket removed: " + ticketName);
        }
    }
}
