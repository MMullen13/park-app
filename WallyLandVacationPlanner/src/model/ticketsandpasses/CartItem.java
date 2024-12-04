package model.ticketsandpasses;

/**
 *
 * @author aniut
 */
public class CartItem {
    private String type;
    private int quantity;
    private double price;

    public CartItem(String type, int quantity, double price) {
        this.type = type;
        this.quantity = quantity;
        this.price = price;
    }

    // Getters and setters
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "type: " + type + ", quantity: " + quantity + ", price: $" + price;
    }
}

