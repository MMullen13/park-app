package model.ticketsandpasses;

import java.util.EventObject;

/**
 *
 * @author Ana
 */
public class PurchasePassEvent extends EventObject{

    private Pass pass;

    public PurchasePassEvent(Object source, Pass pass) {
        super(source);
        
        this.pass = pass;
    }

    public int getPassPrice(String passType) {
        return pass.getPriceForType(passType);
    }
    
    public double calcOnePassPriceWithTaxes(String passType){
        return pass.calcPriceWithTaxes(passType);
    }
}
