/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package model.ticketsandpasses;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author aniut
 */
public class PurchaseTicketEventTest {
    private PurchaseTicketEvent event;
    private Ticket ticket;

    @Before
    public void setUp() {
        ticket = new Ticket();
        event = new PurchaseTicketEvent(this, ticket);
    }

    @After
    public void tearDown() {
        event = null;
        ticket = null;
    }

    @Test
    public void testGetTicketPriceCild() {
        assertEquals(25, event.getPassPrice("child"));
    }

    @Test
    public void testGetTicketPriceAdult() {
        assertEquals(35, event.getPassPrice("adult"));
    }

    @Test
    public void testGetTicketPriceSenior() {
        assertEquals(30, event.getPassPrice("senior"));
    }
    
        @Test
    public void testTicketPriceChild() {
        assertNotEquals(24, event.getPassPrice("child"));
        assertNotEquals(26, event.getPassPrice("child"));
    }

    @Test
    public void testTicketPriceAdult() {
        assertNotEquals(34, event.getPassPrice("adult"));
        assertNotEquals(36, event.getPassPrice("adult"));
    }

    @Test
    public void testTicketPriceSenior() {
        assertNotEquals(29, event.getPassPrice("senior"));
        assertNotEquals(31, event.getPassPrice("senior"));
    }

    @Test
    public void testGetTicketPriceInvalid() {
        assertEquals(0, event.getPassPrice("student"));
    }
}

