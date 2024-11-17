package model.loginsignup;

/**
 * NewUser class represents a new user in the system, extending the User class.
 * It includes personal details such as first name, last name, phone number, age, and a unique ID.
 * The ID is automatically generated and incremented for each new instance.
 * 
 * @author Ana
 */
public class NewUser extends User {

    private String firstName; // User's first name
    private String lastName;  // User's last name
    private String phoneNum;  // User's phone number
    private int ageID;          // User's ageID
    private AgeEnum age;
    private int id;           // Unique ID for the user
    private static int count = 0; // Static counter to create unique IDs for each user

    /**
     * Constructor to create a NewUser with specified email and password.
     * Automatically assigns a unique ID by incrementing the count.
     * 
     * @param email The user's email address.
     * @param password The user's password.
     */
    public NewUser(String email, String password) {
        super(email, password); // Initialize email and password using the superclass constructor
        this.id = count;        // Assign the current count as the user's ID
        count++;                // Increment count for the next user's ID
        
    }
    

    /**
     * Sets the user's first name.
     * 
     * @param firstName The user's first name.
     */
    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Sets the user's last name.
     * 
     * @param lastName The user's last name.
     */
    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Sets the user's phone number.
     * 
     * @param phoneNum The user's phone number.
     */
    @Override
    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    /**
     * Sets the user's ageID.
     * 
     * @param age The user's ageID.
     */
    @Override
    public void setAgeID(int age) {
        this.ageID = age;
    }

    /**
     * Gets the user's first name.
     * 
     * @return The user's first name.
     */
    @Override
    public String getFirstName() {
        return firstName;
    }

    /**
     * Gets the user's last name.
     * 
     * @return The user's last name.
     */
    @Override
    public String getLastName() {
        return lastName;
    }

    /**
     * Gets the user's phone number.
     * 
     * @return The user's phone number.
     */
    @Override
    public String getPhoneNum() {
        return phoneNum;
    }

    /**
     * Gets the user's ageID.
     * 
     * @return The user's ageID.
     */
    @Override
    public int getAgeID() {
        return ageID;
    }

    /**
     * Gets the user's unique ID.
     * 
     * @return The user's unique ID.
     */
    public int getID() {
        return id;
    }
    
    @Override
    public void setAge(AgeEnum age){
        this.age = age;
    }
    
    @Override
    public AgeEnum getAgeCategory(){
        return age;
    }

}

