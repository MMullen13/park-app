/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.foodordering;

import java.io.Serializable;

/**
 *
 * @author theme
 */
public class Eatery implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private String eateryName;
    private Menu menu;

    /**
     * Creates an eatery without an attached menu
     * @param eateryName name of the eatery to create
     */
    public Eatery(String eateryName) {
        this.eateryName = eateryName;
    }

    
    /**
     * Creates an eatery with a predefined menu
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

    @Override
    public String toString() {
        return eateryName;
    }
    
    
    
    
    
}
