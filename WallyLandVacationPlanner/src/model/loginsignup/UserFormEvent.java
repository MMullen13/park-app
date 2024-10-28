package model.loginsignup;

import java.util.EventObject;

/**
 * Represents a user-related event in the login/sign up process.
 * This event is triggered when a user action occurs, such as attempting to log in
 * or registering a new account. It contains details about the user involved in the event.
 * 
 * The class extends from EventObject and encapsulates a UserIF object,
 * providing methods to access user information and determine the type of user (new or existing).
 * 
 * Additional utility methods are provided to check the user type and to get
 * a string representation of the event for logging or debugging purposes.
 * 
 * Usage example:
 * <pre>
     UserFormEvent userEvent = new UserFormEvent(this, user);
     if (userEvent.isNewUser()) {
         // Handle new user registration
     }
 </pre>
 * 
 * @author Ana
 */

public class UserFormEvent extends EventObject {

    private UserIF user;

    public UserFormEvent(Object source, UserIF user) {
        super(source);

        this.user = user;
    }

    public UserIF getUser() {
        return user;
    }

    public String getEmail() {
        return user.getEmail();
    }

    public String getPassword() {
        return user.getPassword();
    }

    /**
     * Checks if the user is an existing user based on the type. This can be
     * useful to handle different actions for new and existing users.
     */
    public boolean isExistingUser() {
        return user instanceof ProfileUser;
    }

    /**
     * Checks if the user is a new user based on the type. This can be useful to
     * handle different actions for new and existing users.
     */
    public boolean isNewUser() {
        return user instanceof NewUser;
    }

    /**
     * Returns a string representation of the user event. This can be used for
     * logging or debugging purposes.
     */
    @Override
    public String toString() {
        return "UserEvent [source=" + getSource() + ", email=" + getEmail()
                + ", userType=" + (isNewUser() ? "NewUser" : "ExistingUser") + "]";
    }
}
