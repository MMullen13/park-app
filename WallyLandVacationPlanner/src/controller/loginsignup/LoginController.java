package controller.loginsignup;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.function.Consumer;
import model.loginsignup.AgeEnum;
import model.loginsignup.UserDatabase;
import model.loginsignup.LoginFormEvent;
import model.loginsignup.NewUser;
import model.loginsignup.ProfileUser;
import model.loginsignup.uservalidator.NameValidator;
import model.loginsignup.uservalidator.PhoneNumberValidator;
import model.loginsignup.RegisterFormEvent;
import model.loginsignup.User;
import model.loginsignup.UserFileService;
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

    MainPageView mainPage;
    private UserDatabase dataBase;
    LoginView loginView;
    RegisterView registerView;
    private PhoneNumberValidator phoneValidator;
    private NameValidator nameValidator;
    private EmailValidator emailValidator;
    private PasswordValidator passwordValidator;
    private NewUser newUser;
    private UserFileService userFileService;
    private String validEmail;
    private String validPassword;

    /**
     * Constructor
     */
    public LoginController() {
        dataBase = new UserDatabase();
        phoneValidator = new PhoneNumberValidator();
        nameValidator = new NameValidator();
        emailValidator = new EmailValidator();
        passwordValidator = new PasswordValidator();
        userFileService = new UserFileService();
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
        int ageID = ev.getAgeID();
        String phoneNum = ev.getPhone();

        AgeEnum age = null;

        // Use the updated validateCredentials to capture validated data
        validEmail = validateCredentials(email, emailValidator, registerView::clearEmailError, registerView::displayEmailError);
        validPassword = validateCredentials(password, passwordValidator, registerView::clearPasswordError, registerView::displayPasswordError);
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
        System.out.println("Age: " + age);

        newUser = new NewUser(validEmail, validPassword);
        newUser.setFirstName(validFirstName);
        newUser.setLastName(validLastName);
        newUser.setAge(age);
        newUser.setPhoneNum(validNumber);

        userFileService.addUserToFile(newUser);

    }


    private boolean login(String email, String password) {
        // Use UserFileService to check if credentials exist in the file
        return userFileService.checkCredentials(email, password);
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

    /**
     * Validates raw input data using the provided validator. Clears any
     * existing error messages and displays new error messages if validation
     * fails.
     *
     * @param rawData The raw data to be validated.
     * @param validator The validator used for data validation.
     * @param clearError A runnable to clear existing error messages.
     * @param displayError A consumer to display validation error messages.
     * @return The validated data, or an empty string if validation fails.
     */
    private String validateCredentials(String rawData, ValidatorIF validator, Runnable clearError, Consumer<String> displayError) {
        try {
            // validate the raw data using the provided validator
            String validatedData = validator.validate(rawData);
            clearError.run();  // clear any existing error message
            return validatedData;
        } catch (IllegalArgumentException ex) {
            System.out.println("Validation failed: " + ex.getMessage());  // debug output
            displayError.accept(ex.getMessage());  // display the specific validation error
            return "";  // return an empty string if validation fails
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
