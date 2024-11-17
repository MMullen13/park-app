package model.loginsignup;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ana
 */
public class RegisterFormEventIT {

    private RegisterFormEvent registerFormEvent;
    private User user;

    @Before
    public void setUp() {
        user = new NewUser("testuser@example.com", "testpassword");
        registerFormEvent = new RegisterFormEvent(this, user);
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setPhoneNum("1234567890");
        user.setAgeID(0);
    }

    @After
    public void tearDown() {
        // Clean up after each test if needed
        registerFormEvent = null;
        user = null;
    }

    /**
     * Test of getUser method, of class RegisterFormEvent.
     */
    @Test
    public void testGetUser() {
        System.out.println("testGetUser");

        assertEquals(user, registerFormEvent.getUser());
    }

    /**
     * Test of getEmail method, of class RegisterFormEvent.
     */
    @Test
    public void testGetEmail() {
        System.out.println("testGetEmail");

        // Test that the event correctly returns the email from the user
        String result = registerFormEvent.getEmail();
        assertEquals("testuser@example.com", result);
    }

    /**
     * Test of getPassword method, of class RegisterFormEvent.
     */
    @Test
    public void testGetPassword() {
        System.out.println("testGetPassword");

        // Test that the event correctly returns the password from the user
        String result = registerFormEvent.getPassword();
        assertEquals("testpassword", result);
    }

    /**
     * Test of getFirstName method, of class RegisterFormEvent.
     */
    @Test
    public void testGetFirstName() {
        System.out.println("testGetFirstName");

        assertEquals("John", registerFormEvent.getFirstName());
    }

    /**
     * Test of getLastName method, of class RegisterFormEvent.
     */
    @Test
    public void testGetLastName() {
        System.out.println("testGetLastName");

        assertEquals("Doe", registerFormEvent.getLastName());
    }

    /**
     * Test of getPhone method, of class RegisterFormEvent.
     */
    @Test
    public void testGetPhone() {
        System.out.println("testGetPhone");

        assertEquals("1234567890", registerFormEvent.getPhone());
    }

    /**
     * Test of getAgeID method, of class RegisterFormEvent.
     */
    @Test
    public void testGetAgeID() {
        System.out.println("testGetAgeID");

        assertEquals(0, registerFormEvent.getAgeID());
    }
}
