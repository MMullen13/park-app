package model.parkmap;

/**
 * MODEL CLASS: Represents a specific locaion on the park map
 * Each location has attributes for its name, coordinates, busy status, and visibility
 * @author Becky
 */

public class Location {
    private String name; //Name of location
    private String coordinates; //Location's position on park map in XY format
    private boolean isBusy; //Indicates if location is busy (long wait times)
    private boolean isVisible; //Indicates if location is visible on the map

    /**
     * Constructs a new Location with the specified properties.
     * @param name the name of the location.
     * @param coordinates the map coordinates of the location.
     * @param isBusy whether the location is busy.
     * @param isVisible whether the location is visible on the map.
     */
    public Location(String name, String coordinates, boolean isBusy, boolean isVisible) {
        this.name = name;
        this.coordinates = coordinates;
        this.isBusy = isBusy;
        this.isVisible = isVisible;
    }

    // Getters for the Location properties.

    /**
     * Gets the name of the location.
     * @return the name of the location.
     */
    public String getName() { return name; }

    /**
     * Gets the coordinates of the location.
     * @return the coordinates of the location.
     */
    public String getCoordinates() { return coordinates; }

    /**
     * Checks if the location is busy.
     * @return true if the location is busy; false otherwise.
     */
    public boolean isBusy() { return isBusy; }

    /**
     * Checks if the location is visible on the map.
     * @return true if the location is visible; false otherwise.
     */
    public boolean isVisible() { return isVisible; }
}
