package view.parkmap;

import controller.parkmap.ParkMapController;
import model.parkmap.Location;

import javax.swing.*;

import java.awt.*;
import java.net.URL;
import java.util.List;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * VIEW CLASS: Graphical user interface for displaying park map and its locations.
 * @author Becky
 */

public class ParkMapView extends JFrame {

    private final ParkMapController controller; //Assoicated park map controller
    private final JPanel mapPanel; //Panel to render the park map and interactive buttons

    /**
     * Initializes the park map view with its associated controller.
     * @param controller the park map controller managing park map view and model data
     */
    public ParkMapView(ParkMapController controller) {
        super("WallyLand Park Map");
        this.controller = controller;

        //Configure JFrame settings
        setSize(1000, 800); //Set window size
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null); //Center window on the screen

        //Create panel for rendering the map
        mapPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                //Load and draw the map image
                URL imageURL = getClass().getClassLoader().getResource("images/parkmap.png");
                if (imageURL == null) {
                    System.err.println("Map image not found!");
                    return;
                }
                Image mapImage = new ImageIcon(imageURL).getImage();
                g.drawImage(mapImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        mapPanel.setLayout(null);
        
        //Re-display the park map when the window is resized
        mapPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                controller.displayMapWithBusyLocations();
            }
        });

        add(new JScrollPane(mapPanel), BorderLayout.CENTER); //Add scrollable map panel
    }

    /**
     * Displays the park map with locations highlighted.
     * @param locations list of locations to display on park map
     */
    public void displayMap(List<Location> locations) {
        mapPanel.removeAll(); //Clear existing elements

        for (Location loc : locations) {
            //Skip hidden locations
            if (!loc.isVisible()) {
                continue;
            }

            //Create a custom circular button
            JButton locButton = new JButton() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    if(getBackground() != null) {
                        //Make the button appear as a circle
                        g.setColor(getBackground());
                        g.fillOval(0, 0, getWidth(), getHeight());
                    }
                }
            };

            //Calculate the coordinates and set the button size
            Rectangle bounds = parseCoordinates(loc.getCoordinates());
            locButton.setBounds(bounds);

            //Set the background color based on the location's status
            locButton.setBackground(loc.isBusy() ? Color.RED : Color.GREEN);
            locButton.setBorder(BorderFactory.createEmptyBorder());
            locButton.setContentAreaFilled(false); 

            locButton.addActionListener(e -> {
                String status = controller.getLocationStatus(loc.getName());
                JOptionPane.showMessageDialog(this, loc.getName() + " wait time is currently " + status);
            });

            //Add the button to the map panel
            mapPanel.add(locButton);
        }
        mapPanel.repaint();
        mapPanel.revalidate();
    }

    /**
     * Converts location coordinates to scaled button positions.
     * @param coordinates string coordinates (e.g., "X100,Y200")
     * @return rectangle bounds for the button
     */
    private Rectangle parseCoordinates(String coordinates) {
        //Parse "X1,Y1" -> Rectangle(x, y, width, height)
        String[] parts = coordinates.split(",");
        int rawX = Integer.parseInt(parts[0].substring(1));
        int rawY = Integer.parseInt(parts[1].substring(1));

        //Get the current dimensions of the map panel
        int panelWidth = mapPanel.getWidth();
        int panelHeight = mapPanel.getHeight();

        //Define the original map dimensions in pixels
        final int ORIGINAL_MAP_WIDTH = 1250;
        final int ORIGINAL_MAP_HEIGHT = 750; 

        //Calculate scaling factors
        double scaleX = (double) panelWidth / ORIGINAL_MAP_WIDTH;
        double scaleY = (double) panelHeight / ORIGINAL_MAP_HEIGHT;

        //Scale the coordinates
        int scaledX = (int) (rawX * scaleX);
        int scaledY = (int) (rawY * scaleY);

        //Scale the circle diameter based on the window size
        int scaledCircleDiameter = (int) (40 * Math.min(scaleX, scaleY));  //30 is the base size, scaled based on the window size

        return new Rectangle(scaledX - scaledCircleDiameter / 2, scaledY - scaledCircleDiameter / 2, scaledCircleDiameter, scaledCircleDiameter);
    }

    /**
    * Returns the map panel for testing purposes.
    * @return the JPanel used for displaying the map.
    */
    public JPanel getMapPanel() {
        return mapPanel;
    }
}