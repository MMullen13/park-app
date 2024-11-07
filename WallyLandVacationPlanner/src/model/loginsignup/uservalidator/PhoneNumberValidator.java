package model.loginsignup.uservalidator;

/**
 * The validateAndFormat method accepts a String unformatted telephone number
 * such as 2151234567 and returns a String containing a formatted telephone
 * number such as (215)123-4567
 *
 * @author Ana
 */
public class PhoneNumberValidator implements ValidatorIF {

    /**
     * The validateAndFormat method accepts an unformatted String telephone
     * number such as 2151234567 and returns a String containing a formatted
     * telephone number such as (215)123-4567
     *
     * @param number
     * @return
     */
    @Override
    public String validate(String input) throws IllegalArgumentException {
        // Validate the input number length (assume 10 digits for simplicity)
        if (input == null || input.length() != 13) {
            throw new IllegalArgumentException("Phone number must be 13 characters.");
        }

        // Check for invalid characters using regular expression
        if (!input.matches("\\(\\d{3}\\)\\d{3}-\\d{4}")) {
            throw new IllegalArgumentException("Invalid phone number format.");
        }

        return input;
    }
}
