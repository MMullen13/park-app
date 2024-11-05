/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.foodordering;

/**
 *
 * @author theme
 */
public class Eatery {
    
    private String eateryName;
    private Menu menu;

    /**
     * Eatery Constructor
     * @param eateryName Name of the eatery
     * @param menu the menu for the Eatery
     */
    public Eatery(String eateryName, Menu menu) {
        this.eateryName = eateryName;
        this.menu = menu;
    }

    /**
     * Gets the eatery name
     * @return eatery name
     */
    public String getEateryName() {
        return eateryName;
    }

    /**
     * Gets the menu for the eatery
     * @return the eatery menu
     */
    public Menu getMenu() {
        return menu;
    }
    
    
    
}
