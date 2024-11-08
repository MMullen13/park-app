package view.loginsignup.login;

import controller.loginsignup.LoginController;
import java.awt.BorderLayout;
import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import model.loginsignup.LoginFormEvent;

/**
 * The LoginView class represents the main window of the WallyLand Park
 * Application. It sets up the user interface with a login form panel and
 * connects it to a controller. This class extends JFrame and manages the
 * layout, size, and initial positioning of the application's main frame.
 *
 * @author Ana
 */
public class LoginView extends JFrame {

    private LoginFormPanel formPanel;
    private LoginController controller;
    private ImageIcon wallylandIcon;

    /**
     * Constructs a LoginView frame, sets the layout and initializes components.
     * The frame is centered on the screen when opened, and the login form panel
     * is added to the frame.
     */
    public LoginView() {
        super("WallyLand");

        wallylandIcon = createIcon("/images/theme-park.png", 200, 200);
        if (wallylandIcon != null) {
            setIconImage(wallylandIcon.getImage()); // This should work if class extends JFrame
        }

        setLayout(new BorderLayout()); // Set up the main layout and components

        formPanel = new LoginFormPanel();
        controller = new LoginController();
        controller.setLoginView(this);

        // Register a listener to handle login form events
        formPanel.setFormListener((LoginFormEvent e) -> {
            controller.handleProfileUser(e);
        });

        // Add the form panel to the frame's center
        add(formPanel, BorderLayout.CENTER);

        // Configure frame properties
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setVisible(true);
    }

    /**
     * Creates an ImageIcon from the specified file path, resizes it to the
     * given width and height, and returns the resized icon.
     *
     * @param path The path to the image file.
     * @param w The desired width of the icon.
     * @param l The desired length (height) of the icon.
     * @return A resized ImageIcon.
     */
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
}
