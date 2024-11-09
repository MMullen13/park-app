package model.loginsignup.uservalidator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ana
 */
public class PhoneNumberValidatorTest {
    
    PhoneNumberValidator instance;
    
    @Before
    public void setUp() {
        instance = new PhoneNumberValidator();
    }
    
    @After
    public void tearDown() {
        instance = null;
    }

    /**
     * Test of validate method, of class PhoneNumberValidator.
     */
    @Test
    public void testValidate() {
        System.out.println("validate phone number");

        String expResult = "(267)234-1234";
        String result = instance.validate("(267)234-1234");
        assertEquals(expResult, result);
    }
    
}
