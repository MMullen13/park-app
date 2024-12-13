package controller.parkmap;

import model.parkmap.ParkMap;
import model.parkmap.Location;
import view.parkmap.ParkMapView;
import java.util.List;

/**
 * CONTROLLER CLASS: Manages interaction between park map view and model.
 * @author Becky
 */

public class ParkMapController {

    private final ParkMap parkMap; //Contains park data
    private final ParkMapView view; //Controls GUI for the park map

    /**
     * Initializes park map controller, model, and view.
     */
    public ParkMapController() {
        this.parkMap = new ParkMap(); //Creates park map model
        this.view = new ParkMapView(this); //Creates park map view and binds it to this controller
        this.view.setVisible(false); //Set default view as hidden
    }

    /**
     * @return park map view associated with this controller
     */
    public ParkMapView getView() {
        return view;
    }

    /**
     * Updates the park map view with the latest map data, highlighting busy locations.
     */
    public void displayMapWithBusyLocations() {
        List<Location> locations = parkMap.getLocations(); //Get park map locations
        view.displayMap(locations); //Display map with park map locations
    }

    /**
     * Fetches the status of a specific location by name.
     * @param locationName the name of the location
     * @return "long" if wait time is long, "short" if wait time is short, or "Location not found" if no match
     */
    public String getLocationStatus(String locationName) {
        for (Location loc : parkMap.getLocations()) { //Cycle through locations
            if (loc.getName().equalsIgnoreCase(locationName)) { //If location name matches
                return loc.isBusy() ? "long" : "short"; //Return status
            }
        }
        return "Location not found"; //Handle cases where the location doesn't exist
    }
}