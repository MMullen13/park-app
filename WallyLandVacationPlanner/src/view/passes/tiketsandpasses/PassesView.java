package view.passes.tiketsandpasses;

import controller.ticketsandpasses.PassesController;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import view.ImageUtils;

/**
 * The PassesView class represents the user interface (UI) for the passes
 * shopping cart in the Wallyland theme park application. It extends JFrame and
 * is responsible for displaying the cart window with the list of items the user
 * has selected for purchase. This class provides the main window for managing
 * pass actions, such as viewing the pass contents and interacting with the
 * controller to update the cart.
 *
 * The PassesView initializes a CartPanel, a visual component of the cart, and
 * uses a PassesController to manage the interaction with the model. The window
 * includes an icon representing Wallyland and a non-resizable frame with a
 * predefined size.
 *
 * @author Ana
 */
public class PassesView extends JFrame {

    private ImageIcon wallylandIcon;
    private PassesPanel passPanel;
    private PassesController controller;

    /**
     * Constructor for the PassesView class. It sets up the frame properties,
     * initializes the controller, creates the CartPanel, and sets the window
     * icon for the Wallyland theme park application.
     *
     * This constructor: - Sets the window title to "Wallyland" - Initializes
     * the PassesController to manage the pass's data - Configures the frame
     * size, position, and behavior on close - Creates a PassesPanel and adds it
     * to the window - Sets a custom icon image for the application window
     */
    public PassesView() {
        super("Wallyland");

        this.controller = new PassesController();
        controller.setPassView(this);

        // Set frame properties
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 700);
        setLocationRelativeTo(null);
        setResizable(false);

        // Set icon
        wallylandIcon = ImageUtils.createIcon("/images/theme-park.png", 200, 200);
        if (wallylandIcon != null) {
            setIconImage(wallylandIcon.getImage());
        }

        // Add ticket panel
        this.passPanel = new PassesPanel(controller);
        add(passPanel);

        setVisible(true);
    }

    /**
     * Closes the passes window by setting its visibility to false. This method
     * can be used to hide the PassesView when the user finishes interacting
     * with the passes.
     */
    public void closeWindow() {
        this.setVisible(false);
    }
}
