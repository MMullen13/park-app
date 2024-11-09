package model.loginsignup.uservalidator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ana
 */
public class EmailValidatorTest {

    EmailValidator emailValidator;

    @Before
    public void setUp() {
        emailValidator = new EmailValidator();
    }

    @After
    public void tearDown() {
        emailValidator = null;
    }

    @Test
    public void testValidEmail() {
        // Test a valid email address
        String validEmail = "test.email@example.com";
        String result = emailValidator.validate(validEmail);
        assertEquals(validEmail, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyEmail() throws Exception{
        // Test an empty email string, which should throw IllegalArgumentException
        emailValidator.validate("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmailWithoutAtSymbol() throws Exception{
        // Test an email missing the "@" symbol, which should throw IllegalArgumentException
        emailValidator.validate("testemail.com");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmailWithInvalidCharacters() throws Exception{
        // Test an email with invalid characters (e.g., spaces), which should throw IllegalArgumentException
        emailValidator.validate("test email@example.com");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmailWithoutDomain() throws Exception{
        // Test an email without a valid domain, should throw IllegalArgumentException
        emailValidator.validate("testemail@.com");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmailWithTooManyAtSymbols() throws Exception{
        // Test an email with more than one "@" symbol, which should throw IllegalArgumentException
        emailValidator.validate("test@@example.com");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmailWithInvalidTLD() throws Exception{
        // Test an email with an invalid top-level domain (e.g., no characters after ".")
        emailValidator.validate("testemail@example.");
    }

    @Test
    public void testEmailWithValidFormatButDifferentCases() throws Exception{
        // Test an email with a valid format but different cases
        String email = "Test.Email@Example.COM";
        String result = emailValidator.validate(email);
        assertEquals(email, result);
    }
}
