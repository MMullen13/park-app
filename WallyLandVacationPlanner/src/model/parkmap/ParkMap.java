package model.parkmap;

import java.util.ArrayList;
import java.util.List;

/**
 * MODEL CLASS: Represents the overall park map, containing a collection of locations.
 * @author Becky
 */

public class ParkMap {

    private final List<Location> locations; //A list of all locations in the park

    /**
     * Constructs a new ParkMap and initializes it with sample locations.
     * The sample data includes a mix of visible and hidden locations.
     */
    public ParkMap() {
        locations = new ArrayList<>();
        locations.add(new Location("Restroom", "X594,Y52", true, false));
        locations.add(new Location("Curly Water Slide", "X727,Y75", true, true));
        locations.add(new Location("Rocket Water Slide", "X444,Y46", true, true));
        locations.add(new Location("Dinos Water Slide", "X249,Y89", false, true));
        locations.add(new Location("Water Slide Fiesta", "X222,Y149", false, true));
        locations.add(new Location("Kiddy Pool", "X462,Y209", false, true));
        locations.add(new Location("Grill & Chill", "X131,Y325", false, false));
        locations.add(new Location("Lockers", "X157,Y411", false, false));
        locations.add(new Location("Restroom", "X187,Y424", false, false));
        locations.add(new Location("First Aid", "X220,Y443", false, false));
        locations.add(new Location("Tickets", "X164,Y481", false, false));
        locations.add(new Location("Parking", "X380,Y637", false, false));
        locations.add(new Location("Wave Pool", "X519,Y492", false, true));
        locations.add(new Location("Bella Bistro", "X697,Y689", false, false));
        locations.add(new Location("Cabana", "X858,Y597", false, false));
        locations.add(new Location("Dino Pond", "X1057,Y533", true, true));
        locations.add(new Location("Threater", "X858,Y290", false, false));
        locations.add(new Location("Restroom", "X897,Y289", false, false));
    }

    /**
     * Retrieves the list of all locations in the park.
     * @return a list of Location objects.
     */
    public List<Location> getLocations() {
        return locations;
    }
}