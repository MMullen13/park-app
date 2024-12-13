package model.ticketsandpasses;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ana
 */
public class CartItemTest {

    private CartItem cartItem;

    @Before
    public void setUp() {
        cartItem = new CartItem("silver pass", 2, 50.0);
    }

    @After
    public void tearDown() {
        cartItem = null;
    }

    @Test
    public void testGetType() {
        assertEquals("silver pass", cartItem.getType());
    }

    @Test
    public void testSetType() {
        cartItem.setType("gold pass");
        assertEquals("gold pass", cartItem.getType());
    }

    @Test
    public void testGetQuantity() {
        assertEquals(2, cartItem.getQuantity());
    }

    @Test
    public void testSetQuantity() {
        cartItem.setQuantity(5);
        assertEquals(5, cartItem.getQuantity());
    }

    @Test
    public void testGetPrice() {
        assertEquals(50.0, cartItem.getPrice(), 0.01);
    }

    @Test
    public void testSetPrice() {
        cartItem.setPrice(75.0);
        assertEquals(75.0, cartItem.getPrice(), 0.01);
    }

    @Test
    public void testToString() {
        String expected = "type: silver pass, quantity: 2, price: $50.0";
        assertEquals(expected, cartItem.toString());
    }
}

