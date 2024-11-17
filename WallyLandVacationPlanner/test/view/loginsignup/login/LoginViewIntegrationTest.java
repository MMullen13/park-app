package view.loginsignup.login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import model.loginsignup.AgeEnum;
import model.loginsignup.LoginFormEvent;
import model.loginsignup.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author aniut
 */
public class LoginViewIntegrationTest {

    private LoginView loginView;
    private LoginFormPanel loginFormPanel;

    @Before
    public void setUp() {
        loginView = new LoginView();
        loginFormPanel = loginView.formPanel;
    }

    @After
    public void tearDown() {
        loginView.dispose();
        loginFormPanel = null;
    }

    @Test
    public void testLoginViewInitialization() {
        // Check that formPanel and controller are initialized
        assertNotNull("LoginFormPanel should be initialized", loginFormPanel);
        assertNotNull("LoginController should be initialized", loginView.controller);

        // Verify components in formPanel
        JTextField emailField = loginFormPanel.emailField;
        assertNotNull("Email field should be initialized", emailField);
        assertNotNull("Password field should be initialized", loginFormPanel.passField);
        assertNotNull("Login button should be initialized", loginFormPanel.loginBtn);
    }

    @Test
    public void testLoginButtonClick() {
        // Set test email and password
        loginFormPanel.emailField.setText("testuser@example.com");
        loginFormPanel.passField.setText("password");

        // Attach a test listener to capture login button action
        loginFormPanel.loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = loginFormPanel.emailField.getText();
                String password = new String(loginFormPanel.passField.getPassword());

                // Simulate user creation and form event
                User user = new User(email, password) {
                    @Override
                    public void setFirstName(String firstName) {
                        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                    }

                    @Override
                    public void setLastName(String lastName) {
                        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                    }

                    @Override
                    public void setPhoneNum(String phoneNum) {
                        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                    }

                    @Override
                    public void setAgeID(int age) {
                        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                    }

                    @Override
                    public void setAge(AgeEnum age) {
                        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                    }
                };
                LoginFormEvent event = new LoginFormEvent(this, user);

                // Verify the login event data
                assertTrue("User email should be 'testuser@example.com'", email.equals("testuser@example.com"));
                assertTrue("User password should be 'password'", password.equals("password"));
            }
        });
        
        // Simulate button click
        loginFormPanel.loginBtn.doClick();
    }
    
    

}
