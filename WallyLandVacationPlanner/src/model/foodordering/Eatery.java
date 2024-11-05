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

    public Eatery(String eateryName, Menu menu) {
        this.eateryName = eateryName;
        this.menu = menu;
    }

    public String getEateryName() {
        return eateryName;
    }

    public Menu getMenu() {
        return menu;
    }
    
    
    
}
