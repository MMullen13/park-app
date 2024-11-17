package model.loginsignup.uservalidator;

/**
 * This is a functional interface that defines a contract for any validator
 * class. The validate method should be implemented to perform validation on the
 * provided input and throw an IllegalArgumentException if the input is invalid.
 *
 * @author Ana
 */
public interface ValidatorIF {

    /**
     * Validates the given input and returns the validated result as a string.
     *
     * @param input The input string to be validated.
     * @return The validated input (can be a formatted or modified string).
     * @throws IllegalArgumentException if the input is invalid.
     */
    String validate(String input) throws IllegalArgumentException;
}
