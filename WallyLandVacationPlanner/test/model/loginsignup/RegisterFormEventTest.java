package model.loginsignup;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ana
 */
public class RegisterFormEventTest {

    User user;
    RegisterFormEvent event;
    
    @Before
    public void setUp() {
       user = new NewUser("testuser@example.com", "testpassword");
       event = new RegisterFormEvent(this, user);
       user.setFirstName("John");
       user.setLastName("Doe");
       user.setPhoneNum("(244)098-6789");
       user.setAgeID(0);
    }
    
    @After
    public void tearDown() {
        user = null;
        event = null;
    }

    /**
     * Test of getUser method, of class RegisterFormEvent.
     */
    @Test
    public void testGetUser() {
        System.out.println("getUser");
       
        assertEquals(user, event.getUser());
    }

    /**
     * Test of getEmail method, of class RegisterFormEvent.
     */
    @Test
    public void testGetEmail() {
        System.out.println("getEmail");
        
        assertEquals("testuser@example.com", event.getEmail());
    }

    /**
     * Test of getPassword method, of class RegisterFormEvent.
     */
    @Test
    public void testGetPassword() {
        System.out.println("getPassword");
        
        assertEquals("testpassword", event.getPassword());
    }

    /**
     * Test of getFirstName method, of class RegisterFormEvent.
     */
    @Test
    public void testGetFirstName() {
        System.out.println("getFirstName");
        
        assertEquals("John", event.getFirstName());
        
    }

    /**
     * Test of getLastName method, of class RegisterFormEvent.
     */
    @Test
    public void testGetLastName() {
        System.out.println("getLastName");
        
        assertEquals("Doe", event.getLastName());
    }

    /**
     * Test of getPhone method, of class RegisterFormEvent.
     */
    @Test
    public void testGetPhone() {
        System.out.println("getPhone");
        
        assertEquals("(244)098-6789", event.getPhone());
    }

    /**
     * Test of getAgeID method, of class RegisterFormEvent.
     */
    @Test
    public void testGetAgeID() {
        System.out.println("getAgeID");
        
        assertEquals(0, event.getAgeID());
    }

    
}
