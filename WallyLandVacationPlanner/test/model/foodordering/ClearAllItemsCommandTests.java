/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.foodordering;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;


/**
 *
 * @author theme
 */
public class ClearAllItemsCommandTests {
    
    private Order order;
    private ClearAllItemsCommand clearAllItemsCommand;

    @Before
    public void setUp() {
        order = new Order();
        clearAllItemsCommand = new ClearAllItemsCommand(order);
    }

    @After
    public void tearDown() {
        order = null;
        clearAllItemsCommand = null;
    }

    /**
     * Test that the order items clear
     */
    @Test
    public void testExecuteClearsAllItems() {
        
        order.addItem(new MenuItem("Pizza", 8.99, 1));
        order.addItem(new MenuItem("Soda", 1.99, 2));

        clearAllItemsCommand.execute();
        
        assertTrue(order.getOrderItems().isEmpty());
    }
    
    /**
     * Test the undo restores the items back to the order.
     */
    @Test
    public void testUndoRestoresItems() {
        
        MenuItem item1 = new MenuItem("Pizza", 8.99, 1);
        MenuItem item2 = new MenuItem("Soda", 1.99, 2);
        order.addItem(item1);
        order.addItem(item2);

        clearAllItemsCommand.execute();

        clearAllItemsCommand.undo();

        ArrayList<MenuItem> items = order.getOrderItems();
        assertEquals(2, items.size());
        assertTrue(items.contains(item1));
        assertTrue(items.contains(item2));
    }

    /**
     * Tests that the undo function can only run 1 time.
     */
    @Test
    public void testUndoCannotBeRanTwice() {

        MenuItem item1 = new MenuItem("Pizza", 8.99, 1);
        order.addItem(item1);

        clearAllItemsCommand.execute();
        clearAllItemsCommand.undo();

        clearAllItemsCommand.undo();

        ArrayList<MenuItem> items = order.getOrderItems();
        assertEquals(1, items.size());
        assertTrue(items.contains(item1));
    }

 
    
}
