/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.foodordering;

/**
 *
 * @author theme
 */
public class MenuItem {
    
    private String itemName;
    private double price;

    /**
     * MenuItem Constructor
     * @param itemName Name of the menu item
     * @param price price for the item
     */
    public MenuItem(String itemName, double price) {
        this.itemName = itemName;
        this.price = price;
    }

    /**
     * Gets the name of an item menu
     * @return itemName
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * Get the price of an item
     * @return item price
     */
    public double getPrice() {
        return price;
    }
    
    
    
    
    
}
