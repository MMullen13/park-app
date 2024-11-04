package model.loginsignup;

import java.util.ArrayList;

/**
 *
 * @author Ana
 */
public class UserDatabase {
    private ArrayList<User> users;

    public UserDatabase() {       
        users = new ArrayList<User>();
    }
    
    public void addUser(NewUser user){
        users.add(user);
    }
    
    public ArrayList<User> getUsers(){
        return users;
    }
}
