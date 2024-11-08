package model.loginsignup.uservalidator;

public class NameValidator implements ValidatorIF {

    /**
     * Validates if the name contains only alphabetic characters.
     *
     * @param name The name to validate (e.g., first name or last name).
     * @return The validated name if it meets the requirements.
     * @throws IllegalArgumentException if the name is invalid.
     */
    @Override
    public String validate(String name) {
        // Check for null or empty names
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }

        // Regex to allow only alphabetic characters, no spaces
        String regex = "^[a-zA-Z]+$";

        if (!name.matches(regex)) {
            throw new IllegalArgumentException("Invalid Name.");
        }

        //capitalize the first letter
        return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
    }
}

