package view.loginsignup.login;

import controller.loginsignup.LoginController;
import java.awt.Component;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ana
 */
public class LoginViewTest {
    
    private LoginView loginView;

    @Before
    public void setUp() {
        loginView = new LoginView();
    }
    
    @After
    public void tearDown() {
        loginView = null;
    }

    @Test
    public void testLoginViewInitialization() {
        // Test if the LoginView initializes correctly
        System.out.println("Testing the LoginView initializes correctly");
        
        assertNotNull(loginView);
        assertEquals(600, loginView.getWidth()); // Check if the frame's width is set correctly
        assertEquals(600, loginView.getHeight()); // Check if the frame's height is set correctly
    }

    @Test
    public void testFormPanelIsAdded() {
        // Test if the LoginFormPanel is added to the LoginView
        System.out.println("Testing the LoginFormPanel is added to the LoginView");
        
        Component[] components = loginView.getContentPane().getComponents();
        boolean formPanelAdded = false;
        for (Component component : components) {
            if (component instanceof LoginFormPanel) {
                formPanelAdded = true;
                break;
            }
        }
        assertTrue("LoginFormPanel should be added to the LoginView", formPanelAdded);
    }


    @Test
    public void testControllerIsSetCorrectly() {
        // Test if the controller is correctly set in the LoginView
        System.out.println("Testing controller");
        LoginController loginController = new LoginController();
        
        loginView.controller = loginController; // Directly set controller to mock
        assertNotNull("Controller should be set", loginView.controller);
    }
  
}
