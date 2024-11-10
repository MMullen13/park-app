/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.foodordering;

import model.foodordering.*;
import view.foodordering.*;

/**
 *
 * @author theme
 */
public class FoodController {
    
   private FoodOrderingModel model;
   private OrderViewForm orderView;
   private OrderConfirmationViewForm confirmationView;
   
   private Order currentOrder;
   private OrderHistory history;

    public FoodController(OrderViewForm orderView) {
        this.orderView = orderView;
        initializeMenus();
               
    }
    
    /**
     * Creates the default eateries and menus from model package
     */
    public void initializeMenus(){
        Menu menu = new Menu();
        menu.createDefaults();
    }
    
    
    
    
    
    
   
   
}
