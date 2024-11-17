/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.foodordering;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

/**
 *
 * @author theme
 */
public class OrderTests {
    
    private Order order;
    private Eatery eatery;

    @Before
    public void setUp() {
        eatery = new Eatery("Bistro Bella");
        order = new Order(eatery);
    }

    /**
     * Test the correct random ID length is generated
     */
    @Test
    public void testGenerateOrderID() {
        
        
        assertEquals(7, order.getOrderID().length());
        
    }

    /**
     * Test that item is added to the order
     */
    @Test
    public void testAddItem() {
        
        MenuItem item = new MenuItem("Burger", 5.99, 1);
        order.addItem(item);
        
        ArrayList<MenuItem> items = order.getOrderItems();
        
        assertEquals(1, items.size());
        assertEquals("Burger", items.get(0).getItemName());
    }

    /**
     * Test that the last item added to the order is removed
     */
    @Test
    public void testDeleteLastItem() {
        
        MenuItem item1 = new MenuItem("Burger", 5.99, 1);
        MenuItem item2 = new MenuItem("Fries", 2.99, 1);
        
        order.addItem(item1);
        order.addItem(item2);

        MenuItem removedItem = order.deleteLastItem();
        assertEquals("Fries", removedItem.getItemName());
        assertEquals(1, order.getOrderItems().size());
    }

    /**
     * Test that all items are removed from the order. 
     */
    @Test
    public void testClearAllItems() {
        
        MenuItem item1 = new MenuItem("Burger", 5.99, 1);
        MenuItem item2 = new MenuItem("Fries", 2.99, 1);
        
        order.addItem(item1);
        order.addItem(item2);

        ArrayList<MenuItem> removedItems = order.clearAllItems();
        
        assertEquals(2, removedItems.size());
        assertEquals(0, order.getOrderItems().size());
    }

    /**
     * Tests the calculated total for the order is correctly calculate with tax
     */
    @Test
    public void testCalculateTotal() {
        
        order.calculateTotal(1, 5.99);
        order.calculateTotal(2, 2.99);

        double expectedTotal = (5.99 + (2 * 2.99)) * 1.07;
        
        assertEquals(expectedTotal, order.getTotal(), 0.01);
    }

    /**
     * Tests the the order total is reset
     */
    @Test
    public void testResetTotal() {
        
        order.calculateTotal(1, 5.99);
        order.resetTotal();
        
        assertEquals(0.00, order.getTotal(), 0.01);
    }

    /**
     * Test the correct pickup time is set
     */
    @Test
    public void testSetPickupTime() {
        
        order.setPickupTime("12:30 PM");
        
        assertEquals("12:30 PM", order.getPickupTime());
    }

    /**
     * Test the correct pickup date is set
     */
    @Test
    public void testSetPickupDate() {
        
        order.setPickupDate("2024-11-14");
        
        assertEquals("2024-11-14", order.getPickupDate());
        
    }

    /**
     * Test to make sure correct eatery is returned
     */
    @Test
    public void testGetEatery() {
        
        assertEquals("Bistro Bella", order.getEatery().getEateryName());
        
    }
    
}
