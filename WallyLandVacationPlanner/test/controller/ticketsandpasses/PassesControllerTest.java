package controller.ticketsandpasses;

import java.util.List;
import java.util.Map;
import model.ticketsandpasses.CartItem;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import view.passes.cart.CartView;
import view.passes.tiketsandpasses.PassesView;
import view.passes.tiketsandpasses.TicketsView;
import java.io.*;
import java.util.*;

/**
 *
 * @author Ana
 */
public class PassesControllerTest {

    private PassesController controller;

    @Before
    public void setUp() {
        controller = new PassesController();
    }

    @After
    public void tearDown() {
        controller = null;
    }

    @Test
    public void testGetCartView() {
        assertNull(controller.getCartView());
        CartView cartView = new CartView();
        controller.setCartView(cartView);
        assertEquals(cartView, controller.getCartView());
    }

    @Test
    public void testSetCartView() {
        CartView cartView = new CartView();
        controller.setCartView(cartView);
        assertEquals(cartView, controller.getCartView());
    }

    @Test
    public void testGetTicketsView() {
        assertNull(controller.getTicketsView());
        TicketsView ticketsView = new TicketsView();
        controller.setTicketsView(ticketsView);
        assertEquals(ticketsView, controller.getTicketsView());
    }

    @Test
    public void testSetTicketsView() {
        TicketsView ticketsView = new TicketsView();
        controller.setTicketsView(ticketsView);
        assertEquals(ticketsView, controller.getTicketsView());
    }

    @Test
    public void testGetPassView() {
        assertNull(controller.getPassView());
        PassesView passView = new PassesView();
        controller.setPassView(passView);
        assertEquals(passView, controller.getPassView());
    }

    @Test
    public void testSetPassView() {
        PassesView passView = new PassesView();
        controller.setPassView(passView);
        assertEquals(passView, controller.getPassView());
    }

    @Test
    public void testGetPassesCartItems() {
        assertTrue(controller.getPassesCartItems().isEmpty());
    }

    @Test
    public void testGetTicketsCartItems() {
        assertTrue(controller.getTicketsCartItems().isEmpty());
    }

    @Test
    public void testUpdatePassTotals() {
        controller.updatePassTotals("Adult", 2);
        double expectedPrice = 2 * controller.pass.getPriceForType("Adult");
        assertEquals(expectedPrice, controller.totalPassesPrice, 0.01);
    }

    @Test
    public void testUpdateTicketsTotals() {
        controller.updateTicketsTotals("Child", 3);
        double expectedPrice = 3 * controller.ticket.getPriceForType("Child");
        assertEquals(expectedPrice, controller.totalTicketsPrice, 0.01);
    }

    @Test
    public void testSaveAndLoadPassCartDataToFile() throws IOException {
        Map<String, Integer> cartItems = new HashMap<>();
        cartItems.put("Silver", 2);
        cartItems.put("Gold", 1);

        controller.savePassCartDataToFile(cartItems);
        controller.loadPassCartDataFromFile();

        List<CartItem> items = controller.getPassesCartItems();
        assertEquals(2, items.size());
        assertEquals("Silver", items.get(0).getType());
        assertEquals(2, items.get(0).getQuantity());
        assertEquals("Gold", items.get(1).getType());
        assertEquals(1, items.get(1).getQuantity());

        // Clean up the test file
        new File("pass_cart_data.txt").delete();
    }

    @Test
    public void testSaveAndLoadTicketCartDataToFile() throws IOException {
        Map<String, Integer> cartItems = new HashMap<>();
        cartItems.put("Senior", 3);
        cartItems.put("Adult", 2);
        cartItems.put("Child", 1);

        controller.savePassCartDataToFile(cartItems);
        controller.loadPassCartDataFromFile();

        List<CartItem> items = controller.getPassesCartItems();
        assertEquals(3, items.size());
        assertEquals("Senior", items.get(0).getType());
        assertEquals(3, items.get(0).getQuantity());
        assertEquals("Adult", items.get(1).getType());
        assertEquals(2, items.get(1).getQuantity());
        assertEquals("Child", items.get(2).getType());
        assertEquals(1, items.get(2).getQuantity());

        // Clean up the test file
        new File("ticket_cart_data.txt").delete();
    }

    @Test
    public void testClearCartFiles() {
        // Create dummy files
        try {
            new File("pass_cart_data.txt").createNewFile();
            new File("ticket_cart_data.txt").createNewFile();
        } catch (IOException e) {
            fail("Test setup failed: Unable to create files.");
        }

        controller.clearCartFiles();

        assertFalse(new File("pass_cart_data.txt").exists());
        assertFalse(new File("ticket_cart_data.txt").exists());
    }

    @Test
    public void testOpenCartView() {
        controller.openCartView();
        assertNotNull(controller.getCartView());
        assertTrue(controller.getCartView().isVisible());
    }
}
