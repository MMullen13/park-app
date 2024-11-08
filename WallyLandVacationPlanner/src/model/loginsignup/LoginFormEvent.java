package model.loginsignup;

import java.util.EventObject;

/**
 * Represents a user-related event in the login/sign up process. This event is
 * triggered when a user action occurs, such as attempting to log in or
 * registering a new account. It contains details about the user involved in the
 * event.
 *
 * The class extends from EventObject and encapsulates a UserIF object,
 * providing methods to access user information and determine the type of user
 * (new or existing).
 *
 * Additional utility methods are provided to check the user type and to get a
 * string representation of the event for logging or debugging purposes.
 *
 * Usage example:
 * <pre>
 * LoginFormEvent userEvent = new LoginFormEvent(this, user);
 * if (userEvent.isNewUser()) {
 * // Handle new user registration
 * }
 * </pre>
 *
 * @author Ana
 */
public class LoginFormEvent extends EventObject {

    private User user;

    /**
     * Constructor 
     * 
     * @param source
     * @param user 
     */
    public LoginFormEvent(Object source, User user) {
        super(source);

        this.user = user;
    }

    /**
     * User 
     *
     * @return user 
     */
    public User getUser() {
        return user;
    }

    /**
     * User Email getter
     *
     * @return user email of type String
     */
    public String getEmail() {
        return user.getEmail();
    }

    /**
     * User Password getter
     *
     * @return user password of type String
     */
    public String getPassword() {
        return user.getPassword();
    }

    /**
     * User First Name getter
     *
     * @return user first name of type String
     */
    public String getFirstName() {
        return user.getFirstName();
    }

    /**
     * User Last Name getter
     *
     * @return user last name of type String
     */
    public String getLastName() {
        return user.getLastName();
    }

    /**
     * User Phone Number getter
     *
     * @return user phone number of type String
     */
    public String getPhone() {
        return user.getPhoneNum();
    }

    /**
     * User Age getter
     *
     * @return user age of type Age
     */
    public int getAge() {
        return user.getAge();
    }

    /**
     * Returns a string representation of the user event. This can be used for
     * logging or debugging purposes.
     */
    @Override
    public String toString() {
        return "UserEvent [source=" + getSource() + ", email=" + getEmail();
    }
}
