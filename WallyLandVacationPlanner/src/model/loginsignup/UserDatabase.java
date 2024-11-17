package model.loginsignup;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class manages the storage and retrieval of user data for the park visitors.
 * It handles saving the list of users to a file and loading the users from a file.
 * 
 * It uses Java's Object Serialization to persist and restore the users.
 * The users are stored in an ArrayList, which is an expandable list of User objects.
 * 
 * Author: Ana
 */
public class UserDatabase {
    private ArrayList<User> parkVisitor; // List to store the users (park visitors)

    /**
     * Constructor that initializes the parkVisitor list.
     */
    public UserDatabase() {       
        parkVisitor = new ArrayList<User>(); // Initialize the list of users
    }
    
    public void connect() throws Exception{
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
           throw new Exception("Driver not found");
        }
    }
    
    public void disconnect(){
        
    }
    
    /**
     * Adds a new user to the parkVisitor list.
     * 
     * @param user The User object to be added to the list.
     */
    public void addUser(User user){
        parkVisitor.add(user); // Adds the user to the list of park visitors
    }
    
    /**
     * Sets the list of users to a new list.
     * 
     * @param users The new list of User objects to replace the current list.
     */
    public void setUsers(ArrayList<User> users){
        this.parkVisitor = users; // Replace the existing list with the provided one
    }
    
    /**
     * Gets the current list of users.
     * 
     * @return The list of User objects stored in the parkVisitor.
     */
    public ArrayList<User> getUsers(){
        return parkVisitor; // Returns the list of users
    }
    
    /**
     * Saves the list of users to a file.
     * The users are serialized into the file in object form.
     * 
     * @param file The File object that specifies where to save the user data.
     * @throws IOException If an I/O error occurs during the writing process.
     */
    public void saveToFile(File file) throws IOException{
        // Create a FileOutputStream to write data to the specified file
        FileOutputStream fos = new FileOutputStream(file);
        
        // Create an ObjectOutputStream to write the list of users as objects
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        
        // Convert the list of users into an array and write it to the file
        User[] users = parkVisitor.toArray(new User[parkVisitor.size()]);
        oos.writeObject(users); // Write the array of users to the file
        
        oos.close(); // Close the ObjectOutputStream to finish writing
    }
    
    /**
     * Loads the list of users from a file.
     * The users are deserialized from the file back into the parkVisitor list.
     * 
     * @param file The File object that specifies the location of the user data to load.
     * @throws IOException If an I/O error occurs during the reading process.
     */
    public void loadFromFile(File file) throws IOException {
        // Create a FileInputStream to read from the specified file
        FileInputStream fis = new FileInputStream(file);
        
        // Create an ObjectInputStream to read objects from the file
        ObjectInputStream ois = new ObjectInputStream(fis);
        
        try {
            // Read the object from the file, expecting an array of User objects
            User[] users = (User[])ois.readObject();
            
            // Clear the existing list of park visitors and add the loaded users
            parkVisitor.clear();
            parkVisitor.addAll(Arrays.asList(users)); // Convert the array into a list and add it to the parkVisitor list
        } catch (ClassNotFoundException ex) {
            // Handle the exception if the class is not found
            Logger.getLogger(UserDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ois.close(); // Close the ObjectInputStream to finish reading
    }
}
