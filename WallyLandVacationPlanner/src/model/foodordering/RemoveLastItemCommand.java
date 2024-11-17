/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.foodordering;

/**
 * Concrete Remove Item Class for Command Pattern. Manages the details for the 
 * last item that was removed from the order.
 * @author theme
 */
public class RemoveLastItemCommand implements OrderCommandIF {
    
    private Order order;
    private MenuItem removedItem;
    private boolean undoCompleted = false;

    /**
     * Class constructor. Sets the Order to work on
     * @param order Current Order to execute commands on
     */
    public RemoveLastItemCommand(Order order) {
        this.order = order;
       
    }
    
    /**
     * Removes the last item that was added to the order and saves the removed 
     * menu item.
     */
    @Override
    public void execute() {
        removedItem = order.deleteLastItem(); //saves the item that was deleted for undo reference
    }

    /**
     * Undoes the command. Re-adds the menu item to the order and changes the 
     * Boolean flag set to true if the undo is ran successfully
     */
    @Override
    public void undo() {
        if (!undoCompleted && removedItem != null){
            order.addItem(removedItem);
            undoCompleted = true;
        }
    }

    /**
     * Gets the removed item from when the command was executed
     * @return The removed menu item
     */
    public MenuItem getRemovedItem() {
        return removedItem;
    }
    
    
    
}
