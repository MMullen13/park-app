package view.loginsignup.register;

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
public class RegisterViewTest {
    
    private RegisterView registerView;

    @Before
    public void setUp() {
        registerView = new RegisterView();
    }
    
    @After
    public void tearDown() {
        registerView = null;
    }

    @Test
    public void testLoginViewInitialization() {
        // Test if the LoginView initializes correctly
        System.out.println("Testing the LoginView initializes correctly");
        
        assertNotNull(registerView);
        assertEquals(600, registerView.getWidth()); // Check if the frame's width is set correctly
        assertEquals(700, registerView.getHeight()); // Check if the frame's height is set correctly
    }

    @Test
    public void testFormPanelIsAdded() {
        // Test if the LoginFormPanel is added to the LoginView
        System.out.println("Testing the RegisterFormPanel is added to the RegisterView");
        
        Component[] components = registerView.getContentPane().getComponents();
        boolean formPanelAdded = false;
        for (Component component : components) {
            if (component instanceof RegisterFormPanel) {
                formPanelAdded = true;
                break;
            }
        }
        assertTrue("RegisterFormPanel should be added to the RegisterView", formPanelAdded);
    }


    @Test
    public void testControllerIsSetCorrectly() {
        // Test if the controller is correctly set in the LoginView
        System.out.println("Testing controller");
        LoginController loginController = new LoginController();
        
        registerView.controller = loginController; // Directly set controller to mock
        assertNotNull("Controller should be set", registerView.controller);
    }
  
}
