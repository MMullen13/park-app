package controller.loginsignup;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.function.Consumer;
import model.loginsignup.AgeEnum;
import model.loginsignup.UserDatabase;
import model.loginsignup.LoginFormEvent;
import model.loginsignup.uservalidator.NameValidator;
import model.loginsignup.uservalidator.PhoneNumberValidator;
import model.loginsignup.RegisterFormEvent;
import model.loginsignup.User;
import model.loginsignup.uservalidator.EmailValidator;
import model.loginsignup.uservalidator.PasswordValidator;
import model.loginsignup.uservalidator.ValidatorIF;
import view.MainPageView;
import view.loginsignup.login.LoginView;
import view.loginsignup.signup.RegisterView;

/**
 * Controller handling login and registration logic.
 *
 * @author Ana
 */
public class LoginController {

    private MainPageView mainPage;
    private UserDatabase dataBase;
    private LoginView loginView;
    private RegisterView registerView;
    private PhoneNumberValidator phoneValidator;
    private NameValidator nameValidator;
    private EmailValidator emailValidator;
    private PasswordValidator passwordValidator;

    /**
     * Constructor initializes the user database and the phone phoneValidator
     */
    public LoginController() {
        dataBase = new UserDatabase();
        phoneValidator = new PhoneNumberValidator();
        nameValidator = new NameValidator();
        emailValidator = new EmailValidator();
        passwordValidator = new PasswordValidator();
    }

    /**
     * Sets the user data in the database by providing a list of users.
     *
     * @param users A list of User objects to populate the database.
     */
    public void setUserData(ArrayList<User> users) {
        dataBase.setUsers(users);
    }

    /**
     * Retrieves the list of users from the database.
     *
     * @return An ArrayList of User objects currently stored in the database.
     */
    public ArrayList<User> getUser() {
        return dataBase.getUsers();
    }

    /**
     * Sets the login view to be used by this controller.
     *
     * @param loginView The LoginView object representing the login interface.
     */
    public void setLoginView(LoginView loginView) {
        this.loginView = loginView;
    }

    /**
     * Sets the registration view to be used by this controller.
     *
     * @param registerView The RegisterView object representing the registration
     * interface.
     */
    public void setRegisterView(RegisterView registerView) {
        this.registerView = registerView;
    }

    /**
     * Handles user login by validating the provided credentials. If valid, it
     * closes the login view and opens the main page. If invalid, it displays an
     * error message.
     *
     * @param ev The LoginFormEvent containing the user's login credentials.
     */
    public void handleProfileUser(LoginFormEvent ev) {
        String email = ev.getEmail();
        String password = ev.getPassword();

        System.out.println(email + "\n" + password + "\n");

        if (login(email, password)) {
            loginView.dispose(); //dispose the login view frame 
            mainPage = new MainPageView();
            mainPage.setVisible(true);
        } else {
            System.out.println("Invalid Login Credentials\n");
        }

    }

    /**
     * Handles the creation of a new user based on the information provided in a
     * registration event. This method extracts the user details, maps the age
     * ID to an age category, and prints the user details for testing purposes.
     *
     * @param ev The RegisterFormEvent object containing user details from the
     * registration form.
     */
    public void handleNewUser(RegisterFormEvent ev) {
        // Extract user information from the registration form event
        String email = ev.getEmail();
        String password = ev.getPassword();
        String firstName = ev.getFirstName();
        String lastName = ev.getLastName();
        int ageID = ev.getAge();
        String phoneNum = ev.getPhone();

        AgeEnum age;

        // Use the updated validateCredentials to capture validated data
        String validEmail = validateCredentials(email, emailValidator, registerView::clearEmailError, registerView::displayEmailError);
        String validPassword = validateCredentials(password, passwordValidator, registerView::clearPasswordError, registerView::displayPasswordError);
        String validFirstName = validateCredentials(firstName, nameValidator, registerView::clearFirstNameError, registerView::displayFirstNameError);
        String validLastName = validateCredentials(lastName, nameValidator, registerView::clearLastNameError, registerView::displayLastNameError);
        String validNumber = validateCredentials(phoneNum, phoneValidator, registerView::clearPhoneError, registerView::displayPhoneError);

        // Determine the age category based on ageID and map it to AgeEnum
        switch (ageID) {
            case 0 ->
                age = AgeEnum.toddler;
            case 1 ->
                age = AgeEnum.child;
            case 2 ->
                age = AgeEnum.adult;
            case 3 ->
                age = AgeEnum.senior;
        }

        System.out.println(validEmail);
        System.out.println(validPassword);
        System.out.println(validFirstName);
        System.out.println(validLastName);
        System.out.println(validNumber);
    }

    //hard coded email and pasword for testing purposes
    private boolean login(String email, String password) {
        //To Do
        return "user@example.com".equals(email) && "password".equals(password);
    }

    /**
     * Validates login credentials by checking if the provided email and
     * password match any existing user in the database.
     *
     * @param email The email address entered by the user.
     * @param password The password entered by the user.
     * @return true if a user with matching email and password is found, false
     * otherwise.
     */
    private boolean validateLogin(String email, String password) {
        // Iterate over each user in the database to find a matching email and password
        for (User user : dataBase.getUsers()) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    private String validateCredentials(String rawData, ValidatorIF validator, Runnable clearError, Consumer<String> displayError) {
        try {
            // Validate the raw data using the provided validator
            String validatedData = validator.validate(rawData);
            clearError.run();  // Clear any existing error message
            return validatedData;
        } catch (IllegalArgumentException ex) {
            System.out.println("Validation failed: " + ex.getMessage());  // Debug output
            displayError.accept(ex.getMessage());  // Display the specific validation error
            return "";  // Return an empty string if validation fails
        }
    }

    /**
     * Saves the current state of the user database to a specified file.
     *
     * @param file The file to save the user data to.
     * @throws IOException If an I/O error occurs during file writing.
     */
    public void saveToFile(File file) throws IOException {
        dataBase.saveToFile(file);
    }

    /**
     * Loads user data from a specified file into the user database.
     *
     * @param file The file from which to load the user data.
     * @throws IOException If an I/O error occurs during file reading.
     */
    public void loadFromFile(File file) throws IOException {
        dataBase.loadFromFile(file);
    }
}
