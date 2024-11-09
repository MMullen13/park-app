package model.loginsignup;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ana
 */
public class NewUserTest {
    
    private NewUser newUser;
    
    @Before
    public void setUp() {
        System.out.println("set up before each class");
        newUser = new NewUser("test@example.com", "password123");
    }
    
    @After
    public void tearDown() {
        newUser = null;
    }

    /**
     * Test of setFirstName method, of class NewUser.
     */
    @Test
    public void testSetFirstName() {
        System.out.println("setFirstName");
        String firstName = "John";

        newUser.setFirstName(firstName);
        assertEquals("Testing set and get firstName", firstName, newUser.getFirstName());
    }

    /**
     * Test of setLastName method, of class NewUser.
     */
    @Test
    public void testSetLastName() {
        System.out.println("setLastName");
        String lastName = "Doe";
 
        newUser.setLastName(lastName);
        assertEquals("Testing set and get lastName", lastName, newUser.getLastName());
    }

    /**
     * Test of setPhoneNum method, of class NewUser.
     */
    @Test
    public void testSetPhoneNum() {
        System.out.println("setPhoneNum");
        String phoneNum = "(267)876-0098";
        newUser.setPhoneNum(phoneNum);
        assertEquals("Testing set and get phoneNum", phoneNum, newUser.getPhoneNum());
    }

    /**
     * Test of setAgeID method, of class NewUser.
     */
    @Test
    public void testSetAgeID() {
        System.out.println("setAgeID");
        int age = 25;

        newUser.setAgeID(age);
        assertEquals("Testing set and get ageID", age, newUser.getAgeID());
    }
    
    @Test
    public void testUniqueIDGeneration() {
        NewUser anotherUser = new NewUser("another@example.com", "password456");
        assertNotEquals("Each user should have a unique ID", newUser.getID(), anotherUser.getID());
    }
    
    @Test
    public void testSetAndGetAgeCategory() {
        AgeEnum ageCategory = null;
        newUser.setAge(ageCategory);
        assertEquals("Testing set and get ageCategory", ageCategory, newUser.getAgeCategory());
    }
    
}
