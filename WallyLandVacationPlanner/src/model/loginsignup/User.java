package model.loginsignup;

import java.io.Serializable;

/**
 * The abstract User class represents a generic user in the system, implementing
 * the UserIF interface and Serializable interface to allow for user objects to be
 * serialized. This class contains common fields and methods related to user information,
 * such as email, password, and personal details. Concrete subclasses are expected to
 * implement specific behavior for setting personal details (e.g., first and last name).
 * 
 * Fields in this class include:
 * - `email`: The user's email address.
 * - `password`: The user's password, kept private for security.
 * - `firstName`, `lastName`: The user's first and last names.
 * - `phoneNumber`: The user's phone number.
 * - `age`: The user's age.
 * 
 * @author Ana
 */
public abstract class User implements UserIF, Serializable {

    // Fields to store user information
    String email; // User's email address
    private String password; // User's password, kept private
    private String firstName; // User's first name
    private String lastName; // User's last name
    private String phoneNumber; // User's phone number
    private int ageID; // User's ageID
    private AgeEnum age;

    /**
     * Constructor to initialize the User object with email and password.
     * 
     * @param email    The user's email address.
     * @param password The user's password.
     */
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    /**
     * Retrieves the user's email address.
     * 
     * @return The email of the user.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Retrieves the user's password.
     * 
     * @return The password of the user.
     */
    public String getPassword() {
        return password;
    }
    
    /**
     * Retrieves the user's first name.
     * 
     * @return The first name of the user.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Retrieves the user's last name.
     * 
     * @return The last name of the user.
     */
    public String getLastName() {
        return lastName;
    }
    
    /**
     * Retrieves the user's phone number.
     * 
     * @return The phone number of the user.
     */
    public String getPhoneNum() {
        return phoneNumber;
    }
    
    /**
     * Retrieves the user's ageID.
     * 
     * @return The ageID of the user.
     */
    public int getAgeID() {
        return ageID;
    }
    
    /**
     * Retrieves the user's age.
     * 
     * @return The age of the user.
     */
    public AgeEnum getAgeCategory(){
        return age;
    }

    /**
     * Abstract method for setting the user's first name.
     * This must be implemented by subclasses.
     * 
     * @param firstName The first name to set for the user.
     */
    public abstract void setFirstName(String firstName);

    /**
     * Abstract method for setting the user's last name.
     * This must be implemented by subclasses.
     * 
     * @param lastName The last name to set for the user.
     */
    public abstract void setLastName(String lastName);
    
    /**
     * Abstract method for setting the user's phone number.
     * This must be implemented by subclasses.
     * 
     * @param phoneNum The phone number to set for the user.
     */
    public abstract void setPhoneNum(String phoneNum);
    
    /**
     * Abstract method for setting the user's age id.
     * This must be implemented by subclasses.
     * 
     * @param age The ageID to set for the user.
     */
    public abstract void setAgeID(int age);
    
    /**
     * Abstract method for setting the user's age.
     * This must be implemented by subclasses.
     * 
     * @param age The age enumerator to set for the user.
     */
    public abstract void setAge(AgeEnum age);
}
