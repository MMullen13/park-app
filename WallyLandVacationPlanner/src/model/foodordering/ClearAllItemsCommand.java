/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.foodordering;

import java.util.ArrayList;

/**
 * Concrete class for command pattern. Clears all of the items in the list
 * and saves a backup so the clear and be undone.
 * @author theme
 */
public class ClearAllItemsCommand implements OrderCommandIF {
    
    private Order order;
    private ArrayList<MenuItem> backupItems;
    private boolean undoCompleted = false; //Tracks if an undo command ran.

    /**
     * Class constructor
     * @param order the order that the command will execute on
     */
    public ClearAllItemsCommand(Order order) {
        this.order = order;
        
    }
    
    /**
     * Saves a list of all the items. Removes the entire list of menu items
     */
    @Override
    public void execute() {
        backupItems = order.clearAllItems();       
    }
    
    /**
     * Reverts the clear all items command that was last ran. Checks that the
     * list is not null and the the undo did not already run. Once ran, changes
     * the Boolean flag to true so it can not run again and clears the backup list.
     */
    @Override
    public void undo() {
        if (!undoCompleted && backupItems != null){
            for(MenuItem item : backupItems){
                order.addItem(item);            
            }
            
            undoCompleted = true;
        }
        backupItems.clear();  
    }
    
}
