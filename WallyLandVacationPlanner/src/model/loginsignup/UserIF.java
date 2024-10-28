package model.loginsignup;

/**
 * User Interface for the WallyLand User
 *
 * @author Ana
 */
public interface UserIF {

    /**
     * Method to get the user's email
     *
     * @return String type email
     */
    public String getEmail();

    /**
     * Method to get the user's password
     *
     * @return String type password
     */
    public String getPassword();
}
