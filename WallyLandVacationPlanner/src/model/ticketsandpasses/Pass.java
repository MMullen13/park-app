package model.ticketsandpasses;

/**
 *
 * @author aniut
 */
public class Pass extends PassAbs{
    private static final int SILVER_PASS_PRICE = 100;
    private static final int GOLD_PASS_PRICE = 150;
    private static final int PLATINUM_PASS_PRICE = 200;
    private static final double PASS_TAXES = 0.7;

    @Override
    public int getPriceForType(String passType) {
        int price = 0;
        
        switch(passType.toLowerCase()){
            case "silver" -> price = SILVER_PASS_PRICE;
            case "gold" -> price = GOLD_PASS_PRICE;
            case "platinum" -> price = PLATINUM_PASS_PRICE;
        }         
        return price;
    }
    
    @Override
    public double calcPriceWithTaxes(String passType){
        double total = 0;
        
        switch(passType.toLowerCase()){
            case "silver" -> total = SILVER_PASS_PRICE + (SILVER_PASS_PRICE * PASS_TAXES);
            case "gold" -> total = GOLD_PASS_PRICE  + (GOLD_PASS_PRICE * PASS_TAXES);
            case "platinum" -> total = PLATINUM_PASS_PRICE  + (PLATINUM_PASS_PRICE * PASS_TAXES);
        }
        return total;
    }
}
