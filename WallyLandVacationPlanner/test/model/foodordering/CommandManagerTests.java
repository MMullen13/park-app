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
public class CommandManagerTests {
    
    private CommandManager commandManager;
    private Order order;
    private OrderCommandIF clearAllCommand;

    @Before
    public void setUp() {
        commandManager = new CommandManager();
        order = new Order();
        clearAllCommand = new ClearAllItemsCommand(order);
    }

    @After
    public void tearDown() {
        commandManager = null;
        order = null;
        clearAllCommand = null;
    }

    /**
     * Tests the execute command
     */
    @Test
    public void testExecuteCommand() {

        MenuItem item1 = new MenuItem("Pizza", 8.99, 1);
        order.addItem(item1);

        commandManager.executeCommand(clearAllCommand);

        assertTrue(order.getOrderItems().isEmpty());
    }

    /**
     * Tests the undo command on the last command executed
     */
    @Test
    public void testUndoLastCommand() {

        MenuItem item1 = new MenuItem("Pizza", 8.99, 1);
        order.addItem(item1);

        commandManager.executeCommand(clearAllCommand);
        boolean undoSuccessful = commandManager.undoLastCommand();

        assertTrue(undoSuccessful);
        assertEquals(1, order.getOrderItems().size());
        assertEquals("Pizza", order.getOrderItems().get(0).getItemName());
    }

    /**
     * Test the undo command when stack is empty
     */
    @Test
    public void testUndoOnEmptyStack() {
        
        boolean undoSuccessful = commandManager.undoLastCommand();

        assertFalse(undoSuccessful);
    }

    /**
     * Test to see if last command equals last command given
     */
    @Test
    public void testGetLastCommand() {

        commandManager.executeCommand(clearAllCommand);

        assertEquals(clearAllCommand, commandManager.getLastCommand());
    }

    
}
