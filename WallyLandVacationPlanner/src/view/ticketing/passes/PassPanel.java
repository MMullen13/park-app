package view.ticketing.passes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Ana
 */
public class PassPanel extends JPanel{
    
        public PassPanel() {
        setLayout(new BorderLayout());
        setBackground(new Color(240, 248, 255));

        // Create the main panel for the tickets
        JPanel ticketsPanel = new JPanel();
        ticketsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 30));
        ticketsPanel.setOpaque(false);
        ticketsPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40)); // Padding

        // Add ticket cards to the panel
        ticketsPanel.add(createTicketCard("Child Day Pass", "$25", "Child passes include access to all rides and attractions for one day. " +
                "Children younger than age 3 don’t need a ticket."));
        ticketsPanel.add(createTicketCard("Adult Day Pass", "$35", "Access to all rides and attractions for one day."));
        ticketsPanel.add(createTicketCard("Senior Day Pass", "$30", "Access to all rides and attractions for one day."));

        add(ticketsPanel, BorderLayout.CENTER);

        JPanel footerPanel = createFooterPanel();
        add(footerPanel, BorderLayout.SOUTH);

        JPanel headerPanel = createHeaderPanel();
        add(headerPanel, BorderLayout.NORTH);
    }
    
        
    /**
     * Creates a ticket card panel.
     */
    private JPanel createTicketCard(String header, String price, String description) {
        JPanel cardPanel = new JPanel();
        cardPanel.setLayout(new BorderLayout());
        cardPanel.setBorder(BorderFactory.createLineBorder(new Color(90, 145, 204), 2, true)); // Rounded border
        cardPanel.setBackground(Color.WHITE);
        cardPanel.setPreferredSize(new Dimension(200, 300)); // Adjusted size for horizontal alignment

        // Header (Name + Price)
        JLabel headerLabel = new JLabel(header + " - " + price, JLabel.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 16));
        headerLabel.setOpaque(true);
        headerLabel.setBackground(new Color(90, 145, 204));
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setPreferredSize(new Dimension(220, 40));
        cardPanel.add(headerLabel, BorderLayout.NORTH);

        // Description
        JLabel descriptionLabel = new JLabel("<html><div style='text-align: center;'>" + description + "</div></html>", JLabel.CENTER);
        descriptionLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        descriptionLabel.setForeground(new Color(40, 95, 150));
        cardPanel.add(descriptionLabel, BorderLayout.CENTER);

        // Button
        JButton chooseButton = new JButton("Buy Ticket");
        chooseButton.setFont(new Font("Arial", Font.BOLD, 14));
        chooseButton.setBackground(new Color(58, 115, 169));
        chooseButton.setForeground(Color.WHITE);
        chooseButton.setFocusPainted(false);

        chooseButton.setPreferredSize(new Dimension(130, 40)); // Smaller button
        chooseButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                chooseButton.setBackground(new Color(40, 95, 150)); // Darker blue on hover
            }

            @Override
            public void mouseExited(MouseEvent e) {
                chooseButton.setBackground(new Color(58, 115, 169)); // Original blue
            }

            @Override
            public void mousePressed(MouseEvent e) {
                chooseButton.setForeground(new Color(40, 95, 150));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                chooseButton.setForeground(Color.WHITE);
            }
        });

        // Add the button to a container to center it horizontally
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(chooseButton);

        cardPanel.add(buttonPanel, BorderLayout.SOUTH);

        return cardPanel;
    }

    private JPanel createHeaderPanel() {
        JPanel header = new JPanel() {
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
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        header.setPreferredSize(new Dimension(600, 30)); // Adjust height for the footer

        JLabel contactLabel = new JLabel("Wallyland Park Tickets");

        contactLabel.setFont(new Font("Arial", Font.BOLD, 16));
        contactLabel.setForeground(Color.WHITE);
        contactLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        header.add(Box.createRigidArea(new Dimension(0, 5))); // Add spacing at the top
        header.add(contactLabel);
        header.add(Box.createRigidArea(new Dimension(0, 10))); // Add spacing at the bottom

        return header;
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
        footer.setPreferredSize(new Dimension(600, 60)); // Adjust height for the footer

        JLabel infoLabelOne = new JLabel("Your 1-day tickets are valid for single-day admission to our park. Tickets are nonrefundable.");      
        JLabel infoLabelTwo = new JLabel("The price paid for a wholly unused ticket can be applied to the purchase of a new ticket with an equal or higher price.");

        infoLabelOne.setFont(new Font("Arial", Font.PLAIN, 12));
        infoLabelOne.setForeground(Color.WHITE);
        infoLabelOne.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        infoLabelTwo.setFont(new Font("Arial", Font.PLAIN, 12));
        infoLabelTwo.setForeground(Color.WHITE);
        infoLabelTwo.setAlignmentX(Component.CENTER_ALIGNMENT);

        footer.add(Box.createRigidArea(new Dimension(0, 10))); // Add spacing at the top
        footer.add(infoLabelOne);
        footer.add(Box.createRigidArea(new Dimension(0, 5))); // Add spacing at the top
        footer.add(infoLabelTwo);
        footer.add(Box.createRigidArea(new Dimension(0, 10))); // Add spacing at the bottom

        return footer;
    }
}
