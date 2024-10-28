package controller.loginsignup;

import model.loginsignup.UserDatabase;
import model.loginsignup.UserFormEvent;
import view.MainPageView;
import view.loginsignup.login.LoginView;

/**
 *
 * @author Ana
 */
public class UserController {

    private MainPageView mainPage;
    private UserDatabase dataBase;
    private LoginView loginView; 

    public UserController() {
        dataBase = new UserDatabase();
    }
    
    public void setLoginView(LoginView loginView){
        this.loginView = loginView;
    }

    public void handleProfileUser(UserFormEvent ev) {
        String email = ev.getEmail();
        String password = ev.getPassword();
               

        System.out.println(email + "\n" + password + "\n");

        if (validateLogin(email, password)) {
            loginView.dispose();
            mainPage = new MainPageView();
            mainPage.setVisible(true);
        } else {
            System.out.println("Invalid Login Credentials\n");
        }
        
        
          
    }

    public void handleNewUser(UserFormEvent ev) {
        String email = ev.getEmail();
        String password = ev.getPassword();
//        String firstName = ev.getFirstName();
//        String lastName = ev.getLastName();

    }
    
        private boolean validateLogin(String email, String password){
        //To Do
        return "user@example.com".equals(email) && "password".equals(password);
    }
}
