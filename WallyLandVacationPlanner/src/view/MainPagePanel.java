package view;

import controller.foodordering.FoodController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.net.URL;
import javax.swing.*;
import javax.swing.border.Border;
import view.foodordering.OrderConfirmationViewForm;
import view.foodordering.OrderHistoryView;
import view.foodordering.OrderViewForm;
import view.passes.tiketsandpasses.PassesPanel;
import view.passes.tiketsandpasses.PassesView;
import view.passes.cart.CartView;
import view.passes.cart.CartPanel;

/**
 * Main Page Panel encapsulating the main page components.
 *
 * @author Ana
 */
public class MainPagePanel extends JPanel {

    private FoodController cntl;
    private OrderViewForm orderView;
    private OrderConfirmationViewForm confirmationView;
    private PassesView passView;
    private CartView ticketCartView;
    private ImageIcon wallylandImage;
    private FadedImagePanel backgroundPanel;
    private JPanel footerPanel;
    private Footer footer; 

    /**
     * Constructor
     */
    public MainPagePanel() {
        setLayout(new BorderLayout());

        cntl = new FoodController();
        orderView = new OrderViewForm(cntl);
        confirmationView = new OrderConfirmationViewForm(cntl);

        footer = new Footer();

        wallylandImage = createIcon("/images/pb.jpg", 1100, 900);
        backgroundPanel = new FadedImagePanel(wallylandImage);
        add(backgroundPanel, BorderLayout.CENTER);

        JMenuBar menuBar = createMenuBar();
        add(menuBar, BorderLayout.NORTH);
        
        String msgOne = "Contact Us: 123-456-7890 | Email: info@wallyland.com";
        String msgTwo = "Address: 123 WallyLand Ave, Fun City, USA";

        footerPanel = footer.createFooterPanel(msgOne, msgTwo);
        add(footerPanel, BorderLayout.SOUTH);
        
        Border innerBorder = BorderFactory.createTitledBorder("Home");
        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
    }

    private JMenuBar createMenuBar() {
        // Create a custom JMenuBar
        JMenuBar menuBar = new JMenuBar() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                // Apply anti-aliasing for smooth rendering
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Create a gradient background for the menu bar
                GradientPaint gradient = new GradientPaint(0, 0, new Color(17, 138, 200), getWidth(), 0, new Color(17, 138, 200));
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        menuBar.setPreferredSize(new Dimension(860, 80)); // Adjust height for a modern look

        // Customize menu items
        JMenu orderFood = createCustomMenu("Dining");
        JMenu viewMap = createCustomMenu("Park Map");
        JMenu purchaseTickets = createCustomMenu("Purchase Tickets");
        JMenu bday = createCustomMenu("Birthday Packages");
        JMenu info = createCustomMenu("Info");
        JMenu exit = createCustomMenu("Exit");

        // Create custom menu items with spacing
        JMenuItem newOrder = createCustomMenuItem("New Order");
        JMenuItem viewConfirmation = createCustomMenuItem("View Order Confirmation");
        JMenuItem orderHistory = createCustomMenuItem("Order History");

        JMenuItem tickets = createCustomMenuItem("Purchase a Day Pass");
        JMenuItem passes = createCustomMenuItem("Purchase a Season Pass");
        JMenuItem groupTickets = createCustomMenuItem("Purchase a Group Pass");
        JMenuItem promotions = createCustomMenuItem("More Deals");
        JMenuItem cart = createCustomMenuItem("View Cart");

        JMenuItem birthdayOne = createCustomMenuItem("Plan a Birthday For 10+ Guests");
        JMenuItem birthdayTwo = createCustomMenuItem("Plan a Birthday For 20+ Guests");

        JMenuItem map = createCustomMenuItem("Explore Park Map");

        JMenuItem events = createCustomMenuItem("Upcoming Events");
        JMenuItem attractions = createCustomMenuItem("Attractions");

        JMenuItem signOut = createCustomMenuItem("User Sign Out");

        // Add menus to the bar
        menuBar.add(purchaseTickets);
        menuBar.add(bday);
        menuBar.add(viewMap);
        menuBar.add(orderFood);
        menuBar.add(info);
        menuBar.add(exit);

        // Add items to the menus
        exit.add(signOut);

        viewMap.add(map);

        bday.add(birthdayOne);
        bday.add(birthdayTwo);

        info.add(events);
        info.add(attractions);

        orderFood.add(newOrder);
        orderFood.add(viewConfirmation);
        orderFood.add(orderHistory);

        purchaseTickets.add(tickets);
        purchaseTickets.add(passes);
        purchaseTickets.add(groupTickets);
        purchaseTickets.add(promotions);
        purchaseTickets.add(cart);

        // Attach actions
        cart.addActionListener(e -> handleCartView());
        tickets.addActionListener(e -> handleTicketPurchasing());
        passes.addActionListener(e -> handlePassPurchasing());
        newOrder.addActionListener(e -> handleNewOrder());
        viewConfirmation.addActionListener(e -> handleViewConfirmation());
        orderHistory.addActionListener(e -> handleOrderHistory());
        signOut.addActionListener(e -> handleSignOut());
        map.addActionListener(e -> handleParkMap());
        groupTickets.addActionListener(e -> handleParkMap());
        promotions.addActionListener(e -> handleParkMap());
        events.addActionListener(e -> handleParkMap());
        attractions.addActionListener(e -> handleParkMap());
        birthdayOne.addActionListener(e -> handleParkMap());
        birthdayTwo.addActionListener(e -> handleParkMap());

        menuBar.setBorder(BorderFactory.createEmptyBorder());

        return menuBar;
    }

    private JMenu createCustomMenu(String title) {
        JMenu menu = new JMenu(title) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Set background based on state
                if (getModel().isRollover() || getModel().isSelected()) {
                    g2d.setColor(new Color(58, 115, 169)); // Hover background color 
                } else {
                    g2d.setColor(new Color(17, 138, 200)); // Default background color 
                }
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 5, 5);

                // Draw the text
                g2d.setColor(Color.WHITE); // Text color
                FontMetrics fm = g2d.getFontMetrics();
                int textX = (getWidth() - fm.stringWidth(getText())) / 2; // Center text horizontally
                int textY = (getHeight() + fm.getAscent() - fm.getDescent()) / 2; // Center text vertically
                g2d.drawString(getText(), textX, textY);
            }
        };

        menu.setFont(new Font("Arial", Font.BOLD, 18));
        menu.setOpaque(false); // Ensure transparency
        menu.setBorder(BorderFactory.createEmptyBorder(12, 21, 12, 20)); // Add padding
        return menu;
    }

    private JMenuItem createCustomMenuItem(String title) {
        JMenuItem menuItem = new JMenuItem(title) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Draw custom hover background
                if (getModel().isArmed()) {  // When hovered or selected
                    g2d.setColor(new Color(17, 138, 200)); // Light blue hover background
                    g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 5, 5);
                    g2d.setColor(Color.WHITE); // Change text color to white on hover
                } else {
                    g2d.setColor(getBackground()); // Default background
                    g2d.fillRect(0, 0, getWidth(), getHeight());
                    g2d.setColor(getForeground()); // Default text color
                }

                // Draw the text (centered)
                FontMetrics fm = g2d.getFontMetrics();
                int textX = (getWidth() - fm.stringWidth(getText())) / 2; // Center text horizontally
                int textY = (getHeight() + fm.getAscent() - fm.getDescent()) / 2; // Center text vertically
                g2d.drawString(getText(), textX, textY);

                // Don't call super.paintComponent(g) as we're handling all rendering
            }
        };

        menuItem.setFont(new Font("Arial", Font.PLAIN, 16));
        menuItem.setAlignmentX(Component.LEFT_ALIGNMENT);
        menuItem.setForeground(Color.BLACK);  // Default text color
        menuItem.setOpaque(false); // Ensure transparency
        menuItem.setBackground(new Color(240, 240, 240)); // Default background color
        menuItem.setBorder(BorderFactory.createEmptyBorder(15, 3, 15, 3)); // Add padding
        return menuItem;
    }

    private void handleTicketPurchasing() {

    }
    
    private void handlePassPurchasing() {
        passView = new PassesView();
        passView.setVisible(true);
        
        
    }
    
    private void handleCartView() {
        ticketCartView = new CartView();
        ticketCartView.setVisible(true);
    }

    private void handleNewOrder() {
        if (cntl.isOrderPickedUp()) {
            if (cntl.getOrderView() == null) {
                cntl.setOrderView(new OrderViewForm(cntl));
            }
            cntl.getOrderView().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "You must complete and pick up your current order before creating a new one.");
        }
    }

    private void handleViewConfirmation() {
        if (cntl.getConfirmationView() != null) {
            cntl.getConfirmationView().setVisible(true);
        } else {
            System.out.println("Confirmation view is not initialized.");
        }
    }

    private void handleOrderHistory() {
        OrderHistoryView history = new OrderHistoryView();
        history.setVisible(true);
    }
    
    private void handleParkMap() {
        ComingSoonView history = new ComingSoonView();
        history.setVisible(true);
    }
    
    private void handleBdayPackages() {
        ComingSoonView history = new ComingSoonView();
        history.setVisible(true);
    }
    
    private void handleInfo() {
        ComingSoonView history = new ComingSoonView();
        history.setVisible(true);
    }

    private void handleSignOut() {
        int action = JOptionPane.showConfirmDialog(this, "Do you really want to Sign Out?", "Confirm Sign Out", JOptionPane.OK_CANCEL_OPTION);
        if (action == JOptionPane.OK_OPTION) {
            JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
            topFrame.dispose();
        }
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
