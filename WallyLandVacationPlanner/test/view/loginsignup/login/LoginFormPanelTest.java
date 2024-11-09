package view.loginsignup.login;

import java.awt.Component;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import view.loginsignup.LoginFormListenerIF;

/**
 *
 * @author Ana
 */
public class LoginFormPanelTest {

    LoginFormPanel loginFormPanel;
    LoginFormListenerIF formListener;
    JTextField emailField;
    JPasswordField passField;

    
    @Before
    public void setUp() {
        loginFormPanel = new LoginFormPanel();
        loginFormPanel.setFormListener(formListener);

        // Access components in LoginFormPanel for testing
        emailField = (JTextField) getComponentByType(loginFormPanel, JTextField.class);
        passField = (JPasswordField) getComponentByType(loginFormPanel, JPasswordField.class);
    }

    private Component getComponentByType(JPanel panel, Class<?> componentType) {
        for (Component comp : panel.getComponents()) {
            if (componentType.isInstance(comp)) {
                return comp;
            }
        }
        return null;
    }

    @After
    public void tearDown() {
        loginFormPanel = null;
        emailField = null;
        passField = null;
    }

    @Test
    public void testInitialState() {
        // Test the initial state of the form
        assertNotNull(emailField);
        assertNotNull(passField);

        // Verify that the "Password" placeholder is in the password field initially
        assertEquals("Password", new String(passField.getPassword()));

        // Verify that the email field has the default text
        assertEquals("user@example.com", emailField.getText());
    }

    @Test
    public void testEmailFieldBehavior() {

        emailField.setText("user@example.com");
        assertEquals("user@example.com", emailField.getText());
        
        emailField.setText("");
        assertEquals("", emailField.getText());
    }

    @Test
    public void testPasswordFieldBehavior() {
        passField.setText("Password");
        assertEquals("Password", new String(passField.getPassword()));

        passField.setText("");
        assertEquals("", new String(passField.getPassword()));
    }

}
