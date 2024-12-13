package view.parkmap;

import controller.parkmap.ParkMapController;
import model.parkmap.Location;
import javax.swing.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.awt.*;
import java.util.Arrays;

public class ParkMapViewTest {
    public ParkMapViewTest() {
    }

    private ParkMapView view;
    private ParkMapController controller;

    @Before
    public void setUp() {
        controller = new ParkMapController(); //Create controller
        view = new ParkMapView(controller); //Create view
    }

    @Test
    public void testDisplayMap() {
        Location loc1 = new Location("Rocket Slide", "X200,Y300", true, true);
        Location loc2 = new Location("Kiddy Pool", "X100,Y200", false, true);

        view.displayMap(Arrays.asList(loc1, loc2));

        Component[] components = view.getMapPanel().getComponents();
        assertEquals(2, components.length);

        JButton button1 = (JButton) components[0];
        JButton button2 = (JButton) components[1];

        assertEquals(Color.RED, button1.getBackground());
        assertEquals(Color.GREEN, button2.getBackground());
    }

    @Test
    public void testDisplayMap_HiddenLocationsNotVisible() {
        Location loc = new Location("Hidden Restroom", "X300,Y400", false, false);

        view.displayMap(Arrays.asList(loc));

        Component[] components = view.getMapPanel().getComponents();
        assertEquals(0, components.length); // Button is not added for hidden locations
    }
}
