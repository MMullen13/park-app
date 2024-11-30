package view.ticketing.tickets;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
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
    private JPanel purchasePanel;
    private JLabel totalItemsCartLabel;
    private JLabel childTicketsCartLabel;
    private JLabel adultTicketsCartLabel;
    private JLabel seniorTicketsCartLabel;
    private JLabel totalPriceLabel;
    private Map<String, Integer> cartItems = new HashMap<>();
    private final double TAX_RATE = 0.07;

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
                + "Tickets are for children ages 3 to 18. Children younger than age 3 do not require a ticket."));

        ticketsPanel.add(createTicketCard("Adult Day Pass", "$35", "Access to all rides and attractions for one day. "
                + "Tickets are for adults ages 18 to 61."));
        ticketsPanel.add(createTicketCard("Senior Day Pass", "$30", "Access to all rides and attractions for one day. "
                + "Tickets are for seniors ages 62 to 80. Seniors older than 80 ride for free and do not need a ticket."));

        ticketsPanel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center-align tickets panel
        add(ticketsPanel);

        // Add the new labels for ticket quantities
        childTicketsCartLabel = new JLabel("Child Tickets: 0");
        childTicketsCartLabel.setFont(new Font("Arial", Font.BOLD, 14));
        childTicketsCartLabel.setForeground(new Color(40, 95, 150));
        childTicketsCartLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(childTicketsCartLabel);

        adultTicketsCartLabel = new JLabel("Adult Tickets: 0");
        adultTicketsCartLabel.setFont(new Font("Arial", Font.BOLD, 14));
        adultTicketsCartLabel.setForeground(new Color(40, 95, 150));
        adultTicketsCartLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(adultTicketsCartLabel);

        seniorTicketsCartLabel = new JLabel("Senior Tickets: 0");
        seniorTicketsCartLabel.setFont(new Font("Arial", Font.BOLD, 14));
        seniorTicketsCartLabel.setForeground(new Color(40, 95, 150));
        seniorTicketsCartLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(seniorTicketsCartLabel);

        // Cart Label
        totalItemsCartLabel = new JLabel("Total: 0 passes"); // label for cart summary
        totalItemsCartLabel.setFont(new Font("Arial", Font.BOLD, 14));
        totalItemsCartLabel.setForeground(new Color(40, 95, 150));
        totalItemsCartLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Align label to center
        add(totalItemsCartLabel);

        // Total price label
        totalPriceLabel = new JLabel("Total Price (incl. taxes): $0.00");
        totalPriceLabel.setFont(new Font("Arial", Font.BOLD, 16));
        totalPriceLabel.setForeground(new Color(40, 95, 150));
        totalPriceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(totalPriceLabel);

        // Purchase Button
        purchasePanel = new JPanel();
        purchasePanel.setOpaque(false);
        purchasePanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0)); // Add spacing around the button

        purchaseBtn = new JButton("Checkout");
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
        cardPanel.setBorder(BorderFactory.createLineBorder(new Color(70, 130, 180), 6, true)); // Rounded border
        cardPanel.setBackground(new Color(170, 187, 192)); // gray background
        cardPanel.setPreferredSize(new Dimension(200, 300)); // Uniform size for all cards

        // Add MouseListener for hover effect
        cardPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                cardPanel.setBackground(new Color(255, 255, 255)); // Change background on hover
            }

            @Override
            public void mouseExited(MouseEvent e) {
                cardPanel.setBackground(new Color(170, 187, 192)); // Revert to original background
            }
        });

        // Header (Name + Price)
        JLabel headerLabel = new JLabel(header + " - " + price, JLabel.CENTER) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Gradient for header
                GradientPaint gradient = new GradientPaint(0, 0, new Color(58, 115, 169), getWidth(), 0, new Color(17, 138, 200));
                g2d.setPaint(gradient);
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 5, 5); // Rounded corners

                // Draw the text
                g2d.setColor(Color.WHITE); // Text color
                g2d.setFont(getFont()); // Use the label's font
                FontMetrics fm = g2d.getFontMetrics();
                String text = getText();
                int x = (getWidth() - fm.stringWidth(text)) / 2; // Center horizontally
                int y = (getHeight() + fm.getAscent() - fm.getDescent()) / 2; // Center vertically
                g2d.drawString(text, x, y);
            }
        };
        headerLabel.setOpaque(false); // Let the gradient show
        headerLabel.setFont(new Font("Arial", Font.BOLD, 16));
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        headerLabel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        headerLabel.setPreferredSize(new Dimension(40, 40));
        cardPanel.add(headerLabel);

        // Description with fixed height
        JLabel descriptionLabel = new JLabel("<html><div style='text-align: left; '>" + description + "</div></html>", JLabel.CENTER);
        descriptionLabel.setFont(new Font("Arial", Font.ITALIC, 14));
        descriptionLabel.setForeground(Color.WHITE);
        descriptionLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        descriptionLabel.setAlignmentY(Component.TOP_ALIGNMENT);

        JPanel descriptionPanel = new JPanel();
        descriptionPanel.setBackground(new Color(152, 175, 197));
        descriptionPanel.setPreferredSize(new Dimension(200, 100));
        descriptionPanel.setLayout(new BorderLayout());
        descriptionPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        descriptionPanel.add(descriptionLabel, BorderLayout.CENTER);
        cardPanel.add(descriptionPanel);

        // Quantity Selector
        JPanel quantityPanel = new JPanel();
        quantityPanel.setBackground(new Color(152, 175, 197));
        quantityPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));

        JLabel quantityLabel = new JLabel("Qty:");
        quantityLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        quantityLabel.setForeground(Color.WHITE);
        quantityPanel.add(quantityLabel);

        JSpinner quantitySpinner = new JSpinner(new SpinnerNumberModel(0, 0, 10, 1));
        quantitySpinner.setFont(new Font("Arial", Font.PLAIN, 14));
        quantitySpinner.setPreferredSize(new Dimension(60, 30));
        quantitySpinner.setName(header);

        quantitySpinner.addChangeListener(e -> {
            JSpinner source = (JSpinner) e.getSource();
            String itemName = source.getName(); // Get the item name
            int quantity = (int) source.getValue(); // Get the spinner value

            // Update the cart
            if (itemName != null && itemName.equals("Child Day Pass")) {
                cartItems.put(itemName, quantity);
            }
            if (itemName != null && itemName.equals("Adult Day Pass")) {
                cartItems.put(itemName, quantity);
            }
            if (itemName != null && itemName.equals("Senior Day Pass")) {
                cartItems.put(itemName, quantity);
            }

            // Update the respective ticket label
            if (itemName != null && itemName.equals("Child Day Pass")) {
                childTicketsCartLabel.setText("Child Tickets: " + quantity);
            } else if (itemName != null && itemName.equals("Adult Day Pass")) {
                adultTicketsCartLabel.setText("Adult Tickets: " + quantity);
            } else if (itemName != null && itemName.equals("Senior Day Pass")) {
                seniorTicketsCartLabel.setText("Senior Tickets: " + quantity);
            }

            // Update totalItemsCartLabel
            updateCartLabel();

        });

        quantityPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        quantityPanel.add(quantitySpinner);

        cardPanel.add(quantityPanel);

        // footer Panel to add space
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(new Color(152, 175, 197));
        footerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 15));

        cardPanel.add(footerPanel);

        // Fill remaining space
        cardPanel.add(Box.createVerticalGlue());

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
                GradientPaint gradient = new GradientPaint(0, 0, new Color(17, 138, 200), getWidth(), 0, new Color(17, 138, 200));
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
                GradientPaint gradient = new GradientPaint(0, 0, new Color(58, 115, 169), getWidth(), 0, new Color(58, 115, 169));
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

    private void updateCartLabel() {
        // Calculate the total number of tickets from the cart
        int totalTickets = 0;
        double totalPrice = 0;

        // Calculate total tickets and price
        for (Map.Entry<String, Integer> entry : cartItems.entrySet()) {
            int quantity = entry.getValue();
            double price = 0.0;
            switch (entry.getKey()) {
                case "Child Day Pass" -> price = 25.0;
                case "Adult Day Pass" -> price = 35.0;
                case "Senior Day Pass" -> price = 30.0;
            }
            totalTickets += quantity;
            totalPrice += quantity * price;
        }

        // Apply tax
        totalPrice += totalPrice * TAX_RATE;

        // Update the labels
        totalItemsCartLabel.setText("Total: " + totalTickets + " passes");
        totalPriceLabel.setText(String.format("Total Price (incl. taxes): $%.2f", totalPrice));
    }

}
