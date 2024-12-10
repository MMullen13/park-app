package view.parkmap;

import controller.parkmap.ParkMapController;
import model.parkmap.Location;

import javax.swing.*;

import java.awt.*;
import java.net.URL;
import java.util.List;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class ParkMapView extends JFrame {

    private final ParkMapController controller;
    private final JPanel mapPanel;

    public ParkMapView(ParkMapController controller) {
        super("WallyLand Park Map");
        this.controller = controller;

        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        mapPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Draw the map image
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
        
        mapPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                // Re-display map when the panel is resized
                controller.displayMapWithBusyLocations();
            }
        });

        add(new JScrollPane(mapPanel), BorderLayout.CENTER);
    }

    public void displayMap(List<Location> locations) {
        // Clear existing buttons
        mapPanel.removeAll();

        for (Location loc : locations) {
            // Create a custom circular button
            JButton locButton = new JButton() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    if(getBackground() != null) {
                        // Make the button appear as a circle
                        g.setColor(getBackground());
                        g.fillOval(0, 0, getWidth(), getHeight());
                    }
                }
            };

            // Calculate the coordinates and set the button size
            Rectangle bounds = parseCoordinates(loc.getCoordinates());
            locButton.setBounds(bounds);

            // Set the background color based on the location's status
            if(!loc.isVisible()) {
                locButton.setVisible(false);
            } else {
                locButton.setBackground(loc.isBusy() ? Color.RED : Color.GREEN);
                locButton.setBorder(BorderFactory.createEmptyBorder());
                locButton.setContentAreaFilled(false); 
            }

            locButton.addActionListener(e -> {
                String status = controller.getLocationStatus(loc.getName());
                JOptionPane.showMessageDialog(this, loc.getName() + " wait time is currently " + status);
            });

            // Add the button to the map panel
            mapPanel.add(locButton);
        }
        mapPanel.repaint();
        mapPanel.revalidate();
    }

    private Rectangle parseCoordinates(String coordinates) {
        // Parse "X1,Y1" -> Rectangle(x, y, width, height)
        String[] parts = coordinates.split(",");
        int rawX = Integer.parseInt(parts[0].substring(1));
        int rawY = Integer.parseInt(parts[1].substring(1));

        // Get the current dimensions of the map panel
        int panelWidth = mapPanel.getWidth();
        int panelHeight = mapPanel.getHeight();

        // Define the original map dimensions (e.g., 1000x1000 pixels as an example)
        final int ORIGINAL_MAP_WIDTH = 1250;
        final int ORIGINAL_MAP_HEIGHT = 750; 

        // Calculate scaling factors
        double scaleX = (double) panelWidth / ORIGINAL_MAP_WIDTH;
        double scaleY = (double) panelHeight / ORIGINAL_MAP_HEIGHT;

        // Scale the coordinates
        int scaledX = (int) (rawX * scaleX);
        int scaledY = (int) (rawY * scaleY);

        // Scale the circle diameter based on the window size
        int scaledCircleDiameter = (int) (40 * Math.min(scaleX, scaleY));  // 30 is the base size, scaled based on the window size

        return new Rectangle(scaledX - scaledCircleDiameter / 2, scaledY - scaledCircleDiameter / 2, scaledCircleDiameter, scaledCircleDiameter);
    }
}