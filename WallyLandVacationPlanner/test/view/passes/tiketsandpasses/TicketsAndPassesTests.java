/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.passes.tiketsandpasses;


import controller.ticketsandpasses.PassesController;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;

/**
 *
 * @author theme
 */
public class TicketsAndPassesTests {
    
    private PassesView passesView;
    private TicketsView ticketsView;
    private PassesPanel passesPanel;
    private PassesController controller;
    private TicketsPanel ticketsPanel;
    
    //Passes and Tickets View Tests
    @Before
    public void setUpPassesView() {
        
        passesView = new PassesView();
        passesView.setVisible(false);
    }
    
    
    @Test
    public void testPassesConstructorSettings() throws Exception {
        assertEquals("Window title should be set to 'Wallyland'", "Wallyland", passesView.getTitle());
        assertEquals("Default close operation should be DISPOSE_ON_CLOSE", JFrame.DISPOSE_ON_CLOSE, passesView.getDefaultCloseOperation());
        assertEquals("Window should not be resizable", false, passesView.isResizable());
        assertNotNull("Icon image set", passesView.getIconImage());
    }
    
    @Test
    public void testPassesFrameProperties() {
        
        assertEquals("Wallyland", passesView.getTitle());
        assertEquals(800, passesView.getSize().width);
        assertEquals(700, passesView.getSize().height);
        assertFalse(passesView.isResizable());
        assertEquals(JFrame.DISPOSE_ON_CLOSE, passesView.getDefaultCloseOperation());
        
        
    }

    @Test
    public void testPassesIcon() {
        
        assertNotNull(passesView.getIconImage());
        
    }

    @Test
    public void testPassesCloseWindow() {
        
        passesView.closeWindow();
        assertFalse(passesView.isVisible());
        
    }
    
    @Test
    public void testTicketFrameProperties() {
        
        assertEquals("Wallyland", ticketsView.getTitle());
        assertEquals(800, ticketsView.getSize().width);
        assertEquals(700, ticketsView.getSize().height);
        assertFalse(ticketsView.isResizable());
        assertEquals(JFrame.DISPOSE_ON_CLOSE, ticketsView.getDefaultCloseOperation());
    }

    @Test
    public void testTicketIconSet() {
        
        assertNotNull(ticketsView.getIconImage());
        
    }

    @Test
    public void testTicketCloseWindow() {
        ticketsView.closeWindow();
        assertFalse(ticketsView.isVisible());
        
    }

    @After
    public void tearDown() {
        
        passesView.dispose();
        
    }
    
    
    
    //Passes Panel Tests    
    @Before
    public void setUpPassesPanel() {
        
        controller = new PassesController();
        passesPanel = new PassesPanel(controller);
        passesPanel.setVisible(false); 
    }

    @Test
    public void testPassesUpdateCartLabel() throws Exception {
        // Reflection to access private method
        Method updateCartLabel = PassesPanel.class.getDeclaredMethod("updateCartLabel");
        updateCartLabel.setAccessible(true);

        // Simulation of adding items to the cart
        Map<String, Integer> cartItems = getPrivateField(passesPanel, "cartItems", Map.class);
        cartItems.put("Silver", 3);
        cartItems.put("Gold", 2);

        
        updateCartLabel.invoke(passesPanel);

        JLabel totalItemsCartLabel = getPrivateField(passesPanel, "totalItemsCartLabel", JLabel.class);
        JLabel totalPriceLabel = getPrivateField(passesPanel, "totalPriceLabel", JLabel.class);
        assertTrue("Label should display the correct number of items", totalItemsCartLabel.getText().contains("5 passes"));
        assertTrue("Label should display the correct total price", totalPriceLabel.getText().startsWith("Total Price (incl. taxes):"));
    }

    @Test
    public void testPassesButtonStatesAfterAddingItems() throws Exception {
        // Reflection to access private method and field
        Method updateButtonState = PassesPanel.class.getDeclaredMethod("updateButtonStates");
        updateButtonState.setAccessible(true);
        Map<String, Integer> cartItems = getPrivateField(passesPanel, "cartItems", Map.class);

        
        cartItems.put("Platinum", 1);

        updateButtonState.invoke(passesPanel);

        
        JButton addToCartBtn = getPrivateField(passesPanel, "addToCartBtn", JButton.class);
        assertTrue("Add to Cart button should be enabled after adding items", addToCartBtn.isEnabled());
    }

    
    
    //Ticket Panel Tests
    @Before
    public void setUpTicketPanel() {
        
        controller = new PassesController();
        ticketsPanel = new TicketsPanel(controller);
        ticketsPanel.setVisible(false); // Prevent GUI from showing
    }
    
    @Test
    public void testTicketsUpdateCartLabel() throws Exception {
        // Reflection to access private method and fields
        Method updateCartLabel = TicketsPanel.class.getDeclaredMethod("updateCartLabel");
        updateCartLabel.setAccessible(true);

        
        Map<String, Integer> cartItems = getPrivateField(ticketsPanel, "cartItems", Map.class);
        cartItems.put("Adult", 2);
        cartItems.put("Senior", 1);

       
        updateCartLabel.invoke(ticketsPanel);

       
        JLabel totalItemsCartLabel = getPrivateField(ticketsPanel, "totalItemsCartLabel", JLabel.class);
        JLabel totalPriceLabel = getPrivateField(ticketsPanel, "totalPriceLabel", JLabel.class);
        assertEquals("Label should display the number of tickets", "Total Items: 3 tickets", totalItemsCartLabel.getText());
        assertTrue("Label should display the total price", totalPriceLabel.getText().startsWith("Total Price (incl. taxes):"));
    }

    @Test
    public void testTicketsButtonStatesAfterAddingItems() throws Exception {
        // Reflection to access private method and field
        Method updateButtonStates = TicketsPanel.class.getDeclaredMethod("updateButtonStates");
        updateButtonStates.setAccessible(true);
        Map<String, Integer> cartItems = getPrivateField(ticketsPanel, "cartItems", Map.class);

       
        cartItems.put("Adult", 3); 

        
        updateButtonStates.invoke(ticketsPanel);

        
        JButton addToCartBtn = getPrivateField(ticketsPanel, "addToCartBtn", JButton.class);
        assertTrue("Add to Cart button should be enabled", addToCartBtn.isEnabled());
    }
    
    // method to access a private field using reflection
    @SuppressWarnings("unchecked")
    private <F> F getPrivateField(Object obj, String fieldName, Class<F> fieldType) throws Exception {
        Field field = obj.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        return (F) field.get(obj);
    } 
        
}
