package model.loginsignup;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author aniut
 */
public class UserFactoryTest {

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of createUser method, of class UserFactory to create a NewUser
     * class.
     */
    @Test
    public void testCreateNewUser() {
        UserIF user = UserFactory.createUser("newuser@example.com", "newpassword", true);
        assertTrue(user instanceof NewUser);

        User newUser = (User) user;
        assertEquals("newuser@example.com", newUser.getEmail());
        assertEquals("newpassword", newUser.getPassword());
    }

    /**
     * Test of createUser method, of class UserFactory to create a NewUser
     * class.
     */
    @Test
    public void testCreateProfileUser() {
        UserIF user = UserFactory.createUser("profileuser@example.com", "profilepassword", false);
        assertTrue(user instanceof ProfileUser);

        User profileUser = (User) user;
        assertEquals("profileuser@example.com", profileUser.getEmail());
        assertEquals("profilepassword", profileUser.getPassword());
    }

}
