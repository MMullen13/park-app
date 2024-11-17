package controller.loginsignup;

import model.loginsignup.LoginFormEvent;
import model.loginsignup.NewUser;
import model.loginsignup.ProfileUser;
import model.loginsignup.RegisterFormEvent;
import model.loginsignup.User;
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
public class LoginControllerIntegrationTest {

    private LoginController loginController;
    private LoginView loginView;
    private RegisterView registerView;
    private User user;

    @Before
    public void setUp() {
        loginController = new LoginController();
        loginView = new LoginView();
        registerView = new RegisterView();
        loginController.setLoginView(loginView);
        loginController.setRegisterView(registerView);      
    }

    @After
    public void tearDown() {
        // Clean up after each test (if needed)
        loginController = null;
        loginView.dispose();
        registerView.dispose();
    }

    @Test
    public void testUserRegistrationAndLogin() {
        user = new NewUser("testuser@example.com", "ValidPass123");
        RegisterFormEvent registrationEvent = new RegisterFormEvent(this, user);
        loginController.handleNewUser(registrationEvent);

        user = new ProfileUser("testuser@example.com", "ValidPass123");
        LoginFormEvent loginEvent = new LoginFormEvent(this, user);
        loginController.handleProfileUser(loginEvent);

        assertNotNull(loginController.getLoginView());
        assertNull(loginController.mainPage);
    }
    
    @Test
    public void testInvalidLogin() {
        user = new ProfileUser("testuser@example.com", "InvalidPass123");
        LoginFormEvent invalidLoginEvent = new LoginFormEvent(this, user);
        loginController.handleProfileUser(invalidLoginEvent);

        // Verify the login view remains open and no main page is displayed
        assertNotNull(loginController.getLoginView());
        assertNull(loginController.mainPage);
    }
}

