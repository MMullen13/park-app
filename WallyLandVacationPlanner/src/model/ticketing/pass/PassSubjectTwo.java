package model.ticketing.pass;

import java.util.*;
import model.ticketing.ObserverIF;

/**
 *
 * @author aniut
 */
public class PassSubjectTwo {
    private final List<ObserverIF> observers = new ArrayList<>();
    private final Map<String, Pass> passMap = new HashMap<>();

    public PassSubjectTwo() {
        // Initialize the map with pass types
        passMap.put("silver", new Pass());
        passMap.put("gold", new Pass());
        passMap.put("platinum", new Pass());
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

    // Add passes and notify observers
    public void addPasses(String type, int quantity) {
        Pass pass = passMap.get(type.toLowerCase());
        if (pass != null) {
            pass.addPass(type, quantity);
            double totalPrice = pass.calculatePrice(type, quantity);

            String message = String.format("%d %s pass(es) added. Total Price: $%.2f", 
                                            quantity, type, totalPrice);
            notifyObservers(message);
        } else {
            System.err.println("Invalid pass type: " + type);
        }
    }

    // Retrieve the total count for a pass type
    public int getPassCount(String type) {
        Pass pass = passMap.get(type.toLowerCase());
        if (pass != null) {
            return switch (type.toLowerCase()) {
                case "silver" -> pass.getSilverPass();
                case "gold" -> pass.getGoldPass();
                case "platinum" -> pass.getPlatinumPass();
                default -> 0;
            };
        }
        return 0;
    }

    // Get all passes in the map
    public Map<String, Pass> getAllPasses() {
        return passMap;
    }
}
