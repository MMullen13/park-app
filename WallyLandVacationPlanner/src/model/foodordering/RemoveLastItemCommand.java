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
    private Stack<MenuItem>removedItemStack;

    /**
     * Class constructor. Sets the Order to work on
     * @param order Order number
     */
    public RemoveLastItemCommand(Order order) {
        this.order = order;
        this.removedItemStack = new Stack<>();
    }
    
    
    @Override
    public void execute() {
        MenuItem removedItem = order.deleteLastItem();
        if (removedItem != null){
            removedItemStack.push(removedItem);
        }
    }

    @Override
    public void undo() {
        if (!removedItemStack.isEmpty()){
            MenuItem restoreItem = removedItemStack.pop();
            order.addItem(restoreItem);
        }
    }
    
}
