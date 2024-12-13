/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.passes.cart;

import controller.ticketsandpasses.PassesController;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import javax.swing.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import model.ticketsandpasses.CartItem;


/**
 *
 * @author theme
 */
public class CartTests {
    
    private CartPanel cartPanel;
    private PassesController controller;

    @Before
    public void setUp() throws Exception {
        controller = new PassesController();
        cartPanel = new CartPanel(controller);
        cartPanel.setVisible(false);
    }

    @Test
    public void testInitialButtonStates() throws Exception {
        JButton deleteBtn = getPrivateField(cartPanel, "deleteCartItemsBtn", JButton.class);
        JButton purchaseBtn = getPrivateField(cartPanel, "purchaseButton", JButton.class);
        
        assertFalse("Delete button should be initially disabled", deleteBtn.isEnabled());
        assertFalse("Purchase button should be initially disabled", purchaseBtn.isEnabled());
    }

    @Test
    public void testAddingItemUpdatesLabels() throws Exception {
        List<CartItem> ticketCartItemsList = getPrivateField(cartPanel, "ticketCartItemsList", List.class);
        ticketCartItemsList.add(new CartItem("Adult", 1, 25.00));

        invokePrivateMethod(cartPanel, "updateTotalLabel");
        invokePrivateMethod(cartPanel, "updateEmptyCartLabel");

        JLabel totalLabel = getPrivateField(cartPanel, "totalLabel", JLabel.class);
        JLabel emptyCartLabel = getPrivateField(cartPanel, "emptyCartLabel", JLabel.class);

        assertNotEquals("Total should not be $0.00", "$0.00", totalLabel.getText());
        assertFalse("Empty cart label should not be visible", emptyCartLabel.isVisible());
    }

    @Test
    public void testButtonStatesAfterAddingItems() throws Exception {
        List<CartItem> ticketCartItemsList = getPrivateField(cartPanel, "ticketCartItemsList", List.class);
        ticketCartItemsList.add(new CartItem("Adult", 1, 25.00));

        invokePrivateMethod(cartPanel, "updateButtonStates");

        JButton deleteBtn = getPrivateField(cartPanel, "deleteCartItemsBtn", JButton.class);
        JButton purchaseBtn = getPrivateField(cartPanel, "purchaseButton", JButton.class);

        assertTrue("Delete button should be enabled when there are items", deleteBtn.isEnabled());
        assertTrue("Purchase button should be enabled when there are items", purchaseBtn.isEnabled());
    }

   

    //method to run a private method
    private void invokePrivateMethod(Object obj, String methodName) throws Exception {
        Method privateMethod = obj.getClass().getDeclaredMethod(methodName);
        privateMethod.setAccessible(true);
        privateMethod.invoke(obj);
    }

    //method to get access a private field
    private <F> F getPrivateField(Object obj, String fieldName, Class<F> fieldType) throws Exception {
        Field field = obj.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        return fieldType.cast(field.get(obj));
    }
    
   
}
    
    
    
    
    

