package controller.parkmap;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import view.parkmap.ParkMapView;

public class ParkMapControllerTest {
    public ParkMapControllerTest() {
    }

    private ParkMapController controller;
    @SuppressWarnings("unused")
    private ParkMapView view;

    @Before
    public void setUp() {
        controller = new ParkMapController(); //Create controller
        view = new ParkMapView(controller); //Create view
    }

    @Test
    public void testGetView() {
        assertNotNull(controller.getView());
    }

    @Test
    public void testGetLocationStatus_FoundBusy() {
        String status = controller.getLocationStatus("Rocket Water Slide");

        assertEquals("long", status);
    }

    @Test
    public void testGetLocationStatus_FoundNotBusy() {
        String status = controller.getLocationStatus("Dinos Water Slide");

        assertEquals("short", status);
    }

    @Test
    public void testGetLocationStatus_NotFound() {
        String status = controller.getLocationStatus("Rocket Slide");

        assertEquals("Location not found", status);
    }
}

