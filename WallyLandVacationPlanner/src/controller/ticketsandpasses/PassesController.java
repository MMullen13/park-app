package controller.ticketsandpasses;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import model.ticketsandpasses.CartItem;
import model.ticketsandpasses.Pass;
import model.ticketsandpasses.Ticket;
import view.passes.tiketsandpasses.PassesView;
import view.passes.cart.CartView;
import view.passes.tiketsandpasses.TicketsView;

/**
 * The PassesController class handles the business logic for managing passes,
 * tickets, and cart items. It provides methods to update totals, save and load
 * cart data to/from files, and manage views related to passes, tickets, and the
 * cart.
 *
 * @author Ana
 */
public class PassesController {

    protected PassesView passView;
    protected TicketsView ticketsView;
    public CartView cartView;
    private List<CartItem> passesCartItems;
    private List<CartItem> ticketsCartItems;
    protected double totalPassesPrice = 0.0;
    protected double totalTicketsPrice = 0.0;
    protected Pass pass;
    protected Ticket ticket;

    /**
     * Constructs a new PassesController instance and initializes fields.
     */
    public PassesController() {
        pass = new Pass();
        ticket = new Ticket();
        passesCartItems = new ArrayList<>();
        ticketsCartItems = new ArrayList<>();
    }

    /**
     * Gets the current CartView instance.
     *
     * @return the current CartView
     */
    public CartView getCartView() {
        return cartView;
    }

    /**
     * Sets the CartView instance.
     *
     * @param cartView the CartView to set
     */
    public void setCartView(CartView cartView) {
        this.cartView = cartView;
    }

    /**
     * Gets the current TicketsView instance.
     *
     * @return the current TicketsView
     */
    public TicketsView getTicketsView() {
        return ticketsView;
    }

    /**
     * Sets the TicketsView instance.
     *
     * @param ticketsView the TicketsView to set
     */
    public void setTicketsView(TicketsView ticketsView) {
        this.ticketsView = ticketsView;
    }

    /**
     * Gets the current PassesView instance.
     *
     * @return the current PassesView
     */
    public PassesView getPassView() {
        return passView;
    }

    /**
     * Sets the PassesView instance.
     *
     * @param passView the PassesView to set
     */
    public void setPassView(PassesView passView) {
        this.passView = passView;
    }

    /**
     * Gets the list of cart items for passes.
     * 
     * @return a copy of the passes cart items list
     */
    public List<CartItem> getPassesCartItems() {
        return new ArrayList<>(passesCartItems); // Return a copy to avoid direct manipulation
    }

    /**
     * Gets the list of cart items for tickets.
     * 
     * @return a copy of the tickets cart items list
     */
    public List<CartItem> getTicketsCartItems() {
        return new ArrayList<>(ticketsCartItems); // Return a copy to avoid direct manipulation
    }

    /**
     * Updates the total price for passes based on the given type and quantity.
     * 
     * @param passType the type of pass
     * @param quantity the quantity of passes
     */
    public void updatePassTotals(String passType, int quantity) {
        totalPassesPrice += quantity * pass.getPriceForType(passType);
    }

    /**
     * Updates the total price for tickets based on the given type and quantity.
     * 
     * @param passType the type of ticket
     * @param quantity the quantity of tickets
     */
    public void updateTicketsTotals(String passType, int quantity) {
        totalTicketsPrice += quantity * ticket.getPriceForType(passType);
    }

    /**
     * Saves the pass cart data to a file.
     * 
     * @param cartItems a map of cart items with their types and quantities
     */
    public void savePassCartDataToFile(Map<String, Integer> cartItems) {
        synchronized (this) {
            File file = new File("pass_cart_data.txt");
            try (FileWriter writer = new FileWriter(file)) {
                for (Map.Entry<String, Integer> entry : cartItems.entrySet()) {
                    writer.write("type: " + entry.getKey() + ", quantity: " + entry.getValue() + ", price: $"
                            + pass.getPriceForType(entry.getKey()) + "\n");
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error saving cart data to file: " + e.getMessage(),
                        "File Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Loads the pass cart data from a file and updates the cart items list.
     */
    public void loadPassCartDataFromFile() {
        File file = new File("pass_cart_data.txt");

        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    String[] parts = line.split(", ");
                    if (parts.length != 3) {
                        continue;
                    }

                    String ticketType = parts[0].split(": ")[1];
                    int quantity = Integer.parseInt(parts[1].split(":")[1].trim());
                    double price = Double.parseDouble(parts[2].split("\\$")[1].trim());

                    passesCartItems.add(new CartItem(ticketType, quantity, price));
                } catch (NumberFormatException e) {
                    System.err.println("Malformed line skipped: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading cart data: " + e.getMessage());
        }
    }

    /**
     * Saves the ticket cart data to a file.
     * 
     * @param cartItems a map of cart items with their types and quantities
     */
    public void saveTicketCartDataToFile(Map<String, Integer> cartItems) {
        synchronized (this) {
            File file = new File("ticket_cart_data.txt");
            try (FileWriter writer = new FileWriter(file)) {
                for (Map.Entry<String, Integer> entry : cartItems.entrySet()) {
                    writer.write("type: " + entry.getKey() + ", quantity: " + entry.getValue() + ", price: $"
                            + ticket.getPriceForType(entry.getKey()) + "\n");
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error saving cart data to file: " + e.getMessage(),
                        "File Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Loads the ticket cart data from a file and updates the cart items list.
     */
    public void loadTicketCartDataFromFile() {
        File file = new File("ticket_cart_data.txt");

        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    String[] parts = line.split(", ");
                    if (parts.length != 3) {
                        continue;
                    }

                    String ticketType = parts[0].split(": ")[1];
                    int quantity = Integer.parseInt(parts[1].split(":")[1].trim());
                    double price = Double.parseDouble(parts[2].split("\\$")[1].trim());

                    ticketsCartItems.add(new CartItem(ticketType, quantity, price));
                } catch (NumberFormatException e) {
                    System.err.println("Malformed line skipped: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading cart data: " + e.getMessage());
        }
    }

    /**
     * Clears the cart data files for both passes and tickets.
     */
    public void clearCartFiles() {
        File passFile = new File("pass_cart_data.txt");
        File ticketFile = new File("ticket_cart_data.txt");

        if (passFile.exists()) {
            passFile.delete();
        }
        if (ticketFile.exists()) {
            ticketFile.delete();
        }
    }

    /**
     * Opens the CartView window. If a previous instance exists, it will be closed
     * */
    public void openCartView() {
        if (cartView != null) {
            cartView.closeWindow(); // Close the old window if it exists
        }
        cartView = new CartView(); // Create a new CartView instance
        cartView.setVisible(true); // Show the new CartView
    }
}
