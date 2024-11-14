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
    private ArrayList<MenuItem> backupItems;
    private boolean undoCompleted = false;

    public ClearAllItemsCommand(Order order) {
        this.order = order;
        
    }
    

    @Override
    public void execute() {
        backupItems = order.clearAllItems();
               
    }

    @Override
    public void undo() {
        if (!undoCompleted && backupItems != null){
            for(MenuItem item : backupItems){
                order.addItem(item);            
            }
            
            undoCompleted = true;
            System.out.println(undoCompleted);
        }
        backupItems.clear();
        
        
    }
    
}
