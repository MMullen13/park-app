/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.foodordering;

import java.util.ArrayList;

/**
 *
 * @author theme
 */
public class Menu {
    
    private ArrayList<MenuItem> menuItems;
    private String menuName;

    /**
     * Constructor. Creates instance of the class
     * @param menuItems ArrayList of items on the menu
     * @param menuName name of the eatery menu
     */
    public Menu(ArrayList<MenuItem> menuItems, String menuName) {
        this.menuItems = menuItems;
        this.menuName = menuName;
    }
    
    /**
     * Adds and Item to the menu
     * @param item item to be added
     */
    public void addMenuItem(MenuItem item){
        menuItems.add(item);
    }
    
    /**
     * Removes item from the menu
     * @param item item to be removed
     */
    public void removeMenuItem(MenuItem item){
        menuItems.remove(item);
    }

    /**
     * Returns a list of items on the menu
     * @return 
     */
    public ArrayList<MenuItem> getMenuItems() {
        return menuItems;
    }

    /**
     * Gets the Menu Name
     * @return menu name
     */
    public String getMenuName() {
        return menuName;
    }
    
    
    
   
    
}
