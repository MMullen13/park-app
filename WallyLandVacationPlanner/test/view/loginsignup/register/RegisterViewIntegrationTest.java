package view.loginsignup.register;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ana
 */
public class RegisterViewIntegrationTest {
    
    private RegisterView registerView;
    private RegisterFormPanel registerFormPanel;
    
    @Before
    public void setUp() {
        registerView = new RegisterView();
        registerFormPanel = new RegisterFormPanel();
    }
    
    @After
    public void tearDown() {
        registerView.dispose();
        registerFormPanel = null;
    }

    @Test
    public void testRegisterViewInitialization() {
        assertNotNull("Register view should have a form panel initialized", registerView.formPanel);
        assertNotNull("Register view should have a controller initialized", registerView.controller);
    }
    
    @Test
    public void testDisplayPhoneError() {
        registerView.displayPhoneError("Invalid Phone Format");
        assertEquals("Invalid Phone Format", registerView.formPanel.phoneErrorLabel.getText());
        assertEquals(java.awt.Color.RED, registerView.formPanel.phoneErrorLabel.getForeground());
    }
    
    @Test
    public void testDisplayFirstNameError() {
        registerView.displayFirstNameError("Invalid First Name");
        assertEquals("Invalid First Name", registerView.formPanel.firstNameErrorLabel.getText());
        assertEquals(java.awt.Color.RED, registerView.formPanel.firstNameErrorLabel.getForeground());
    }
    
    @Test
    public void testDisplayLastNameError() {
        registerView.displayLastNameError("Invalid Last Name");
        assertEquals("Invalid Last Name", registerView.formPanel.lastNameErrorLabel.getText());
        assertEquals(java.awt.Color.RED, registerView.formPanel.lastNameErrorLabel.getForeground());
    }

    @Test
    public void testClearPhoneError() {
        registerView.displayPhoneError("Invalid Phone Format");
        registerView.clearPhoneError();
        assertEquals("", registerView.formPanel.phoneErrorLabel.getText());
    }
    
    @Test
    public void testDefaultTextFieldValues() {
        assertEquals("Email", registerFormPanel.emailField.getText());
        assertEquals("Password", registerFormPanel.passwordField.getText());
        assertEquals("First Name", registerFormPanel.firstNameField.getText());
        assertEquals("Last Name", registerFormPanel.lastNameField.getText());
        assertEquals("(215) 123-4567", registerFormPanel.phoneField.getText());
    }
    
    @Test
    public void testErrorMessagesClearedInitially() {
        assertEquals("", registerFormPanel.phoneErrorLabel.getText());
        assertEquals("", registerFormPanel.firstNameErrorLabel.getText());
        assertEquals("", registerFormPanel.emailErrorLabel.getText());
    }
    
     @Test
    public void testSetFormListener() {
        registerFormPanel.setFormListener(e -> {});
        assertNotNull("Form listener should be set", registerFormPanel.formListener);
    }
}
