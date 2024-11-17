/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.foodordering;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author theme
 */
public class EateryTests {
    
    private Eatery eateryWithMenu;
    private Eatery eateryWithoutMenu;
    private Menu testMenu;

    @Before
    public void setUp() {
        testMenu = new Menu();
        eateryWithMenu = new Eatery("Bella Bistro", testMenu);
        eateryWithoutMenu = new Eatery("No Menu Eatery");
    }

    @After
    public void tearDown() {
        eateryWithMenu = null;
        eateryWithoutMenu = null;
        testMenu = null;
    }

    /**
     * Test getter for eatery name
     */
    @Test
    public void testGetEateryName() {
        
        assertEquals("Bella Bistro", eateryWithMenu.getEateryName());
        
        assertEquals("No Menu Eatery", eateryWithoutMenu.getEateryName());
    }
    
    /**
     * test getter for get menu for an eatery
     */
    @Test
    public void testGetMenu() {
        
        assertEquals(testMenu, eateryWithMenu.getMenu());
        
        assertNull(eateryWithoutMenu.getMenu());
        
    }

    /**
     * Tests the toString for returning eatery information
     */
    @Test
    public void testToString() {
        
        assertEquals("Bella Bistro", eateryWithMenu.toString());
        
        assertEquals("No Menu Eatery", eateryWithoutMenu.toString());
        
    }
    
}
