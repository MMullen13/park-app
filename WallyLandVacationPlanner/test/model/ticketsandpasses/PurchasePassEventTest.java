package model.ticketsandpasses;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ana
 */
public class PurchasePassEventTest {

    private PurchasePassEvent event;
    private Pass pass;

    @Before
    public void setUp() {
        pass = new Pass();
        event = new PurchasePassEvent(this, pass);
    }

    @After
    public void tearDown() {
        event = null;
        pass = null;
    }

    @Test
    public void testGetPassPriceSilver() {
        assertEquals(100, event.getPassPrice("silver"));
    }

    @Test
    public void testGetPassPriceGold() {
        assertEquals(150, event.getPassPrice("gold"));
    }

    @Test
    public void testGetPassPricePlatinum() {
        assertEquals(200, event.getPassPrice("platinum"));
    }

    @Test
    public void testGetPassPriceInvalid() {
        assertEquals(0, event.getPassPrice("bronze"));
    }
}

