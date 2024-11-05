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
    private ArrayList<MenuItem> orderItems;

    /**
     * Class Constructor. Generates random Order ID. Creates Array of Menu Items
     * @param eatery 
     * @param menu 
     */
    public Order(Eatery eatery, Menu menu) {
        this.orderID = generateOrderID(7);
        this.eatery = eatery;
        this.menu = menu;
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
     * Calculates the total of the order based on the items in the orderItems array
     * @return the total including 7% sales tax.
     */
    @Override
    public double calculateTotal() {
        double total = 0.0;
        for (MenuItem item : orderItems){
            total += item.getPrice();
        }
        total = (total * 0.07);
        return total;
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

    
    
    
    
    
    
    
}
