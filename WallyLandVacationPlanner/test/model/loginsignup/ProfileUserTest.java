/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package model.loginsignup;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author aniut
 */
public class ProfileUserTest {

    ProfileUser user;

    @Before
    public void setUp() {
        user = new ProfileUser("test@example.com", "password123");
    }

    @After
    public void tearDown() {
        user = null;
    }

    @Test
    public void testGetFirstNameThrowsException() {
        System.out.println("Throws exception to get the first name");
        assertThrows(UnsupportedOperationException.class, user::getFirstName);
    }

    @Test
    public void testGetLastNameThrowsException() {
        System.out.println("Throws exception to get the last name");
        assertThrows(UnsupportedOperationException.class, user::getLastName);
    }

    @Test
    public void testGetPhoneNumThrowsException() {
        System.out.println("Throws exception to get the phone number");
        assertThrows(UnsupportedOperationException.class, user::getPhoneNum);
    }

    @Test
    public void testGetAgeIDThrowsException() {
        System.out.println("Throws exception to get the user id");
        assertThrows(UnsupportedOperationException.class, user::getAgeID);
    }

    @Test
    public void testSetFirstNameThrowsException() {
        System.out.println("Throws exception to set first name");
        assertThrows(UnsupportedOperationException.class, () -> user.setFirstName("Ana"));
    }

    @Test
    public void testSetLastNameThrowsException() {
        System.out.println("Throws exception to set last name");
        assertThrows(UnsupportedOperationException.class, () -> user.setLastName("Smith"));
    }

    @Test
    public void testSetPhoneNumThrowsException() {
        System.out.println("Throws exception to set phone number");
        assertThrows(UnsupportedOperationException.class, () -> user.setPhoneNum("123456789"));
    }

    @Test
    public void testSetAgeIDThrowsException() {
        System.out.println("Throws exception to set age");
        assertThrows(UnsupportedOperationException.class, () -> user.setAgeID(25));
    }
}
