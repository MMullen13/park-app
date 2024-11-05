package view.loginsignup.login;

import controller.loginsignup.LoginController;
import java.awt.BorderLayout;
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

    /**
     * Constructs a LoginView frame, sets the layout and initializes components.
     * The frame is centered on the screen when opened, and the login form panel
     * is added to the frame.
     */
    public LoginView() {
        super("WallyLand Park Application");

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
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        setVisible(true);
    }
}
