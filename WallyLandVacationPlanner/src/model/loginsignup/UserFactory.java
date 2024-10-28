package model.loginsignup;

/**
 *
 * @author Ana
 */
public class UserFactory {
    
    public static UserIF createUser(String email, String password, boolean isNewUser){
        if(isNewUser){
            return new NewUser(email, password);
        } else {
            return new ProfileUser(email, password);
        }
    }
    
}
