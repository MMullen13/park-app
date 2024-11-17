/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.foodordering;

import java.util.ArrayList;
import java.util.Random;

/**
 * Creates and manages order Items for the application
 * @author theme
 */
public class Order implements OrderIF {
    
    private String orderID;
    private Eatery eatery;
    private Menu menu;
    private String pickupTime;
    private String pickupDate;
    private ArrayList<MenuItem> orderItems;
    private double total;
    MenuItem lastItemRemoved;

    /**
     * Creates an order without a defined eatery
     */
    public Order() {
    }
    
   
    /**
     * Class Constructor. Generates random Order ID. Creates Array of Menu Items
     * @param eatery The eatery that the order will be created for. 
     */
    public Order(Eatery eatery) {
        this.orderID = generateOrderID(7);
        this.eatery = eatery;
        this.orderItems = new ArrayList<>();
    }
    
    
    /**
     * Helper method to create a random order id.
     * @param length How long the id should be
     * @return the random order id string
     */
    private String generateOrderID(int length){
        String variables = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder randomID = new StringBuilder();
        Random random = new Random();
        
        for(int i = 0; i< length; i++){
            randomID.append(variables.charAt(random.nextInt(variables.length())));
        }
        return randomID.toString();
    }
    
    /**
     * Deletes the last item that was added to the order. Allows command pattern
     * implementation.
     * @return the last item removed or null if no item was removed.
     */
    public MenuItem deleteLastItem(){
        System.out.println(orderItems);
        if (!orderItems.isEmpty()){
            lastItemRemoved = orderItems.get(orderItems.size()-1);
            orderItems.remove(orderItems.size() - 1);
            System.out.println(orderItems);
            return lastItemRemoved;
            
        }
        else{
            System.out.println("No Items to remove");
            return null;
        }
    }
    
    /**
     * remove all items from an order. Allows command pattern implementation
     * @return an ArrayList of the order items that were removed.
     */
    public ArrayList<MenuItem> clearAllItems(){
        ArrayList<MenuItem> removedItems = new ArrayList<>(orderItems);
        orderItems.clear();
        return removedItems;        
    }

    /**
     * Calculates the total of the order based on the items in the orderItems array
     * @param quantity Number of the items
     * @param price Price of the item
     */
    @Override
    public void calculateTotal(int quantity, double price) {
        double orderTotal = quantity * price;
        total += orderTotal;
        
    }

    /**
     * Adds an item to the order
     * @param item Item to be added
     */
    @Override
    public void addItem(MenuItem item) {
        orderItems.add(item);
    }

    /**
     * Removes an item from the order. 
     * @param item Item to be removed. 
     */
    @Override
    public void removeItem(MenuItem item) {
        if (orderItems.isEmpty()){
               System.out.println("Nothing to Remove");         
        }
        else{
            orderItems.remove(item);
        }
    }
    
    /**
     * Resets the total of the order to 0.00
     */
    public void resetTotal(){
        total = 0.00;
    }

    /**
     * Gets the order pickup time
     * @return Order pickup time
     */
    @Override
    public String getPickupTime() {
        return pickupTime;
    }
    

    /**
     * gets the menu associated with the order
     * @return Order Menu
     */
    @Override
    public Menu getMenu() {
        return menu;
    }

    /**
     * Gets the randomly generated orderID
     * @return Order ID
     */
    @Override
    public String getOrderID() {
        return orderID;
    }

    /**
     * Gets the eatery associated with the order
     * @return Eatery Name
     */
    @Override
    public Eatery getEatery() {
        return eatery;
    }
    
    /**
     * Sets the pickup time chosen by user
     * @param pickupTime Desired Pickup Time
     */
    public void setPickupTime(String pickupTime){
        this.pickupTime = pickupTime;
    }
    
    /**
     * gets the total of the order with tax applied.
     * @return Total cost of order with tax
     */
    public double getTotal(){
        return total * 1.07;
    }

    /**
     * Sets the eatery for the order that the user has chosen.
     * @param eatery Name of the eatery to add to the order.
     */
    public void setEatery(Eatery eatery) {
        this.eatery = eatery;
    }

    /**
     * Gets the scheduled pickup time for the order
     * @return Pickup time as a string
     */
    public String getPickupDate() {
        return pickupDate;
    }

    /**
     * Sets the date of the order
     * @param pickupDate Date the order is created for
     */
    public void setPickupDate(String pickupDate) {
        this.pickupDate = pickupDate;
    }

    /**
     * Gets the list of menu items that were added to the order
     * @return An ArrayList of menu items currently in the order
     */
    public ArrayList<MenuItem> getOrderItems() {
        return orderItems;
    }

    /**
     * Gets the last item that was removed from the order.
     * @return The last removed Menu item
     */
    public MenuItem getLastItemRemoved() {
        return lastItemRemoved;
    }
    
    
    
    
    
    
    

    
    
    
    
    
    
    
}
