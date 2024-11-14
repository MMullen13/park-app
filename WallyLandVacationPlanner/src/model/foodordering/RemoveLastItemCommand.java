/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.foodordering;

import java.util.Stack;

/**
 *
 * @author theme
 */
public class RemoveLastItemCommand implements OrderCommandIF {
    
    private Order order;
    private MenuItem removedItem;
    private boolean undoCompleted = false;

    /**
     * Class constructor. Sets the Order to work on
     * @param order Order number
     */
    public RemoveLastItemCommand(Order order) {
        this.order = order;
       
    }
    
    
    @Override
    public void execute() {
        removedItem = order.deleteLastItem();
    }

    @Override
    public void undo() {
        if (!undoCompleted && removedItem != null){
            order.addItem(removedItem);
            undoCompleted = true;
        }
    }
    
}
