/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.foodordering;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author theme
 */
public class RemoveLastItemCommandTests {
    
    private Order order;
    private RemoveLastItemCommand command;
    private MenuItem item1;
    private MenuItem item2;

    @Before
    public void setUp() {
        order = new Order();
        item1 = new MenuItem("Burger", 5.99, 1);
        item2 = new MenuItem("Fries", 2.99, 1);
        order.addItem(item1);
        order.addItem(item2);
        command = new RemoveLastItemCommand(order);
    }

    @Test
    public void testExecute() {
        
        command.execute();
        
        assertEquals(1, order.getOrderItems().size());
        assertEquals("Burger", order.getOrderItems().get(0).getItemName());
        assertEquals("Fries", command.getRemovedItem().getItemName());
    }

    @Test
    public void testUndo() {
        
        command.execute();
        
        command.undo();
        
        assertEquals(2, order.getOrderItems().size());
        assertEquals("Burger", order.getOrderItems().get(0).getItemName());
        assertEquals("Fries", order.getOrderItems().get(1).getItemName());
        
        
    }

    @Test
    public void testUndoAlreadyCompleted() {
        
        command.execute();
        
        command.undo();
        command.undo(); 

        assertEquals(2, order.getOrderItems().size());
        assertEquals("Burger", order.getOrderItems().get(0).getItemName());
        assertEquals("Fries", order.getOrderItems().get(1).getItemName());
    }

    @Test
    public void testExecuteWhenOrderIsEmpty() {
        
        order.clearAllItems(); 
        command.execute();

        assertNull(command.getRemovedItem());
        assertEquals(0, order.getOrderItems().size());
    }
    
}
