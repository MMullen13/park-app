package model.ticketsandpasses;

/**
 * Class representing a ticket with various types (Child, Adult, Senior).
 * It extends the abstract class {@link PassAbs} and implements the methods to
 * get the price for a given ticket type and calculate the price with taxes.
 * 
 * @author Ana
 */
public class Ticket extends PassAbs {

    // Constant prices for different ticket types
    private static final int CHILD_PRICE = 25;
    private static final int ADULT_PRICE = 35;
    private static final int SENIOR_PRICE = 30;
    
    // Tax rate for the tickets
    private static final double PASS_TAXES = 0.7;

    /**
     * Gets the price for a specific ticket type.
     * 
     * @param passType The type of the ticket (e.g., "child", "adult", "senior").
     * @return The price for the given ticket type as an integer.
     */
    @Override
    public int getPriceForType(String passType) {
        int price = 0;
        
        switch (passType.toLowerCase()) {
            case "child" -> price = CHILD_PRICE;
            case "adult" -> price = ADULT_PRICE;
            case "senior" -> price = SENIOR_PRICE;
        }
        
        return price;
    }
    
    /**
     * Calculates the price with taxes for a specific ticket type.
     * 
     * @param passType The type of the ticket (e.g., "child", "adult", "senior").
     * @return The calculated price including taxes as a double.
     */
    @Override
    public double calcPriceWithTaxes(String passType) {
        double total = 0;
        
        switch (passType.toLowerCase()) {
            case "child" -> total = CHILD_PRICE + (CHILD_PRICE * PASS_TAXES);
            case "adult" -> total = ADULT_PRICE + (ADULT_PRICE * PASS_TAXES);
            case "senior" -> total = SENIOR_PRICE + (SENIOR_PRICE * PASS_TAXES);
        }
        
        return total;
    }
}

