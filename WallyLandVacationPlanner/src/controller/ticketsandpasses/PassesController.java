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
 *
 * @author Ana
 */
public class PassesController {

    protected PassesView passView;
    protected TicketsView ticketsView;
    protected CartView cartView;
    private List<CartItem> passesCartItems;
    private List<CartItem> ticketsCartItems;
    private double totalPassesPrice = 0.0;
    private double totalTicketsPrice = 0.0;
    protected Pass pass;
    protected Ticket ticket;

    public PassesController() {
        pass = new Pass();
        ticket = new Ticket();
        passesCartItems = new ArrayList<>();
        ticketsCartItems = new ArrayList<>();
    }

    public CartView getCartView() {
        return cartView;
    }

    public void setCartView(CartView cartView) {
        this.cartView = cartView;
    }

    public TicketsView getTicketsView() {
        return ticketsView;
    }

    public void setTicketsView(TicketsView ticketsView) {
        this.ticketsView = ticketsView;
    }

    public PassesView getPassView() {
        return passView;
    }

    public void setPassView(PassesView passView) {
        this.passView = passView;
    }

//    public List<CartItem> getPassesCartItems() {
//        return new ArrayList<>(passesCartItems); // Return a copy to avoid direct manipulation
//    }
//    
//    public List<CartItem> getTicketsCartItems() {
//        return new ArrayList<>(ticketsCartItems); // Return a copy to avoid direct manipulation
//    }

    public void updatePassTotals(String passType, int quantity) {
        totalPassesPrice += quantity * pass.getPriceForType(passType);
    }
    
    public void updateTicketsTotals(String passType, int quantity) {
        totalTicketsPrice += quantity * ticket.getPriceForType(passType);
    }

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
}
