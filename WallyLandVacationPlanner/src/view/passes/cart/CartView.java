package view.passes.cart;

import controller.ticketsandpasses.PassesController;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import view.ImageUtils;

/**
 * The CartView class represents the user interface (UI) for the shopping cart
 * in the Wallyland theme park application. It extends JFrame and is responsible
 * for displaying the cart window with the list of items the user has selected
 * for purchase. This class provides the main window for managing cart actions,
 * such as viewing the cart contents and interacting with the controller to
 * update the cart.
 *
 * The CartView initializes a CartPanel, a visual component of the cart, and
 * uses a PassesController to manage the interaction with the model. The window
 * includes an icon representing Wallyland and a non-resizable frame with a
 * predefined size.
 *
 * @author Ana
 */
public class CartView extends JFrame {

    private ImageIcon wallylandIcon;
    private CartPanel cartPanel;
    private PassesController controller;

    /**
     * Constructor for the CartView class. It sets up the frame properties,
     * initializes the controller, creates the CartPanel, and sets the window
     * icon for the Wallyland theme park application.
     *
     * This constructor: - Sets the window title to "Wallyland" - Initializes
     * the PassesController to manage the cart's data - Configures the frame
     * size, position, and behavior on close - Creates a CartPanel and adds it
     * to the window - Sets a custom icon image for the application window
     */
    public CartView() {
        super("Wallyland");

        this.controller = new PassesController();

        // Set frame properties
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 700);
        setLocationRelativeTo(null);
        setResizable(false);

        cartPanel = new CartPanel(controller);

        add(cartPanel);
        // Set icon
        wallylandIcon = ImageUtils.createIcon("/images/theme-park.png", 200, 200);
        if (wallylandIcon != null) {
            setIconImage(wallylandIcon.getImage());
        }

        setVisible(true);
    }

    /**
     * Closes the cart window by setting its visibility to false. This method
     * can be used to hide the CartView when the user finishes interacting with
     * the cart.
     */
    public void closeWindow() {
        this.setVisible(false);
    }

}
