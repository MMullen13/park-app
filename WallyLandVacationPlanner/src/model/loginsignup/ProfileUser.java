package model.loginsignup;

/**
 * ProfileUser class represents a user profile, inheriting from the User class.
 * This class can be used for accessing user profile-related information.
 * However, specific methods are not implemented and throw UnsupportedOperationException,
 * indicating that these functionalities are not yet supported in this class.
 * 
 * @author Ana
 */
public class ProfileUser extends User {

    /**
     * Constructor to create a ProfileUser with specified email and password.
     * 
     * @param email The user's email address.
     * @param password The user's password.
     */
    public ProfileUser(String email, String password) {
        super(email, password); // Call superclass constructor to initialize email and password
    }

    /**
     * Method to get the user's first name.
     * Currently, this method is not implemented and will throw an exception if called.
     * 
     * @return UnsupportedOperationException when called.
     */
    @Override
    public String getFirstName() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Method to get the user's last name.
     * Currently, this method is not implemented and will throw an exception if called.
     * 
     * @return UnsupportedOperationException when called.
     */
    @Override
    public String getLastName() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Method to get the user's phone number.
     * Currently, this method is not implemented and will throw an exception if called.
     * 
     * @return UnsupportedOperationException when called.
     */
    @Override
    public String getPhoneNum() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Method to get the user's age.
     * Currently, this method is not implemented and will throw an exception if called.
     * 
     * @return UnsupportedOperationException when called.
     */
    @Override
    public int getAgeID() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Method to set the user's first name.
     * Currently, this method is not implemented and will throw an exception if called.
     * 
     * @param firstName The user's first name.
     */
    @Override
    public void setFirstName(String firstName) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Method to set the user's last name.
     * Currently, this method is not implemented and will throw an exception if called.
     * 
     * @param lastName The user's last name.
     */
    @Override
    public void setLastName(String lastName) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Method to set the user's phone number.
     * Currently, this method is not implemented and will throw an exception if called.
     * 
     * @param phoneNum The user's phone number.
     */
    @Override
    public void setPhoneNum(String phoneNum) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Method to set the user's age id.
     * Currently, this method is not implemented and will throw an exception if called.
     * 
     * @param age id The user's age.
     */
    @Override
    public void setAgeID(int age) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Method to set the user's age.
     * Currently, this method is not implemented and will throw an exception if called.
     * 
     * @param age The user's age.
     */
    @Override
    public void setAge(AgeEnum age) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
