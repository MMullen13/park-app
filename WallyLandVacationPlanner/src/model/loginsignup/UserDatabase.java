package model.loginsignup;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ana
 */
public class UserDatabase {
    private ArrayList<User> parkVisitor;

    public UserDatabase() {       
        parkVisitor = new ArrayList<User>();
    }
    
    public void addUser(User user){
        parkVisitor.add(user);
    }
    
    public void setUsers(ArrayList<User> users){
        this.parkVisitor = users;
    }
    
    public ArrayList<User> getUsers(){
        return parkVisitor;
    }
    
    public void saveToFile(File file) throws IOException{
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        
        User[] users = parkVisitor.toArray(new User[parkVisitor.size()]);
        oos.writeObject(users);
        oos.close();
    }
    
    public void loadFromFile(File file) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);
        
        try {
            User[] users = (User[])ois.readObject();
            
            parkVisitor.clear();
            parkVisitor.addAll(Arrays.asList(users));
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        ois.close();
    }
    
}
