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
public class LoginControllerTest {

    LoginController instance;
    LoginView loginView;
    RegisterView registerView;

    @Before
    public void setUp() {
        instance = new LoginController();
        loginView = new LoginView();
        registerView = new RegisterView();
    }

    @After
    public void tearDown() {
        instance = null;
        loginView = null;
        registerView = null;
    }

    /**
     * Test of setLoginView method, of class LoginController.
     */
    @Test
    public void testSetLoginView() {
        System.out.println("setLoginView");

        instance.setLoginView(loginView);
        LoginView result = instance.getLoginView();
        assertEquals("LoginView should be set correctly", loginView, result);
    }

    /**
     * Test of setRegisterView method, of class LoginController.
     */
    @Test
    public void testSetRegisterView() {
        System.out.println("setRegisterView");

        instance.setRegisterView(registerView);
        RegisterView result = instance.getRegisterView();
        assertEquals("LoginView should be set correctly", registerView, result);
    }

}
