package model.ticketing.ticket;

/**
 *
 * @author Ana
 */
public class Ticket {
    private int childTickets;
    private int adultTickets;
    private int seniorTickets;
    private static final int CHILD_PRICE = 100;
    private static final int ADULT_PRICE = 150;
    private static final int SENIOR_PRICE = 200;
    private static final double PASS_TAXES = 0.7;

    public void addTickets(String type, int count) {
        switch (type.toLowerCase()) {
            case "child" -> childTickets += count;
            case "adult" -> adultTickets += count;
            case "senior" -> seniorTickets += count;
        }
    }

    public int getChildTickets() {
        return childTickets;
    }

    public int getAdultTickets() {
        return adultTickets;
    }

    public int getSeniorTickets() {
        return seniorTickets;
    }
    
    // Calculate price based on ticket type and quantity
    public double calculatePrice(String passType, int quantity) {
        int pricePerPass;
        switch (passType.toLowerCase()) {
            case "silver" -> pricePerPass = CHILD_PRICE;
            case "gold" -> pricePerPass = ADULT_PRICE;
            case "platinum" -> pricePerPass = SENIOR_PRICE;
            default -> throw new IllegalArgumentException("Invalid pass type: " + passType);
        }
        return pricePerPass * quantity * PASS_TAXES;
    }
}
