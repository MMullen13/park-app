package controller.parkmap;

import model.parkmap.ParkMap;
import model.parkmap.Location;
import view.parkmap.ParkMapView;
import java.util.List;

public class ParkMapController {

    private final ParkMap parkMap; // The model
    private final ParkMapView view; // The view

    public ParkMapController() {
        // Initialize the model and view
        this.parkMap = new ParkMap();
        this.view = new ParkMapView(this);
        this.view.setVisible(false);
    }

    public ParkMapView getView() {
        return view;
    }

    // Fetch and provide map data to the view
    public void displayMapWithBusyLocations() {
        List<Location> locations = parkMap.getLocations();
        view.displayMap(locations);
    }

    // Allow the view to request the name or status of a location
    public String getLocationStatus(String locationName) {
        for (Location loc : parkMap.getLocations()) {
            if (loc.getName().equalsIgnoreCase(locationName)) {
                return loc.isBusy() ? "Long" : "Short";
            }
        }
        return "Location not found";
    }
}