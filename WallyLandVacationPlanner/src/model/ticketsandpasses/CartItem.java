package model.ticketsandpasses;

/**
 * Represents an item in the shopping cart.
 * This class stores the type, quantity, and price of a cart item and provides methods to access and modify these properties.
 * It also includes a method to return a string representation of the cart item.
 * 
 * @author aniut
 */
public class CartItem {
    
    // The type of the cart item (e.g., "silver pass", "child ticket")
    private String type;

    // The quantity of the cart item
    private int quantity;

    // The price of a single unit of the cart item
    private double price;

    /**
     * Constructs a CartItem with the specified type, quantity, and price.
     * 
     * @param type The type of the cart item.
     * @param quantity The quantity of the item.
     * @param price The price of a single unit of the item.
     */
    public CartItem(String type, int quantity, double price) {
        this.type = type;
        this.quantity = quantity;
        this.price = price;
    }

    // Getter and setter methods

    /**
     * Gets the type of the cart item.
     * 
     * @return The type of the cart item.
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type of the cart item.
     * 
     * @param type The type of the cart item.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets the quantity of the cart item.
     * 
     * @return The quantity of the cart item.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of the cart item.
     * 
     * @param quantity The quantity of the cart item.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Gets the price of a single unit of the cart item.
     * 
     * @return The price of a single unit of the cart item.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price of a single unit of the cart item.
     * 
     * @param price The price of a single unit of the cart item.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Returns a string representation of the cart item, including its type, quantity, and price.
     * 
     * @return A string representing the cart item in the format: "type: [type], quantity: [quantity], price: $[price]".
     */
    @Override
    public String toString() {
        return "type: " + type + ", quantity: " + quantity + ", price: $" + price;
    }
}


