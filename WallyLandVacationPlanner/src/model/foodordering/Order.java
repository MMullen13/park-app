/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.foodordering;

import java.util.ArrayList;
import java.util.Random;

/**
 *
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

    public Order() {
    }
    
    

    /**
     * Class Constructor. Generates random Order ID. Creates Array of Menu Items
     * @param eatery  
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
     * Works with the command class to undo additions to an order
     * @return the last item removed for undo functionality
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
     * Works with command class to remove all items from an order to start over
     * @return an ArrayList of the order items to use with undo command
     */
    public ArrayList<MenuItem> clearAllItems(){
        ArrayList<MenuItem> removedItems = new ArrayList<>(orderItems);
        orderItems.clear();
        return removedItems;        
    }

    /**
     * Calculates the total of the order based on the items in the orderItems array
     * @param quantity
     * @param price
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
    
    public double getTotal(){
        return total * 1.07;
    }

    public void setEatery(Eatery eatery) {
        this.eatery = eatery;
    }

    public String getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(String pickupDate) {
        this.pickupDate = pickupDate;
    }

    public ArrayList<MenuItem> getOrderItems() {
        return orderItems;
    }

    public MenuItem getLastItemRemoved() {
        return lastItemRemoved;
    }
    
    
    
    
    
    
    

    
    
    
    
    
    
    
}
