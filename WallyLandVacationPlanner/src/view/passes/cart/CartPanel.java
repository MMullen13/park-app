package view.passes.cart;

import controller.ticketsandpasses.PassesController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import model.ticketsandpasses.CartItem;
import view.Card;
import view.Footer;
import view.Header;

/**
 *
 * @author Ana
 */
public class CartPanel extends JPanel {

    private Header header;
    private Footer footer;
    private JPanel ticketsPanel;
    private JPanel passesPanel;
    private String ticketType;
    private int quantity;
    private double totalPrice;
    private double price;
    private List<CartItem> ticketCartItemsList;
    private List<CartItem> passCartItemsList;
    private JLabel totalLabel;

    public CartPanel(PassesController controller) {
        ticketCartItemsList = new ArrayList<>();
        passCartItemsList = new ArrayList<>();
        header = new Header();
        footer = new Footer();
        totalLabel = new JLabel("Total Price at Checkout (incl. taxes): $0.00");

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // Use vertical BoxLayout

        Border innerBorder = BorderFactory.createTitledBorder("View Cart");
        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

        setBackground(new Color(235, 237, 238));

        // Header Panel
        JPanel headerPanel = header.createHeaderPanel("Wallyland Purchase Cart", null);
        headerPanel.setOpaque(false);
        headerPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        headerPanel.setPreferredSize(new Dimension(800, 34));
        add(headerPanel, BorderLayout.NORTH);

        JPanel refreshCartPanel = new JPanel();
        refreshCartPanel.setOpaque(false);
        refreshCartPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10)); // Left-align within refreshCartPanel
//        refreshCartPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding
        refreshCartPanel.setPreferredSize(new Dimension(20, 60));
        refreshCartPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));

        // Add a Refresh button
        JButton deleteCartItemsBtn = new JButton("Delete Cart Items");
        deleteCartItemsBtn.setBackground(new Color(152, 175, 197));
        deleteCartItemsBtn.setForeground(Color.WHITE);
        deleteCartItemsBtn.setFocusPainted(false); // Removes focus border on click
        deleteCartItemsBtn.setFont(new Font("Arial", Font.BOLD, 14));
        deleteCartItemsBtn.setPreferredSize(new Dimension(180, 46)); // Width, Height

        // Add hover effects
        deleteCartItemsBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                deleteCartItemsBtn.setBackground(new Color(132, 155, 177));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                deleteCartItemsBtn.setBackground(new Color(152, 175, 197)); // Original blue
            }

            @Override
            public void mousePressed(MouseEvent e) {
                deleteCartItemsBtn.setForeground(new Color(40, 95, 150));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                deleteCartItemsBtn.setForeground(Color.WHITE);
            }
        });

        deleteCartItemsBtn.addActionListener((ActionEvent e) -> {
            refreshTicketCart();
            refreshPassCart();
        });

        deleteCartItemsBtn.addActionListener((ActionEvent e) -> {
            int action = JOptionPane.showConfirmDialog(this,
                    "Do you really want to clear all items in the cart?",
                    "Confirm Cart Items Deletion",
                    JOptionPane.OK_CANCEL_OPTION);

            if (action == JOptionPane.OK_OPTION) {
                controller.clearCartFiles();
                passCartItemsList.clear();
                ticketCartItemsList.clear();
            }

            refreshTicketCart();
            refreshPassCart();

            // Update total price
            updateTotalLabel();
        });

        refreshCartPanel.add(deleteCartItemsBtn);
        add(refreshCartPanel, BorderLayout.CENTER);

        // Create the main panel for the tickets
        ticketsPanel = new JPanel();
        ticketsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Horizontally align cards
        ticketsPanel.setOpaque(false);
        ticketsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Padding
        ticketsPanel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center-align tickets panel
        refreshTicketCart();
        add(ticketsPanel);

        passesPanel = new JPanel();
        passesPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Horizontally align cards
        passesPanel.setOpaque(false);
        passesPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Padding
        passesPanel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center-align tickets panel
        refreshPassCart();
        add(passesPanel);

        
        totalLabel.setFont(new Font("Arial", Font.BOLD, 16));
        totalLabel.setForeground(new Color(40, 95, 150));
        totalLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(totalLabel);

        JPanel purchasePanel = new JPanel();
        purchasePanel.setOpaque(false);
        purchasePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Left-align within refreshCartPanel
        purchasePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding
        purchasePanel.setPreferredSize(new Dimension(800, 80));

        // Add a Refresh button
        JButton purchaseButton = new JButton("Checkout");
        purchaseButton.setBackground(new Color(58, 115, 169)); // Navy blue
        purchaseButton.setForeground(Color.WHITE);
        purchaseButton.setFocusPainted(false); // Removes focus border on click
        purchaseButton.setFont(new Font("Arial", Font.BOLD, 14));
        purchaseButton.setPreferredSize(new Dimension(160, 60)); // Width, Height

        // Add hover effects
        purchaseButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                purchaseButton.setBackground(new Color(40, 95, 150)); // Darker blue on hover
            }

            @Override
            public void mouseExited(MouseEvent e) {
                purchaseButton.setBackground(new Color(58, 115, 169)); // Original blue
            }

            @Override
            public void mousePressed(MouseEvent e) {
                purchaseButton.setForeground(new Color(40, 95, 150));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                purchaseButton.setForeground(Color.WHITE);
            }
        });

        purchaseButton.addActionListener((ActionEvent e) -> {
//            refreshCart();
        });
        purchasePanel.add(purchaseButton);
        add(purchasePanel, BorderLayout.CENTER);

        String msgOne = "Contact Us: 123-456-7890 | Email: info@wallyland.com.";
        String msgTwo = "Address: 123 WallyLand Ave, Fun City, USA";

        // Footer Panel
        JPanel footerPanel = footer.createFooterPanel(msgOne, msgTwo);
        footerPanel.setOpaque(false);
        footerPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        footerPanel.setPreferredSize(new Dimension(800, 60));
        add(footerPanel, BorderLayout.SOUTH);
    }

    private List<CartItem> readCartDataFromPassFile() {
        synchronized (this) {
            File file = new File("pass_cart_data.txt");

            if (!file.exists()) {
                return passCartItemsList;
            }

            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    try {
                        String[] parts = line.split(", ");
                        if (parts.length != 3) {
                            // Skip malformed lines
                            continue;
                        }

                        ticketType = parts[0].split(": ")[1];
                        quantity = Integer.parseInt(parts[1].split(":")[1].trim());
                        price = Double.parseDouble(parts[2].split("\\$")[1].trim());

                        passCartItemsList.add(new CartItem(ticketType, quantity, price));
                    } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                        // Log or handle the malformed line and continue reading
                        System.err.println("Malformed line skipped: " + line);
                    }
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error reading cart data from file: " + e.getMessage(),
                        "File Error", JOptionPane.ERROR_MESSAGE);
            }

            return passCartItemsList;
        }
    }

    private List<CartItem> readCartDataFromTicketFile() {
        synchronized (this) {
            File file = new File("ticket_cart_data.txt");

            if (!file.exists()) {
                return ticketCartItemsList;
            }

            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    try {
                        String[] parts = line.split(", ");
                        if (parts.length != 3) {
                            // Skip malformed lines
                            continue;
                        }

                        ticketType = parts[0].split(": ")[1];
                        quantity = Integer.parseInt(parts[1].split(":")[1].trim());
                        price = Double.parseDouble(parts[2].split("\\$")[1].trim());

                        ticketCartItemsList.add(new CartItem(ticketType, quantity, price));
                    } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                        // Log or handle the malformed line and continue reading
                        System.err.println("Malformed line skipped: " + line);
                    }
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error reading cart data from file: " + e.getMessage(),
                        "File Error", JOptionPane.ERROR_MESSAGE);
            }

            return ticketCartItemsList;
        }
    }

    private double calculateTotalPrice() {
        double total = 0.0;

        // Calculate total for tickets
        for (CartItem item : ticketCartItemsList) {
            double itemTotal = item.getQuantity() * item.getPrice();
            itemTotal += itemTotal * 0.07; // Adding 7% tax
            total += itemTotal;
        }

        // Calculate total for passes
        for (CartItem item : passCartItemsList) {
            double itemTotal = item.getQuantity() * item.getPrice();
            itemTotal += itemTotal * 0.07; // Adding 7% tax
            total += itemTotal;
        }

        return total;
    }

    private void refreshTicketCart() {
        ticketCartItemsList.clear(); // Clear the existing data
        readCartDataFromTicketFile(); // Re-read the file to load new data

        double ticketTypePrice;

        // Clear and update ticketsPanel
        ticketsPanel.removeAll();

        if (!ticketCartItemsList.isEmpty()) {
            for (CartItem item : ticketCartItemsList) {
                ticketTypePrice = item.getQuantity() * item.getPrice() + (item.getQuantity() * item.getPrice() * 0.07);
                Card card = new Card(item.getType(),
                        " - $" + item.getPrice(),
                        "Total for " + item.getQuantity() + " tickets (es): $" + ticketTypePrice,
                        215, 100);
                ticketsPanel.add(card);
            }
        }

        ticketsPanel.revalidate(); // Recalculate layout
        ticketsPanel.repaint();    // Refresh the UI

        updateTotalLabel();
    }

    private void refreshPassCart() {
        passCartItemsList.clear(); // Clear the existing data
        readCartDataFromPassFile(); // Re-read the file to load new data

        double passTypePrice;

        // Clear and update ticketsPanel
        passesPanel.removeAll();

        if (!passCartItemsList.isEmpty()) {
            for (CartItem item : passCartItemsList) {
                passTypePrice = item.getQuantity() * item.getPrice() + (item.getQuantity() * item.getPrice() * 0.07);
                Card card = new Card(item.getType(),
                        " - $" + item.getPrice(),
                        "Total for " + item.getQuantity() + " pass (es): $" + passTypePrice,
                        215, 100);
                passesPanel.add(card);
            }
        }

        passesPanel.revalidate(); // Recalculate layout
        passesPanel.repaint();    // Refresh the UI
        updateTotalLabel();
    }

    private void updateTotalLabel() {
        double total = calculateTotalPrice();
        totalLabel.setText(String.format("Total Price at Checkout (incl. taxes): $%.2f", total));
    }
}
