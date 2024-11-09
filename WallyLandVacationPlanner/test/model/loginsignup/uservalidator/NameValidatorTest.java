package model.loginsignup.uservalidator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ana
 */
public class NameValidatorTest {

    NameValidator nameValidator;

    @Before
    public void setUp() {
        nameValidator = new NameValidator();
    }

    @After
    public void tearDown() {
        nameValidator = null;
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyName() {
        // Test an empty name which should throw IllegalArgumentException.
        nameValidator.validate("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullName() {
        // Test a null name which should throw IllegalArgumentException.
        nameValidator.validate(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNameWithNonAlphaCharacters() {
        // Test a name with digits and symbols which should throw IllegalArgumentException.
        nameValidator.validate("John123");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNameWithSpaces() {
        // Test a name with spaces, which should throw IllegalArgumentException.
        nameValidator.validate("John Doe");
    }

    @Test
    public void testMixedCaseName() {
        // Test a name with mixed case (e.g., "jOhn"), should be capitalized.
        String mixedCaseName = "jOhn";
        String result = nameValidator.validate(mixedCaseName);
        // Name should be capitalized to "John"
        assertEquals("John", result);
    }

    @Test
    public void testUppercaseName() {
        // Test a name with uppercase letters (e.g., "DOE"), should be capitalized to "Doe".
        String uppercaseName = "Doe";
        String result = nameValidator.validate(uppercaseName);
        // Name should be capitalized to "Doe"
        assertEquals("Doe", result);
    }

}
