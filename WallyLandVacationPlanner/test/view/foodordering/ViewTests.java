/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.foodordering;

import controller.foodordering.FoodController;
import org.junit.Before;
import org.junit.Test;
import javax.swing.*;
import java.lang.reflect.*;
import javax.swing.table.DefaultTableModel;
import static org.junit.Assert.assertEquals;



/**
 *
 * @author theme
 */
public class ViewTests {
    
    private OrderConfirmationViewForm orderConfirmationViewForm;
    private FoodController controller;
    private OrderViewForm orderViewForm;
    
    //tests the the orderconfirmationview clear form method
    @Before
    public void setUpOrderConfirmationView() throws Exception {
        
        controller = new FoodController();
        orderConfirmationViewForm = new OrderConfirmationViewForm(controller);
        orderConfirmationViewForm.setVisible(false);
                
    }

   
    @Test
    public void testClearOrderConfirmationView() throws Exception {
        // private method clearOrderConfirmationView using reflection
        Method clearOrderConfirmationView = OrderConfirmationViewForm.class.getDeclaredMethod("clearOrderConfirmationView");
        clearOrderConfirmationView.setAccessible(true);
        clearOrderConfirmationView.invoke(orderConfirmationViewForm);

        
        JLabel orderNumberLabel = ViewTests.this.getPrivateField(orderConfirmationViewForm, "orderNumber", JLabel.class);
        JLabel pickupTimeLabel = ViewTests.this.getPrivateField(orderConfirmationViewForm, "pickupTime", JLabel.class);
        JLabel totalCostLabel = ViewTests.this.getPrivateField(orderConfirmationViewForm, "totalCost", JLabel.class);

        // Check if all the fields reset
        assertEquals("Order number should be empty", "", orderNumberLabel.getText());
        assertEquals("Pickup time should be empty", "", pickupTimeLabel.getText());
        assertEquals("Total cost should be 0.00", "$0.00", totalCostLabel.getText());

        JTable orderSummaryTable = ViewTests.this.getPrivateField(orderConfirmationViewForm, "orderSummaryTable", JTable.class);
        DefaultTableModel tableModel = (DefaultTableModel) orderSummaryTable.getModel();
        assertEquals("Table rows = 0", 0, tableModel.getRowCount());
    }

    // method to access a private field
    @SuppressWarnings("unchecked")
    private <F> F getPrivateField(Object obj, String fieldName, Class<F> fieldType) throws Exception {
        Field field = obj.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        return (F) field.get(obj);
    }
    
    
    //Tests orderviewform
    @Before
    public void setUpNewOrderView() throws Exception {
        controller = new FoodController();
        orderViewForm = new OrderViewForm(controller);
        orderViewForm.setVisible(false);
        usePrivateMethod(orderViewForm, "initManualComponents");
    }

    @Test
    public void testAddItemToOrder() throws Exception {
        // Using reflection to access and manipulate private fields
        JComboBox foodType = (JComboBox) getPrivateField(orderViewForm, "foodType");
        JComboBox options = (JComboBox) getPrivateField(orderViewForm, "options");
        JTextField quantity = (JTextField) getPrivateField(orderViewForm, "quantity");
        JButton addToOrder = (JButton) getPrivateField(orderViewForm, "addToOrder");
        JLabel totalCost = (JLabel) getPrivateField(orderViewForm, "totalCost");
        JTable orderTable = (JTable) getPrivateField(orderViewForm, "orderTable");

        // user interactions
        foodType.setSelectedIndex(0); // Selects food type
        options.setSelectedIndex(0); // Select the first item
        quantity.setText("1"); // Set quantity
        addToOrder.doClick(); // button click

        // Checks update to the table
        DefaultTableModel tableModel = (DefaultTableModel) orderTable.getModel();
        assertEquals("Table should have 1 row", 1, tableModel.getRowCount());

        // checks update to the total cost
        assertEquals("Total cost should be updated correctly", "$4.27", totalCost.getText());
    }

    private Object getPrivateField(Object obj, String fieldName) throws Exception {
        Field field = obj.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(obj);
    }

    private void usePrivateMethod(Object obj, String methodName) throws Exception {
        Method privateMethod = obj.getClass().getDeclaredMethod(methodName);
        privateMethod.setAccessible(true);
        privateMethod.invoke(obj);
    }
    
    
    
}
