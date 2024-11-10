/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.foodordering;

import java.util.ArrayList;
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
   private Menu menu;
   
   private Order currentOrder;
   private OrderHistory history;

   public FoodController() {
       
    }
   
    public FoodController(OrderViewForm orderView) {
        this.orderView = orderView;
        this.menu = new Menu();
        initializeMenus();
        
               
    }

    public ArrayList<Eatery> getEateries(){
        return menu.getEateries();
    }
    
    
    
    /**
     * Creates the default eateries and menus from model package
     */
    public void initializeMenus(){
        menu.createDefaults();
    }
    
    
    
    
    
    
    
    
   
   
}
