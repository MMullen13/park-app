package model.loginsignup;

import java.util.ArrayList;

/**
 *
 * @author Ana
 */
public class UserDatabase {
    private ArrayList<NewUser> users;

    public UserDatabase() {       
        users = new ArrayList<>();
    }
    
    public void addUser(NewUser user){
        users.add(user);
    }
    
    public ArrayList<NewUser> getUsers(){
        return users;
    }
}
