package model.ticketsandpasses;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author aniut
 */
public class TicketTest {

    private Ticket ticket;

    @Before
    public void setUp() {
        ticket = new Ticket();
    }

    @After
    public void tearDown() {
        ticket = null;
    }

    @Test
    public void testGetPriceForTypeSilver() {
        assertEquals(25, ticket.getPriceForType("child"));
    }

    @Test
    public void testGetPriceForTypeGold() {
        assertEquals(35, ticket.getPriceForType("adult"));
    }

    @Test
    public void testGetPriceForTypePlatinum() {
        assertEquals(30, ticket.getPriceForType("senior"));
    }

    @Test
    public void testGetPriceForTypeInvalid() {
        assertEquals(0, ticket.getPriceForType("student"));
    }

    @Test
    public void testCalcPriceWithTaxesSilver() {
        assertEquals(42.5, ticket.calcPriceWithTaxes("child"), 0.01);
    }

    @Test
    public void testCalcPriceWithTaxesGold() {
        assertEquals(59.5, ticket.calcPriceWithTaxes("adult"), 0.01);
    }

    @Test
    public void testCalcPriceWithTaxesPlatinum() {
        assertEquals(51.0, ticket.calcPriceWithTaxes("senior"), 0.01);
    }

    @Test
    public void testCalcPriceWithTaxesInvalid() {
        assertEquals(0.0, ticket.calcPriceWithTaxes("student"), 0.01);
    }
}