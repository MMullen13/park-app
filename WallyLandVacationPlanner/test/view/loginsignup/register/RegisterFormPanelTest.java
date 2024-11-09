package view.loginsignup.register;

import java.awt.Component;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import view.loginsignup.RegisterFormListenerIF;

/**
 *
 * @author Ana
 */
public class RegisterFormPanelTest {

    RegisterFormPanel registerFormPanel;
    RegisterFormListenerIF formListener;
    JTextField emailField;
    JTextField passField;
    JTextField firstNameField;
    JTextField lastNameField;
    JTextField phoneField;
    

    
    @Before
    public void setUp() {
        registerFormPanel = new RegisterFormPanel();
        registerFormPanel.setFormListener(formListener);

        // Access components in LoginFormPanel for testing
        emailField = (JTextField) getComponentByType(registerFormPanel, JTextField.class);
        passField = (JTextField) getComponentByType(registerFormPanel, JTextField.class);
        firstNameField = (JTextField) getComponentByType(registerFormPanel, JTextField.class);
        lastNameField = (JTextField) getComponentByType(registerFormPanel, JTextField.class);
        phoneField = (JTextField) getComponentByType(registerFormPanel, JTextField.class);
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
        registerFormPanel = null;
        emailField = null;
        passField = null;
        firstNameField = null;
        lastNameField = null;
        phoneField = null;
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
        assertEquals("Password", passField.getText());

        passField.setText("");
        assertEquals("", passField.getText());
    }
    
        @Test
    public void testFirstNameFieldBehavior() {

        emailField.setText("John");
        assertEquals("John", emailField.getText());
        
        emailField.setText("");
        assertEquals("", emailField.getText());
    }

    @Test
    public void testLastNameFieldBehavior() {
        passField.setText("Doe");
        assertEquals("Doe", passField.getText());

        passField.setText("");
        assertEquals("", passField.getText());
    }

    @Test
    public void testPhoneFieldBehavior() {
        passField.setText("(222)123-2345");
        assertEquals("(222)123-2345", passField.getText());

        passField.setText("");
        assertEquals("", passField.getText());
    }
}

