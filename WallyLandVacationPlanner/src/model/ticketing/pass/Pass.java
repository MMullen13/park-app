package model.ticketing.pass;

import java.util.stream.DoubleStream;

/**
 *
 * @author aniut
 */
public class Pass {

    private int silverPasses;
    private int goldPasses;
    private int platinumPasses;
    private static final int SILVER_PASS_PRICE = 100;
    private static final int GOLD_PASS_PRICE = 150;
    private static final int PLATINUM_PASS_PRICE = 200;
    private static final double PASS_TAXES = 0.7;

    public void addPass(String type, int count) {
        switch (type.toLowerCase()) {
            case "silver" -> silverPasses += count;
            case "gold" -> goldPasses += count;
            case "platinum" -> platinumPasses += count;
        }
    }

    public int getSilverPass() {
        return silverPasses;
    }

    public int getGoldPass() {
        return goldPasses;
    }

    public int getPlatinumPass() {
        return platinumPasses;
    }
    
    
        // Calculate price based on pass type and quantity
    public double calculatePrice(String passType, int quantity) {
        int pricePerPass;
        switch (passType.toLowerCase()) {
            case "silver" -> pricePerPass = SILVER_PASS_PRICE;
            case "gold" -> pricePerPass = GOLD_PASS_PRICE;
            case "platinum" -> pricePerPass = PLATINUM_PASS_PRICE;
            default -> throw new IllegalArgumentException("Invalid pass type: " + passType);
        }
        return pricePerPass * quantity * PASS_TAXES;
    }

    public double getPrice() {
        double totalPrice = 0;
        totalPrice += silverPasses * SILVER_PASS_PRICE;
        totalPrice += goldPasses * GOLD_PASS_PRICE;
        totalPrice += platinumPasses * PLATINUM_PASS_PRICE;
        return totalPrice * PASS_TAXES;  // Price with taxes
    }

    public int getQuantity() {
        return silverPasses + goldPasses + platinumPasses;  // Return total quantity of all passes
    }
}
