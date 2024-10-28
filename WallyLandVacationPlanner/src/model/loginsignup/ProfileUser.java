package model.loginsignup;


/**
 *
 * @author Ana
 */
public class ProfileUser extends User{

    private String email;
    private String password;

    public ProfileUser( String email, String password) {
        super(email, password);
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

}
