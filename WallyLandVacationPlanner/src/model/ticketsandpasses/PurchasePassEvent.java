package model.ticketsandpasses;

import java.util.EventObject;

/**
 * Event class representing the purchase of a pass. This class extends
 * {@link EventObject} and contains information about the pass being purchased.
 * It provides methods to get the price and calculate the price with taxes for
 * the pass.
 *
 * @author Ana
 */
public class PurchasePassEvent extends EventObject {

    // The pass associated with the purchase event
    private Pass pass;

    /**
     * Constructs a PurchasePassEvent with the specified source and pass.
     *
     * @param source The source of the event (e.g., the form or listener that
     * triggered the event).
     * @param pass The pass being purchased.
     */
    public PurchasePassEvent(Object source, Pass pass) {
        super(source);
        this.pass = pass;
    }

    /**
     * Gets the price for a specific pass type.
     *
     * @param passType The type of the pass (e.g., "silver", "gold",
     * "platinum").
     * @return The price for the given pass type as an integer.
     */
    public int getPassPrice(String passType) {
        return pass.getPriceForType(passType);
    }

    /**
     * Calculates the price of the pass with taxes for a specific pass type.
     *
     * @param passType The type of the pass (e.g., "silver", "gold",
     * "platinum").
     * @return The calculated price including taxes as a double.
     */
    public double calcOnePassPriceWithTaxes(String passType) {
        return pass.calcPriceWithTaxes(passType);
    }
}
