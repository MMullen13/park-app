package model.loginsignup;

/**
 *
 * @author Ana
 */
public abstract class User implements UserIF{
    
    private String email;
    private String password;
    
    public User(String email, String password){
        this.email = email;
        this.password = password;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }
    
}
