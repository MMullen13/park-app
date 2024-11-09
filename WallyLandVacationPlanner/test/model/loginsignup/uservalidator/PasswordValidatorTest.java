package model.loginsignup.uservalidator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ana
 */
public class PasswordValidatorTest {

    PasswordValidator validator;

    @Before
    public void setUp() {
        validator = new PasswordValidator();
    }

    @After
    public void tearDown() {
        validator = null;
    }

    /**
     * Test of validate method, of class PasswordValidator.
     */
    @Test
    public void testValidateGoodPassword() {
        System.out.println("validate password");
        String password = "Pass123!@";

        String expResult = "Pass123!@";
        String result = validator.validate(password);
        assertEquals(expResult, result);
    }

    @Test
    public void testShortPassword() {
        // Test case for a password that is too short (less than 8 characters)
        String password = "Short1!";
        validator.validate(password);
    }

    @Test
    public void testNoUppercase() {
        // Test case for a password missing an uppercase letter
        String password = "lowercase1!";
        validator.validate(password);
    }

    @Test
    public void testNoLowercase() {
        // Test case for a password missing a lowercase letter
        String password = "UPPERCASE1!";
        validator.validate(password);
    }

    @Test
    public void testNoDigit() {
        // Test case for a password missing a digit
        String password = "NoDigit!";
        validator.validate(password);
    }

    @Test
    public void testNoSpecialCharacter() {
        // Test case for a password missing a special character
        String password = "NoSpecial123";
        validator.validate(password);
    }

    @Test
    public void testPasswordWithAllCriteria() {
        // Test case for a valid password with all criteria met
        String password = "Valid123!";
        assertEquals(password, validator.validate(password));
    }

}
