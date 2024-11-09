package controller.loginsignup;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import view.loginsignup.login.LoginView;
import view.loginsignup.register.RegisterView;

/**
 *
 * @author Ana
 */
public class LoginControllerIT {

    private LoginController loginController;
    private LoginView loginView;
    private RegisterView registerView;

    @Before
    public void setUp() {
        // Initialize the controller and views before each test
        loginController = new LoginController();
        loginView = new LoginView();
        registerView = new RegisterView();
    }

    @After
    public void tearDown() {
        // Clean up after each test (if needed)
        loginController = null;
        loginView = null;
        registerView = null;
    }

    /**
     * Test of setLoginView method, of class LoginController.
     */
    @Test
    public void testSetLoginView() {
        System.out.println("testSetLoginView");

        // Set the login view in the controller
        loginController.setLoginView(loginView);

        // Verify that the login view is correctly set
        assertEquals(loginView, loginController.getLoginView());
    }

    /**
     * Test of getLoginView method, of class LoginController.
     */
    @Test
    public void testGetLoginView() {
        System.out.println("testGetLoginView");

        // Set the login view in the controller
        loginController.setLoginView(loginView);

        // Verify that the login view returned by the controller is the one set
        assertNotNull(loginController.getLoginView());
        assertEquals(loginView, loginController.getLoginView());
    }

    /**
     * Test of setRegisterView method, of class LoginController.
     */
    @Test
    public void testSetRegisterView() {
        System.out.println("testSetRegisterView");

        // Set the register view in the controller
        loginController.setRegisterView(registerView);

        // Verify that the register view is correctly set
        assertEquals(registerView, loginController.getRegisterView());
    }

    /**
     * Test of getRegisterView method, of class LoginController.
     */
    @Test
    public void testGetRegisterView() {
        System.out.println("testGetRegisterView");

        // Set the register view in the controller
        loginController.setRegisterView(registerView);

        // Verify that the register view returned by the controller is the one set
        assertNotNull(loginController.getRegisterView());
        assertEquals(registerView, loginController.getRegisterView());
    }
}

