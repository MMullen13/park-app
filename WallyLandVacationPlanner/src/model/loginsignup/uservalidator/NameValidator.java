package model.loginsignup.uservalidator;

public class NameValidator implements ValidatorIF {

    /**
     * Validates if the name contains only alphabetic characters and certain
     * valid special characters.
     *
     * @param name The name to validate (e.g., first name or last name).
     * @return true if the name is valid, false otherwise.
     *
     * @author Ana
     */
    @Override
    public String validate(String name) {
        // Check for null or empty names
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }

        // Regex to allow only alphabetic characters and spaces
        String regex = "^[a-zA-Z\\s]+$";

        if (!name.matches(regex)) {
            throw new IllegalArgumentException("Name can only contain letters and spaces.");
        }

        // Capitalize the first letter of each word and lowercase the rest
        name = name.trim().toLowerCase();
        String[] parts = name.split("\\s+");
        StringBuilder formattedName = new StringBuilder();

        for (String part : parts) {
            if (part.length() > 0) {
                formattedName.append(Character.toUpperCase(part.charAt(0)))
                        .append(part.substring(1)).append(" ");
            }
        }

        return formattedName.toString().trim();
    }
}
