package model.ticketsandpasses;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ana
 */
public class PassTest {

    private Pass pass;

    @Before
    public void setUp() {
        pass = new Pass();
    }

    @After
    public void tearDown() {
        pass = null;
    }

    @Test
    public void testGetPriceForTypeSilver() {
        assertEquals(100, pass.getPriceForType("silver"));
    }

    @Test
    public void testGetPriceForTypeGold() {
        assertEquals(150, pass.getPriceForType("gold"));
    }

    @Test
    public void testGetPriceForTypePlatinum() {
        assertEquals(200, pass.getPriceForType("platinum"));
    }

    @Test
    public void testGetPriceForTypeInvalid() {
        assertEquals(0, pass.getPriceForType("bronze"));
    }

    @Test
    public void testCalcPriceWithTaxesSilver() {
        assertEquals(170.0, pass.calcPriceWithTaxes("silver"), 0.01);
    }

    @Test
    public void testCalcPriceWithTaxesGold() {
        assertEquals(255.0, pass.calcPriceWithTaxes("gold"), 0.01);
    }

    @Test
    public void testCalcPriceWithTaxesPlatinum() {
        assertEquals(340.0, pass.calcPriceWithTaxes("platinum"), 0.01);
    }

    @Test
    public void testCalcPriceWithTaxesInvalid() {
        assertEquals(0.0, pass.calcPriceWithTaxes("bronze"), 0.01);
    }
}
