package model.ticketsandpasses;

/**
 *
 * @author Ana
 */
public abstract class PassAbs {

    public abstract int getPriceForType(String passType);
    
    public abstract double calcPriceWithTaxes(String passType);
}