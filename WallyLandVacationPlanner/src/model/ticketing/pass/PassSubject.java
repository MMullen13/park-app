package model.ticketing.pass;

import model.ticketing.pass.Pass;
import java.util.ArrayList;
import java.util.List;
import model.ticketing.ObserverIF;

/**
 *
 * @author Ana
 */
public class PassSubject {
    private final List<ObserverIF> observers = new ArrayList<>();
    private final Pass pass; // Reference to Pass model

    
    public PassSubject(Pass pass) {
        this.pass = pass;
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
    public void addPasses(String type, int quantity) {
        pass.addPass(type, quantity);
        notifyObservers(quantity + " " + type + " pass(es) added. Total: " + getPassCount(type));
    }

    // Retrieve passes counts by type
    public int getPassCount(String type) {
        return switch (type.toLowerCase()) {
            case "silver" -> pass.getSilverPass();
            case "gold" -> pass.getGoldPass();
            case "platinum" -> pass.getPlatinumPass();
            default -> 0;
        };
    }
}
