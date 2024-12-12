package model.ticketsandpasses;

/**
 * Abstract class representing a pass with methods to calculate prices.
 * The class provides abstract methods to get the price based on pass type and
 * to calculate the price with taxes for a given pass type.
 * 
 * @author Ana
 */
public abstract class PassAbs {

    /**
     * Gets the price for a specific pass type.
     * 
     * @param passType The type of the pass (e.g., "adult", "child").
     * @return The price for the given pass type as an integer.
     */
    public abstract int getPriceForType(String passType);

    /**
     * Calculates the price with taxes for a specific pass type.
     * 
     * @param passType The type of the pass (e.g., "adult", "child").
     * @return The calculated price including taxes as a double.
     */
    public abstract double calcPriceWithTaxes(String passType);
}
