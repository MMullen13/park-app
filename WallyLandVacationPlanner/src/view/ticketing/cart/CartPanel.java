package view.ticketing.cart;

import controller.ticketing.TicketController;
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import model.ticketing.ObserverIF;
import view.Footer;
import view.Header;

/**
 *
 * @author Ana
 */
class CartPanel extends JPanel implements ObserverIF {

    private TicketController controller;
    private Header header;
    private Footer footer;
    private JPanel ticketsPanel;
    private ArrayList<String> cartItems = new ArrayList<>();

    public CartPanel() {
        controller = new TicketController();
        controller.passSubject.addObserver(this);
        header = new Header();
        footer = new Footer();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // Use vertical BoxLayout

        Border innerBorder = BorderFactory.createTitledBorder("View Cart");
        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

        setBackground(new Color(235, 237, 238));

        // Header Panel
        JPanel headerPanel = header.createHeaderPanel("Wallyland Digital Tickets And Passes", null);
        headerPanel.setOpaque(false);
        headerPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        headerPanel.setPreferredSize(new Dimension(800, 34));
        add(headerPanel, BorderLayout.NORTH);

        // Create the main panel for the tickets
        ticketsPanel = new JPanel();
        ticketsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Horizontally align cards
        ticketsPanel.setOpaque(false);
        ticketsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Padding

        ticketsPanel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center-align tickets panel
        add(ticketsPanel);

        String msgOne = "Contact Us: 123-456-7890 | Email: info@wallyland.com.";
        String msgTwo = "Address: 123 WallyLand Ave, Fun City, USA";

        // Footer Panel
        JPanel footerPanel = footer.createFooterPanel(msgOne, msgTwo);
        footerPanel.setOpaque(false);
        footerPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        footerPanel.setPreferredSize(new Dimension(800, 60));
        add(footerPanel, BorderLayout.SOUTH);

    }

    private JPanel createTicketCard(String header, String price, String description) {

        JPanel cardPanel = new JPanel();
        cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.Y_AXIS)); // Stack components vertically
        cardPanel.setBorder(BorderFactory.createLineBorder(new Color(70, 130, 180), 6, true)); // Rounded border
        cardPanel.setBackground(new Color(170, 187, 192)); // gray background
        cardPanel.setPreferredSize(new Dimension(160, 160));

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
        JLabel headerLabel = new JLabel(header + price, JLabel.CENTER) {
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
        headerLabel.setFont(new Font("Arial", Font.BOLD, 14));
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

        // footer Panel to add space
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(new Color(152, 175, 197));
        footerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 15));

        cardPanel.add(footerPanel);

        // Fill remaining space
        cardPanel.add(Box.createVerticalGlue());

        return cardPanel;
    }

    @Override
    public void update(String message) {
        String[] cartEntries = message.split(";");
        ticketsPanel = (JPanel) getComponent(1); //third child

        ticketsPanel.removeAll(); // Clear current items
        
        for (String entry : cartEntries) {
            String[] parts = entry.split(":");
            if (parts.length == 2) {
                String header = parts[0];
                String price = parts[1];

                ticketsPanel.add(createTicketCard(header, price, ""));
                System.out.println(header + " " + price + " " + "");

                cartItems.add(header); //add to the arrayList
            }
        }

        ticketsPanel.removeAll();

        for (String item : cartItems) {
            ticketsPanel.add(createTicketCard(item, "", "Quantity: " ));
        }

        displayTotalItems();

        ticketsPanel.revalidate();
        ticketsPanel.repaint();
    }

    private void displayTotalItems() {
        int totalItems = cartItems.size(); // Get the total number of items in the cart

        // Assuming you have a JLabel to display the total number of items
        JLabel totalItemsLabel = new JLabel("Total items in cart: " + totalItems);
        ticketsPanel.add(totalItemsLabel);

        // Refresh the layout
        ticketsPanel.revalidate();
        ticketsPanel.repaint();
    }

    private void removeItem(String item) {
        cartItems.remove(item); // Remove the item from the ArrayList
        updateCartDisplay(); // Re-render the cart with updated items
    }

    private void updateCartDisplay() {
        ticketsPanel.removeAll();
        for (String item : cartItems) {
            ticketsPanel.add(createTicketCard(item, "", ""));
        }
        displayTotalItems(); // Re-display the total count of items
        ticketsPanel.revalidate();
        ticketsPanel.repaint();
    }
}
