package model.loginsignup;

/**
 * Factory class for creating User objects.
 * This class provides a method to create different types of users
 * based on the input parameters.
 *
 * @author Ana
 */
public class UserFactory {
    
    /**
     * Factory method to create a User object.
     * Depending on the `isNewUser` flag, it creates either a NewUser or a ProfileUser.
     * 
     * @param email The email of the user.
     * @param password The password of the user.
     * @param isNewUser A Boolean flag indicating whether to create a NewUser or a ProfileUser.
     *                  If true, creates a NewUser. If false, creates a ProfileUser.
     * @return A UserIF object, which could be either a NewUser or a ProfileUser instance.
     */
    public static UserIF createUser(String email, String password, boolean isNewUser) {
        if (isNewUser) {
            // Create and return a new instance of NewUser
            return new NewUser(email, password);
        } else {
            // Create and return a new instance of ProfileUser
            return new ProfileUser(email, password);
        }
    }
}

