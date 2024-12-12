package model.ticketsandpasses;

/**
 * Class representing a pass with various types (Silver, Gold, Platinum).
 * It extends the abstract class {@link PassAbs} and implements the methods to
 * get the price for a given pass type and calculate the price with taxes.
 * 
 * @author Ana
 */
public class Pass extends PassAbs {
    
    // Constant prices for different pass types
    private static final int SILVER_PASS_PRICE = 100;
    private static final int GOLD_PASS_PRICE = 150;
    private static final int PLATINUM_PASS_PRICE = 200;
    
    // Tax rate for the passes
    private static final double PASS_TAXES = 0.7;

    /**
     * Gets the price for a specific pass type.
     * 
     * @param passType The type of the pass (e.g., "silver", "gold", "platinum").
     * @return The price for the given pass type as an integer.
     */
    @Override
    public int getPriceForType(String passType) {
        int price = 0;
        
        switch (passType.toLowerCase()) {
            case "silver" -> price = SILVER_PASS_PRICE;
            case "gold" -> price = GOLD_PASS_PRICE;
            case "platinum" -> price = PLATINUM_PASS_PRICE;
        }
        
        return price;
    }
    
    /**
     * Calculates the price with taxes for a specific pass type.
     * 
     * @param passType The type of the pass (e.g., "silver", "gold", "platinum").
     * @return The calculated price including taxes as a double.
     */
    @Override
    public double calcPriceWithTaxes(String passType) {
        double total = 0;
        
        switch (passType.toLowerCase()) {
            case "silver" -> total = SILVER_PASS_PRICE + (SILVER_PASS_PRICE * PASS_TAXES);
            case "gold" -> total = GOLD_PASS_PRICE + (GOLD_PASS_PRICE * PASS_TAXES);
            case "platinum" -> total = PLATINUM_PASS_PRICE + (PLATINUM_PASS_PRICE * PASS_TAXES);
        }
        
        return total;
    }
}

