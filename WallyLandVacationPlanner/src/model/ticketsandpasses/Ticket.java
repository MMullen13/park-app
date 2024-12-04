package model.ticketsandpasses;

/**
 *
 * @author Ana
 */
public class Ticket extends PassAbs{
    private static final int CHILD_PRICE = 100;
    private static final int ADULT_PRICE = 150;
    private static final int SENIOR_PRICE = 200;
    private static final double PASS_TAXES = 0.7;


    @Override
    public int getPriceForType(String passType) {
        int price = 0;
        
        switch(passType.toLowerCase()){
            case "child" -> price = CHILD_PRICE;
            case "adult" -> price = ADULT_PRICE;
            case "senior" -> price = SENIOR_PRICE;
        }         
        return price;
    }
    
    @Override
    public double calcPriceWithTaxes(String passType){
        double total = 0;
        
        switch(passType.toLowerCase()){
            case "child" -> total = CHILD_PRICE + (CHILD_PRICE * PASS_TAXES);
            case "adult" -> total = ADULT_PRICE  + (ADULT_PRICE * PASS_TAXES);
            case "senior" -> total = SENIOR_PRICE  + (SENIOR_PRICE * PASS_TAXES);
        }
        return total;
    }
}
