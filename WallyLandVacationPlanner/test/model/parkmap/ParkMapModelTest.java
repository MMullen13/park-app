package model.parkmap;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;

public class ParkMapModelTest {
    public ParkMapModelTest() {
    }

    @Test
    public void testDefaultLocationsInitialization() {
        ParkMap parkMap = new ParkMap();
        List<Location> locations = parkMap.getLocations();

        assertFalse(locations.isEmpty());
        assertEquals(18, locations.size());
    }

    @Test
    public void testLocationProperties() {
        List<Location> locations = new ArrayList<>();
        locations.add(new Location("Wave Pool", "X300,Y500", true, true));
        
        assertEquals("Wave Pool", locations.get(0).getName());
        assertEquals("X300,Y500", locations.get(0).getCoordinates());
        assertTrue(locations.get(0).isBusy());
        assertTrue(locations.get(0).isVisible());
    }
}
