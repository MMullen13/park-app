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
public class MenuTests {
    
    private Menu menu;
    private MenuItem item1;
    private MenuItem item2;

    @Before
    public void setUp() {
        menu = new Menu("Test Menu");
        item1 = new MenuItem("Pasta", 10.99, 1);
        item2 = new MenuItem("Salad", 5.99, 1);
    }

    @After
    public void tearDown() {
        menu = null;
        item1 = null;
        item2 = null;
    }

    /**
     * Tests the add menu item method
     */
    @Test
    public void testAddMenuItem() {
        
        menu.addMenuItem(item1);
        menu.addMenuItem(item2);
        
        ArrayList<MenuItem> items = menu.getMenuItems();
        
        assertEquals(2, items.size());
        assertTrue(items.contains(item1));
        assertTrue(items.contains(item2));
    }

    /**
     * Test remove menu item method
     */
    @Test
    public void testRemoveMenuItem() {
        
        menu.addMenuItem(item1);
        menu.addMenuItem(item2);
        menu.removeMenuItem(item1);
        
        ArrayList<MenuItem> items = menu.getMenuItems();
        
        assertEquals(1, items.size());
        assertFalse(items.contains(item1));
        assertTrue(items.contains(item2));
    }

    /**
     * Tests the get menu name method
     */
    @Test
    public void testGetMenuName() {
        
        assertEquals("Test Menu", menu.getMenuName());
        
    }

    /**
     * Tests to make sure default eateries are created
     */
    @Test
    public void testCreateDefaults() {
        
        menu.createDefaults();
        
        ArrayList<Eatery> eateries = menu.getEateries();
        
        assertEquals(3, eateries.size());
        assertEquals("Bistro Bella", eateries.get(0).getEateryName());
        assertEquals("Grill & Chill", eateries.get(1).getEateryName());
        assertEquals("Chicken Kitchen", eateries.get(2).getEateryName());
    }
    
}
