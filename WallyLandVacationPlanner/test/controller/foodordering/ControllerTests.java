/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.foodordering;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Map;
import model.foodordering.*;

/**
 *
 * @author theme
 */
public class ControllerTests {
    
    private FoodController foodController;
    
    @Before
    public void setUp() {
        foodController = new FoodController();
    }
    
    @After
    public void tearDown() {
        foodController = null;
    }
    
        @Test
    public void testAddItemToOrder() {
        MenuItem item = new MenuItem("Burger", 5.99, 1);
        foodController.addItemToOrder(item);

        ArrayList<MenuItem> items = foodController.getOrderItems();
        assertEquals(1, items.size());
        assertEquals("Burger", items.get(0).getItemName());
        assertEquals(5.99, items.get(0).getPrice(), 0.001);
    }

    @Test
    public void testRemoveLastItem() {
        MenuItem item1 = new MenuItem("Burger", 5.99, 1);
        MenuItem item2 = new MenuItem("Fries", 2.99, 1);
        foodController.addItemToOrder(item1);
        foodController.addItemToOrder(item2);

        foodController.removeLastItem();
        ArrayList<MenuItem> items = foodController.getOrderItems();
        assertEquals(1, items.size());
        assertEquals("Burger", items.get(0).getItemName());
    }

    @Test
    public void testClearAllItems() {
        MenuItem item1 = new MenuItem("Burger", 5.99, 1);
        MenuItem item2 = new MenuItem("Fries", 2.99, 1);
        foodController.addItemToOrder(item1);
        foodController.addItemToOrder(item2);

        foodController.clearAllItems();
        ArrayList<MenuItem> items = foodController.getOrderItems();
        assertEquals(0, items.size());
    }

    @Test
    public void testCalculateTotal() {
        MenuItem item1 = new MenuItem("Burger", 5.99, 1);
        MenuItem item2 = new MenuItem("Fries", 2.99, 2);
        foodController.addItemToOrder(item1);
        foodController.addItemToOrder(item2);

        foodController.calculateTotal(1, 5.99);
        foodController.calculateTotal(2, 2.99);

        assertEquals(12.80, foodController.getTotal(), 0.01);
    }

    @Test
    public void testNewOrder() {
        Eatery eatery = new Eatery("Bistro Bella");
        foodController.newOrder(eatery);

        Eatery currentEatery = foodController.getEatery();
        assertNotNull(currentEatery);
        assertEquals("Bistro Bella", currentEatery.getEateryName());
    }

    @Test
    public void testGetMenuItems() {
        Map<String, ArrayList<MenuItem>> menuItems = foodController.getMenuItems("Bistro Bella");
        assertTrue(menuItems.containsKey("Drinks"));
        assertTrue(menuItems.containsKey("Appitizers"));
        assertTrue(menuItems.containsKey("Mains"));
        assertTrue(menuItems.containsKey("Sides"));
        assertTrue(menuItems.containsKey("Desserts"));
    }

    @Test
    public void testMarkOrderAsPickedUp() {
        foodController.markedOrderAsNotPickedUp();
        assertFalse(foodController.isOrderPickedUp());

        foodController.markedOrderAsPickedUp();
        assertTrue(foodController.isOrderPickedUp());
    }
    
}
