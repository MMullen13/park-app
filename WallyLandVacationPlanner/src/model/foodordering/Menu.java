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

    public Menu(ArrayList<MenuItem> menuItems, String menuName) {
        this.menuItems = menuItems;
        this.menuName = menuName;
    }
    
    public void addMenuItem(MenuItem item){
        
    }
    
    public void removeMenuItem(MenuItem item){
        
    }

    public ArrayList<MenuItem> getMenuItems() {
        return menuItems;
    }

    public String getMenuName() {
        return menuName;
    }
    
    
    
   
    
}
