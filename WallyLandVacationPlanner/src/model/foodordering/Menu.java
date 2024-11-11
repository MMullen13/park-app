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
    
    private ArrayList<MenuItem> menuItems = new ArrayList<>();
    private String menuName;
    private ArrayList<Eatery> eateries = new ArrayList<>();

    /**
     * Constructor. Creates instance of the class
     * @param menuName name of the eatery menu
     */
    public Menu(String menuName) {
        this.menuName = menuName;
    }
    
    /**
     * Empty Constructor
     */
    public Menu(){
        
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
    
    public void createDefaults(){
        Menu bistroMenu = new Menu("Bistro Bella");
        menuItems.addAll(MenuItem.getApps("Bistro Bella"));
        menuItems.addAll(MenuItem.getDrinks("Bistro Bella"));
        menuItems.addAll(MenuItem.getMains("Bistro Bella"));
        menuItems.addAll(MenuItem.getSides("Bistro Bella"));
        menuItems.addAll(MenuItem.getDesserts("Bistro Bella"));
        
        Eatery bistroBella = new Eatery("Bistro Bella", bistroMenu);
        eateries.add(bistroBella);
        
        Menu grillMenu = new Menu("Grill & Chill");
        menuItems.addAll(MenuItem.getApps("Grill & Chill"));
        menuItems.addAll(MenuItem.getDrinks("Grill & Chill"));
        menuItems.addAll(MenuItem.getMains("Grill & Chill"));
        menuItems.addAll(MenuItem.getSides("Grill & Chill"));
        menuItems.addAll(MenuItem.getDesserts("Grill & Chill"));
        
        Eatery grillChill = new Eatery("Grill & Chill", grillMenu);
        eateries.add(grillChill);
        
        Menu chickenlMenu = new Menu("Chicken Kitchen");
        menuItems.addAll(MenuItem.getApps("Chicken Kitchen"));
        menuItems.addAll(MenuItem.getDrinks("Chicken Kitchen"));
        menuItems.addAll(MenuItem.getMains("Chicken Kitchen"));
        menuItems.addAll(MenuItem.getSides("Chicken Kitchen"));
        menuItems.addAll(MenuItem.getDesserts("Chicken Kitchen"));
        
        Eatery chickenKitchen = new Eatery("Chicken Kitchen", grillMenu);
        eateries.add(chickenKitchen);
        
    }
    
    

    /**
     * Returns a list of items on the menu
     * @return 
     */
    public ArrayList<MenuItem> getMenuItems() {
        return menuItems;
    }
    
    public ArrayList<Eatery> getEateries(){
        return eateries;
    }

    /**
     * Gets the Menu Name
     * @return menu name
     */
    public String getMenuName() {
        return menuName;
    }
    
    
    
   
    
}
