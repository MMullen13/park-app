package view.ticketing.tickets;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;

/**
 *
 * @author Ana
 */
public class TicketPanel extends JPanel {

    private JButton purchaseBtn;

    public TicketPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // Use vertical BoxLayout

        Border innerBorder = BorderFactory.createTitledBorder("Purchase Tickets");
        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

        setBackground(new Color(235, 237, 238));

        // Header Panel
        JPanel headerContainer = new JPanel(new BorderLayout());
        JPanel headerPanel = createHeaderPanel();
        headerPanel.setOpaque(false);
        headerContainer.add(headerPanel, BorderLayout.CENTER);
        add(headerContainer, BorderLayout.NORTH);

        // Create the main panel for the tickets
        JPanel ticketsPanel = new JPanel();
        ticketsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Horizontally align cards
        ticketsPanel.setOpaque(false);
        ticketsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Padding

        // Add ticket cards to the panel
        ticketsPanel.add(createTicketCard("Child Day Pass", "$25", "Child passes include access to age-appropriate rides and attractions. "
                + "Children younger than age 3 don’t need a ticket."));
        ticketsPanel.add(createTicketCard("Adult Day Pass", "$35", "Access to all rides and attractions for one day."));
        ticketsPanel.add(createTicketCard("Senior Day Pass", "$30", "Access to all rides and attractions for one day. "
                + "Seniors older than 80 don’t need a ticket."));
        ticketsPanel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center-align tickets panel
        add(ticketsPanel);

        // Cart Label
        JLabel cartLabel = new JLabel("Selected Tickets: (0 items)"); // label for cart summary
        cartLabel.setFont(new Font("Arial", Font.BOLD, 14));
        cartLabel.setForeground(new Color(40, 95, 150));
        cartLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Align label to center
        add(cartLabel);

        // Purchase Button
        JPanel purchasePanel = new JPanel();
        purchasePanel.setOpaque(false);
        purchasePanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0)); // Add spacing around the button

        JButton purchaseBtn = new JButton("Checkout");
        purchaseBtn.setBackground(new Color(58, 115, 169)); // Navy blue
        purchaseBtn.setForeground(Color.WHITE);
        purchaseBtn.setFocusPainted(false); // Removes focus border on click
        purchaseBtn.setFont(new Font("Arial", Font.BOLD, 14));
        purchaseBtn.setPreferredSize(new Dimension(160, 60)); // Width, Height
        purchaseBtn.setIcon(createIcon("/images/icons8-cart-100.png", 40, 40));
        purchaseBtn.addActionListener((ActionEvent e) -> {
            // Add action listener
        });
        purchaseBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                purchaseBtn.setBackground(new Color(40, 95, 150)); // Darker blue on hover
            }

            @Override
            public void mouseExited(MouseEvent e) {
                purchaseBtn.setBackground(new Color(58, 115, 169)); // Original blue
            }

            @Override
            public void mousePressed(MouseEvent e) {
                purchaseBtn.setForeground(new Color(40, 95, 150));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                purchaseBtn.setForeground(Color.WHITE);
            }
        });

        purchasePanel.add(purchaseBtn);
        purchasePanel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center-align purchase button
        add(purchasePanel);

        // Footer Panel
        JPanel footerContainer = new JPanel(new BorderLayout());
        JPanel footerPanel = createFooterPanel();
        footerPanel.setOpaque(false);
        footerContainer.add(footerPanel, BorderLayout.CENTER);
        add(footerContainer, BorderLayout.PAGE_END);

        // Add spacing between elements
        add(Box.createVerticalGlue()); // Allows dynamic adjustment based on available space
    }

    private JPanel createTicketCard(String header, String price, String description) {
        JPanel cardPanel = new JPanel();
        cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.Y_AXIS)); // Stack components vertically
        cardPanel.setBorder(BorderFactory.createLineBorder(new Color(17, 138, 200), 4, true)); // Rounded border
        cardPanel.setBackground(Color.WHITE);
        cardPanel.setPreferredSize(new Dimension(200, 340)); // Uniform size for all cards

        // Header (Name + Price)
        JLabel headerLabel = new JLabel(header + " - " + price, JLabel.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 16));
        headerLabel.setOpaque(true);
        headerLabel.setBackground(new Color(17, 138, 200));
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        headerLabel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60)); // Fixed height for header
        cardPanel.add(headerLabel);

        // Description with fixed height
        JLabel descriptionLabel = new JLabel("<html><div style='text-align: center;'>" + description + "</div></html>", JLabel.CENTER);
        descriptionLabel.setFont(new Font("Arial", Font.ITALIC, 14));
        descriptionLabel.setForeground(new Color(25, 75, 100));
        descriptionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Wrap description in a panel with a fixed height
        JPanel descriptionPanel = new JPanel();
        descriptionPanel.setBackground(Color.WHITE);
        descriptionPanel.setPreferredSize(new Dimension(200, 100)); // Fixed height for description area
        descriptionPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        descriptionPanel.setLayout(new BorderLayout()); // Center the description inside
        descriptionPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add 10px padding on all sides
        descriptionPanel.add(descriptionLabel, BorderLayout.CENTER);
        cardPanel.add(descriptionPanel);

        // Quantity Selector
        JPanel quantityPanel = new JPanel();
        quantityPanel.setBackground(Color.WHITE);
        quantityPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 2, 15));

        JLabel quantityLabel = new JLabel("Qty:");
        quantityLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        quantityLabel.setForeground(new Color(40, 95, 150));
        quantityPanel.add(quantityLabel);

        JSpinner quantitySpinner = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1)); // Default 1, min 1, max 10, step 1
        quantitySpinner.setFont(new Font("Arial", Font.PLAIN, 14));
        quantityPanel.add(quantitySpinner);

        quantityPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        cardPanel.add(quantityPanel);

        // Button
        JButton chooseButton = new JButton("Add to Cart");
        chooseButton.setFont(new Font("Arial", Font.BOLD, 14));
        chooseButton.setBackground(new Color(17, 138, 200));
        chooseButton.setForeground(Color.WHITE);
        chooseButton.setFocusPainted(false);
        chooseButton.setPreferredSize(new Dimension(130, 40)); // Fixed button size

        chooseButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                chooseButton.setBackground(new Color(58, 115, 169)); // Darker blue on hover
            }

            @Override
            public void mouseExited(MouseEvent e) {
                chooseButton.setBackground(new Color(17, 138, 200)); // Original blue
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
        

        chooseButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        cardPanel.add(Box.createVerticalStrut(6)); // Add spacing above the button
        cardPanel.add(chooseButton);
        cardPanel.add(Box.createVerticalGlue()); // Push button and quantity selector to the bottom if space is available

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
                GradientPaint gradient = new GradientPaint(0, 0, new Color(17, 138, 200), getWidth(), 0, new Color(58, 115, 169));
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        header.setPreferredSize(new Dimension(600, 30)); // Adjust height for the footer

        JLabel contactLabel = new JLabel("Wallyland Park Tickets");

        contactLabel.setFont(new Font("Arial", Font.BOLD, 18));
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
                GradientPaint gradient = new GradientPaint(0, 0, new Color(17, 138, 200), getWidth(), 0, new Color(58, 115, 169));
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

    private ImageIcon createIcon(String path, int w, int l) {
        URL url = getClass().getResource(path);

        if (url == null) {
            System.err.println("Unable to load image icon: " + path);
        }

        ImageIcon icon = new ImageIcon(url);
        Image scaledImage = icon.getImage().getScaledInstance(w, l, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(scaledImage);
        return resizedIcon;
    }
}
