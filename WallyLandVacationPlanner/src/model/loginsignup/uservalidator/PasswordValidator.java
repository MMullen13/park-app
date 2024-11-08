package model.loginsignup.uservalidator;

/**
 * This class implements the ValidatorIF interface to validate passwords. The
 * password must meet the following criteria: - At least 8 characters long -
 * Contains at least one uppercase letter - Contains at least one digit
 */
public class PasswordValidator implements ValidatorIF {

    /**
     * Validates the provided password.
     *
     * @param password The password entered by the user.
     * @return The valid password if it meets all criteria.
     * @throws IllegalArgumentException If the password doesn't meet the
     * validation criteria.
     */
    @Override
    public String validate(String password) throws IllegalArgumentException {
        // Check if the password is null or empty
        if (password == null || password.trim().isEmpty()) {
            // Throw exception if the password is empty
            throw new IllegalArgumentException("Password cannot be empty.");
        }

        int score = 0;

        // Check if the password is at least 8 characters long
        if (password.length() >= 8) {
            score++;
        }

        // Check if the password contains at least one uppercase letter
        if (password.matches("(?=.*[A-Z]).*")) {
            score++;
        }

        // Check if the password contains at least one lowercase letter 
        if (password.matches("(?=.*[a-z]).*")) {
            score++;
        }

        // Check if the password contains at least one digit
        if (password.matches(".*\\d.*")) {
            score++;
        }

        // Check if the password contains one special character
        if (password.matches("(?=.*[!@#$%^&*]).*")) {
            score++;
        }

        if (score < 4) {
            throw new IllegalArgumentException("Invalid Password");
        }

        // If all checks are passed, return the valid password
        return password;
    }
}
