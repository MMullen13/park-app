/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.foodordering;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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
   private MenuItem menuItem;
   private ArrayList<Eatery> eateries;
   
   private Order currentOrder;
   private OrderHistory history;

   public FoodController() {
       
    }
   
    public FoodController(OrderViewForm orderView) {
        this.orderView = orderView;
        this.menu = new Menu();
        this.menuItem = new MenuItem();
        this.eateries = menu.getEateries();
        initializeMenus();
        
               
    }

    public ArrayList<Eatery> getEateries(){
        return menu.getEateries();
    }
    
    public Map<String, ArrayList<MenuItem>> getMenuItems(String eateryName){
        
        Map<String, ArrayList<MenuItem>> itemByCategory = new HashMap<>();
        
        itemByCategory.put("Drinks", new ArrayList<>());
        itemByCategory.put("Appitizers", new ArrayList<>());
        itemByCategory.put("Mains", new ArrayList<>());
        itemByCategory.put("Sides", new ArrayList<>());
        itemByCategory.put("Desserts", new ArrayList<>());
        
        for(Eatery eatery : eateries){
            if(eatery.getEateryName().equals(eateryName)){
                Menu menu = eatery.getMenu();
                itemByCategory.get("Drinks").addAll(MenuItem.getDrinks(eateryName));
                itemByCategory.get("Appitizers").addAll(MenuItem.getApps(eateryName));
                itemByCategory.get("Mains").addAll(MenuItem.getMains(eateryName));
                itemByCategory.get("Sides").addAll(MenuItem.getSides(eateryName));
                itemByCategory.get("Desserts").addAll(MenuItem.getDesserts(eateryName));
                break;
            }
        }
        return itemByCategory;
        
    }
    
    
    
    /**
     * Creates the default eateries and menus from model package
     */
    public void initializeMenus(){
        menu.createDefaults();
    }
    
    
    
    
    
    
    
    
   
   
}
