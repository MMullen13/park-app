/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.foodordering;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.List;



/**
 *
 * @author theme
 */
public class MenuItemsTest {
    
    private MenuItem menuItem;

    @Before
    public void setUp() {
        menuItem = new MenuItem("Burger", 5.99, 2);
    }

    /**
     * Test getter for item name
     */
    @Test
    public void testGetItemName() {
        
        assertEquals("Burger", menuItem.getItemName());
        
    }

    /**
     * Test getter for item price
     */
    @Test
    public void testGetPrice() {
        
        assertEquals(5.99, menuItem.getPrice(), 0.001);
        
    }

    /**
     * test correct quantity is returned
     */
    @Test
    public void testGetQuantity() {
        
        assertEquals(2, menuItem.getQuantity());
        
    }

    /**
     * Tests that the correct drinks are returned
     */
    @Test
    public void testGetDrinks() {
        
        List<MenuItem> drinks = MenuItem.getDrinks("Bistro Bella");
        
        assertNotNull(drinks);
        assertEquals(4, drinks.size());
        assertEquals("Sparkling Water", drinks.get(0).getItemName());
    }

    /**
     * Tests that the correct mains are returned
     */
    @Test
    public void testGetMains() {
        
        List<MenuItem> mains = MenuItem.getMains("Grill & Chill");
        
        assertNotNull(mains);
        assertEquals(4, mains.size());
        assertEquals("Double Bacon Cheeseburger", mains.get(0).getItemName());
    }

    /**
     * Tests that the correct apps are returned
     */
        @Test
    public void testGetApps() {
        
        List<MenuItem> apps = MenuItem.getApps("Chicken Kitchen");
        
        assertNotNull(apps);
        assertEquals(4, apps.size());
        assertEquals("Popcorn Chicken", apps.get(0).getItemName());
    }

    /**
     * Tests that the correct sides are returned
     */
    @Test
    public void testGetSides() {
        
        List<MenuItem> sides = MenuItem.getSides("Bistro Bella");
        
        assertNotNull(sides);
        assertEquals(4, sides.size());
        assertEquals("Baked Potato", sides.get(0).getItemName());
    }

    /**
     * Tests that the correct desserts are returned
     */
    @Test
    public void testGetDesserts() {
        
        List<MenuItem> desserts = MenuItem.getDesserts("Grill & Chill");
        
        assertNotNull(desserts);
        assertEquals(4, desserts.size());
        assertEquals("Ice Cream", desserts.get(0).getItemName());
    }
    
}
