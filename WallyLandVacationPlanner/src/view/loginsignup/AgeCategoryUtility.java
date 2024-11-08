package view.loginsignup;

/**
 * An utility class for managing age category information.
 * This class is used to represent an age category with an associated id and descriptive text.
 * It provides methods to retrieve the id and convert the object to a string for display purposes.
 * 
 * Author: Ana
 */
public class AgeCategoryUtility {

    private int id; // The unique identifier for the age category
    private String text; // The textual representation of the age category

    /**
     * Constructor to create an AgeCategoryUtils object with an id and text.
     * 
     * @param id The unique identifier for the age category.
     * @param text The description or name of the age category.
     */
    public AgeCategoryUtility(int id, String text) {
        this.id = id; // Assign the id
        this.text = text; // Assign the textual description
    }

    /**
     * Gets the id of the age category.
     * 
     * @return The id of the age category.
     */
    public int getID() {
        return id; // Return the id of the age category
    }

    /**
     * Converts the AgeCategoryUtility object to a string representation.
     * 
     * @return The textual description of the age category.
     */
    @Override
    public String toString() {
        return text; // Return the textual description
    }
}
