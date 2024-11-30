package model.ticketing;

/**
 *
 * @author Ana
 */
public class PurchaseFormEvent implements PurchaseFormEventIF{
    private String ticketType;
    private int quantity;
    private double price;

    public PurchaseFormEvent(String ticketType, int quantity, double price) {
        this.ticketType = ticketType;
        this.quantity = quantity;
        this.price = price;
    }

    @Override
    public String getTicketType() {
        return ticketType;
    }

    @Override
    public int getQuantity() {
        return quantity;
    }

    @Override
    public double getPrice() {
        return price;
    }
}
