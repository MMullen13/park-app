package view.passes.tiketsandpasses;

import controller.ticketsandpasses.PassesController;
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
import java.awt.RenderingHints;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import model.ticketsandpasses.PurchasePassFormListenerIF;
import model.ticketsandpasses.PurchaseTicketEvent;
import model.ticketsandpasses.Ticket;
import view.Footer;
import view.Header;
import view.ImageUtils;
import view.passes.cart.CartView;

/**
 *
 * @author Ana
 */
public class TicketsPanel extends JPanel {

    private JButton addToCart;
    private JButton viewCart;
    private JPanel purchasePanel;
    private JLabel totalItemsCartLabel;
    private JLabel childTicketsCartLabel;
    private JLabel adultTicketsCartLabel;
    private JLabel seniorTicketsCartLabel;
    private JLabel totalPriceLabel;
    private Map<String, Integer> cartItems = new HashMap<>();
    private final double TAX_RATE = 0.07;
    private Header header;
    private Footer footer;
    public int silverPassQuantity = 0;
    public int goldPassQuantity = 0;
    public int platinumPassQuantity = 0;
    public int totalTicketsQuantity;
    public double totalPrice;
    private PurchasePassFormListenerIF listener;
    private Ticket ticket;
    private PassesController controller;


    public TicketsPanel(PassesController controller) {
        this.controller = controller;
        header = new Header();
        footer = new Footer();
        ticket = new Ticket();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // Use vertical BoxLayout

        Border innerBorder = BorderFactory.createTitledBorder("Purchase Tickets");
        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

        setBackground(new Color(235, 237, 238));

        // Header Panel
        JPanel headerContainer = new JPanel(new BorderLayout());
        JPanel headerPanel = header.createHeaderPanel("Wallyland Tickets", null);
        headerPanel.setOpaque(false);
        headerContainer.add(headerPanel, BorderLayout.CENTER);
        add(headerContainer, BorderLayout.NORTH);

        // Create the main panel for the tickets
        JPanel ticketsPanel = new JPanel();
        ticketsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Horizontally align cards
        ticketsPanel.setOpaque(false);
        ticketsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Padding

        ticketsPanel.add(createTicketCard("Child", "$25", "Child passes include access to age-appropriate rides and attractions. "
                + "Tickets are for children ages 3 to 18. Children younger than age 3 do not require a ticket."));
        ticketsPanel.add(createTicketCard("Adult", "$35", "Access to all rides and attractions for one day. "
                + "Tickets are for adults ages 18 to 61."));
        ticketsPanel.add(createTicketCard("Senior", "$30", "Access to all rides and attractions for one day. "
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
        totalItemsCartLabel = new JLabel("Total Items: 0 tickets"); // label for cart summary
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

        addToCart = new JButton("Add to Cart");
        addToCart.setBackground(new Color(152, 175, 197));
        addToCart.setForeground(Color.WHITE);
        addToCart.setFocusPainted(false); // Removes focus border on click
        addToCart.setFont(new Font("Arial", Font.BOLD, 14));
        addToCart.setPreferredSize(new Dimension(160, 60)); // Width, Height
        addToCart.setIcon(ImageUtils.createIcon("/images/icons8-add-to-cart.png", 40, 40));

        addToCart.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                addToCart.setBackground(new Color(132, 155, 177)); // Darker blue on hover
            }

            @Override
            public void mouseExited(MouseEvent e) {
                addToCart.setBackground(new Color(152, 175, 197)); // Original blue
            }

            @Override
            public void mousePressed(MouseEvent e) {
                addToCart.setForeground(new Color(40, 95, 150));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                addToCart.setForeground(Color.WHITE);
            }
        });

        addToCart.addActionListener((ActionEvent e) -> {
            for (Map.Entry<String, Integer> entry : cartItems.entrySet()) {
                String passType = entry.getKey();
                int passQuantity = entry.getValue();
                
                System.out.println(passType + " " + passQuantity);

                if (passQuantity > 0) {
                    controller.updateTicketsTotals(passType, passQuantity);
                    System.out.println("controller updates tickets totals");
                }
            }

            PurchaseTicketEvent ticketEvent = new PurchaseTicketEvent(this, ticket);
            if (listener != null) {
                listener.formEventOccured(ticketEvent);
            }
            saveData();
        });

        viewCart = new JButton("View Cart");
        viewCart.setBackground(new Color(58, 115, 169)); // Navy blue
        viewCart.setForeground(Color.WHITE);
        viewCart.setFocusPainted(false); // Removes focus border on click
        viewCart.setFont(new Font("Arial", Font.BOLD, 14));
        viewCart.setPreferredSize(new Dimension(160, 60)); // Width, Height
        viewCart.setIcon(ImageUtils.createIcon("/images/icons8-cart-100.png", 40, 40));
        viewCart.addActionListener((ActionEvent e) -> {
            // Add action listener
        });
        viewCart.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                viewCart.setBackground(new Color(40, 95, 150)); // Darker blue on hover
            }

            @Override
            public void mouseExited(MouseEvent e) {
                viewCart.setBackground(new Color(58, 115, 169)); // Original blue
            }

            @Override
            public void mousePressed(MouseEvent e) {
                viewCart.setForeground(new Color(40, 95, 150));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                viewCart.setForeground(Color.WHITE);
            }
        });

        viewCart.addActionListener((ActionEvent e) -> {
            CartView cartView = new CartView();
            cartView.setVisible(true);

            Window parentWindow = SwingUtilities.getWindowAncestor(TicketsPanel.this);

            if (parentWindow instanceof TicketsView ticketView) {
                ticketView.closeWindow();
            }
        });

        purchasePanel.add(addToCart);
        purchasePanel.add(viewCart);
        purchasePanel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center-align purchase button
        add(purchasePanel);

        // Footer Panel
        JPanel footerContainer = new JPanel(new BorderLayout());
        String msgOne = "Your 1-day tickets are valid for single-day admission to our park. Tickets are nonrefundable.";
        String msgTwo = "The price paid for a wholly unused ticket can be applied to the purchase of a new ticket with an equal or higher price.";
        JPanel footerPanel = footer.createFooterPanel(msgOne, msgTwo);
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

            if (itemName != null && itemName.equals("Child")) {
                cartItems.put(itemName, quantity);
            }
            if (itemName != null && itemName.equals("Adult")) {
                cartItems.put(itemName, quantity);
            }
            if (itemName != null && itemName.equals("Senior")) {
                cartItems.put(itemName, quantity);
            }

            // Update the respective ticket label
            if (itemName != null && itemName.equals("Child")) {
                childTicketsCartLabel.setText("Child Tickets: " + quantity);
            } else if (itemName != null && itemName.equals("Adult")) {
                adultTicketsCartLabel.setText("Adult Tickets: " + quantity);
            } else if (itemName != null && itemName.equals("Senior")) {
                seniorTicketsCartLabel.setText("Senior Tickets: " + quantity);
            }

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

    private void updateCartLabel() {
        // Calculate the total number of tickets from the cart
        totalTicketsQuantity = 0;
        totalPrice = 0;

        // Calculate total tickets and price
        for (Map.Entry<String, Integer> entry : cartItems.entrySet()) {
            int quantity = entry.getValue();
            double price = ticket.getPriceForType(entry.getKey());

            totalTicketsQuantity += quantity;
            totalPrice += quantity * price;
        }
        totalPrice += totalPrice * TAX_RATE;
        totalItemsCartLabel.setText("Total Items: " + totalTicketsQuantity + " tickets");
        totalPriceLabel.setText(String.format("Total Price (incl. taxes): $%.2f", totalPrice));
//        System.out.println(totalPrice);
    }

    private void saveData() {
        controller.saveTicketCartDataToFile(cartItems);
    }
}
