package model.loginsignup;

import java.util.EventObject;

/**
 *
 * @author Ana
 */
public class ProfileUser extends EventObject implements UserIF{

    private String email;
    private String password;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
 
    
    public ProfileUser(Object source, String email, String password) {
        super(source);
        
        this.email = email;
        this.password = password;
    }
    
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
