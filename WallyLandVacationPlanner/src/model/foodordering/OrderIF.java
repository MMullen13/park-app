/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package model.foodordering;

/**
 *
 * @author theme
 */
public interface OrderIF {
    
    /**
     * Calculate the order total. Include all taxes and fees
     * @return total cost of order
     */
    public double calculateTotal();
    
    /**
     * Adds Item to the order
     * @param item item to be added
     */
    public void addItem(MenuItem item);
    
    /**
     * Removes item from the order
     * @param item item to be removed
     */
    public void removeItem(MenuItem item);
    
    /**
     * Gets the scheduled pickup time. Format 00:00. 24 Hour Clock
     * @return time order needs to be picked up
     */
    public String getPickupTime();
    
    /**
     * Menu for the eatery
     * @return The eatery menu
     */
    public Menu getMenu();
    
    /**
     * Order's ID
     * @return Order Id
     */
    public String getOrderID();
    
    /**
     * Eatery for order
     * @return Eatery name
     */
    public Eatery getEatery();
    
    
}
