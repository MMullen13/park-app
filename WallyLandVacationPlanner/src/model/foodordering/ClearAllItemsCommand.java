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
public class ClearAllItemsCommand implements OrderCommandIF {
    
    private Order order;
    private ArrayList<MenuItem> restoreAll;

    public ClearAllItemsCommand(Order order) {
        this.order = order;
        this.restoreAll = new ArrayList<>();
    }
    

    @Override
    public void execute() {
        restoreAll = order.clearAllItems();
               
    }

    @Override
    public void undo() {
        for(MenuItem items : restoreAll){
            order.addItem(items);            
        }
    }
    
}
