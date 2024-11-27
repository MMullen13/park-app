package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.net.URL;
import javax.swing.*;
import javax.swing.border.Border;

/**
 * Coming Soon Panel
 * 
 * @author Ana
 */
public class ComingSoonPanel extends JPanel {

    private ImageIcon wallylandImage;
    private JPanel backgroundPanel;
    private JPanel footerPanel;

    /**
     * Constructor
     */
    public ComingSoonPanel() {
        setLayout(new BorderLayout());

        wallylandImage = createIcon("/images/pb.jpg", 1100, 900);
        backgroundPanel = new JPanel();
        JLabel contactLabel = new JLabel("Coming Soon");

        contactLabel.setFont(new Font("Arial", Font.BOLD, 24));
        contactLabel.setForeground(Color.BLACK);
        contactLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        backgroundPanel.add(contactLabel);
        add(backgroundPanel, BorderLayout.CENTER);

        footerPanel = createFooterPanel();
        add(footerPanel, BorderLayout.SOUTH);
        
        Border innerBorder = BorderFactory.createTitledBorder("");
        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
    } 

    private JPanel createFooterPanel() {
        JPanel footer = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Create a gradient background for the footer
                GradientPaint gradient = new GradientPaint(0, 0, new Color(40, 95, 150), getWidth(), 0, new Color(58, 115, 169));
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        footer.setLayout(new BoxLayout(footer, BoxLayout.Y_AXIS));
        footer.setPreferredSize(new Dimension(600, 80)); // Adjust height for the footer

        JLabel contactLabel = new JLabel("Contact Us: 123-456-7890 | Email: info@wallyland.com");
        JLabel addressLabel = new JLabel("Address: 123 WallyLand Ave, Fun City, USA");

        contactLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        contactLabel.setForeground(Color.WHITE);
        contactLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        addressLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        addressLabel.setForeground(Color.WHITE);
        addressLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        footer.add(Box.createRigidArea(new Dimension(0, 10))); // Add spacing at the top
        footer.add(contactLabel);
        footer.add(Box.createRigidArea(new Dimension(0, 5))); // Add spacing between labels
        footer.add(addressLabel);
        footer.add(Box.createRigidArea(new Dimension(0, 10))); // Add spacing at the bottom

        return footer;
    }

    private ImageIcon createIcon(String path, int w, int l) {
        URL url = getClass().getResource(path);

        if (url == null) {
            System.err.println("Unable to load image icon: " + path);
            return null;
        }

        ImageIcon icon = new ImageIcon(url);
        Image scaledImage = icon.getImage().getScaledInstance(w, l, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }

}
