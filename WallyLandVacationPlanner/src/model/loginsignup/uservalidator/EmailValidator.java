package model.loginsignup.uservalidator;

/**
 * EmailValidator class implements the ValidatorIF interface to validate email addresses.
 * The class validates the email format using a regular expression.
 * It ensures that the email contains only valid characters and follows the general structure of an email.
 * 
 * @author Ana
 */
public class EmailValidator implements ValidatorIF {

    /**
     * Validates the provided email string.
     * The email should contain an "@" symbol and a domain name, along with a valid format.
     * 
     * @param email The email address to be validated.
     * @return The valid email if it matches the regex pattern.
     * @throws IllegalArgumentException if the email format is invalid.
     */
    @Override
    public String validate(String email) throws IllegalArgumentException {
//        // Check if the email is null or empty
//        if (email == null || email.trim().isEmpty()) {
//            throw new IllegalArgumentException("Email cannot be empty.");
//        }

        // Regex for validating the general structure of an email address
        String emailRegex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";

        if(!email.matches(emailRegex)){
            throw new IllegalArgumentException("Invalid email format.");
        }
        // Return the email if it's valid
        return email;
    }
}

