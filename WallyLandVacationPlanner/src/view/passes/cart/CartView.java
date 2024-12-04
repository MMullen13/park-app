package view.passes.cart;


import controller.ticketsandpasses.PassesController;
import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 *
 * @author Ana
 */
public class CartView extends JFrame {

    private ImageIcon wallylandIcon;
    private CartPanel cartPanel;
    private PassesController controller;

    public CartView() {
        super("Wallyland");
        
        this.controller = new PassesController();

        controller.setCartView(this);

        // Set frame properties
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 700);
        setLocationRelativeTo(null);
        setResizable(false);
        
        cartPanel = new CartPanel();       
        
        add(cartPanel);
        // Set icon
        wallylandIcon = createIcon("/images/theme-park.png", 200, 200);
        if (wallylandIcon != null) {
            setIconImage(wallylandIcon.getImage());
        }

        setVisible(true);
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

    public void updateCart(String passType, int passQuantity) {
        // Format the update message
        String updateMessage = passType + ":" + passQuantity;
    }

    public void addToCart(String passType, int quantity, double totalPrice) {
        cartPanel.addItemToCart(passType, quantity, totalPrice);
    }

    public void updateCartView() {
        cartPanel.refreshCart();
    }

}
