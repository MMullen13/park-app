package controller.loginsignup;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import model.loginsignup.AgeEnum;
import model.loginsignup.UserDatabase;
import model.loginsignup.LoginFormEvent;
import model.loginsignup.RegisterFormEvent;
import model.loginsignup.User;
import view.MainPageView;
import view.loginsignup.login.LoginView;
import view.loginsignup.signup.RegisterView;

/**
 *
 * @author Ana
 */
public class LoginController {

    private MainPageView mainPage;
    private UserDatabase dataBase;
    private LoginView loginView;
    private RegisterView registerView;

    public LoginController() {
        dataBase = new UserDatabase();
    }
    
    public void setUserData(ArrayList<User> users){
        dataBase.setUsers(users);
    }
    
    public ArrayList<User> getUser(){
        return dataBase.getUsers();
    }

    public void setLoginView(LoginView loginView) {
        this.loginView = loginView;
    }
    
    public void setRegisterView(RegisterView registerView){
        this.registerView = registerView;
    }

    public void handleProfileUser(LoginFormEvent ev) {
        String email = ev.getEmail();
        String password = ev.getPassword();

        System.out.println(email + "\n" + password + "\n");

        if (validateLogin(email, password)) {
            loginView.dispose(); //dispose the login view frame 
            mainPage = new MainPageView();
            mainPage.setVisible(true);
        } else {
            System.out.println("Invalid Login Credentials\n");
        }

    }

    public void handleNewUser(RegisterFormEvent ev) {
        String email = ev.getEmail();
        String password = ev.getPassword();
        String firstName = ev.getFirstName();
        String lastName = ev.getLastName();
        int ageID = ev.getAge();
        String phoneNum = ev.getPhone();

        AgeEnum age;

        switch (ageID) {
            case 0:
                age = AgeEnum.toddler;
                break;
            case 1:
                age = AgeEnum.child;
                break;
            case 2:
                age = AgeEnum.adult;
                break;
            case 3:
                age = AgeEnum.senior;
                break;
        }

        System.out.println(email + "\n" + password + "\n" + firstName + "\n" + lastName + "\n" + ageID + "\n" + phoneNum);


//        registerView.dispose();
//        loginView = new LoginView();
//        loginView.setVisible(true);
    }

    private boolean validateLogin(String email, String password) {
        //To Do
        return "user@example.com".equals(email) && "password".equals(password);
    }
    
    public void saveToFile(File file) throws IOException{
        dataBase.saveToFile(file);
    }
    
    public void loadFromFile(File file) throws IOException{
        dataBase.loadFromFile(file);
    }
}
