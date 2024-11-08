package view.loginsignup.signup;

import controller.loginsignup.LoginController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import model.loginsignup.RegisterFormEvent;

/**
 * The RegisterView class represents the main window of the WallyLand Park
 * Application. It sets up the user interface with a register form panel and
 * connects it to a controller. This class extends JFrame and manages the
 * layout, size, and initial positioning of the application's main frame.
 *
 * @author Ana
 */
public class RegisterView extends JFrame {

    private RegisterFormPanel formPanel;
    private LoginController controller;
    private ImageIcon wallylandIcon;

    /**
     * Constructs a RegisterView frame, sets the layout and initializes
     * components. The frame is centered on the screen when opened, and the
     * register form panel is added to the frame.
     */
    public RegisterView() {
        super("WallyLand");
        this.controller = new LoginController();

        controller.setRegisterView(this);

        wallylandIcon = createIcon("/images/theme-park.png", 200, 200);
        if (wallylandIcon != null) {
            setIconImage(wallylandIcon.getImage()); // This should work if class extends JFrame
        }

        setLayout(new BorderLayout());

        formPanel = new RegisterFormPanel();

        // Register a listener to handle login form events
        formPanel.setFormListener((RegisterFormEvent e) -> {
            controller.handleNewUser(e);
//            completeRegistration();
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent ev) {
                System.out.println("Registration window closing");
                dispose(); //dispose the registration window
                System.gc(); //run the garbage collector
            }
        });

        // Add the form panel to the frame's center
        add(formPanel, BorderLayout.CENTER);

        // Configure frame properties
        setSize(600, 700);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);

        setVisible(true);
    }

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

    public void displayPhoneError(String message) {
        formPanel.phoneErrorLabel.setText("Invalid Phone Format");
        formPanel.phoneErrorLabel.setForeground(Color.red);
        updateSuccessState();
    }

    public void displayFirstNameError(String message) {
        formPanel.firstNameErrorLabel.setForeground(Color.RED);
        formPanel.firstNameErrorLabel.setText("Invalid First Name");
        updateSuccessState();
    }

    public void displayLastNameError(String message) {
        formPanel.lastNameErrorLabel.setForeground(Color.RED);
        formPanel.lastNameErrorLabel.setText("Invalid Last Name");
        updateSuccessState();
    }

    public void displayEmailError(String message) {
        formPanel.emailErrorLabel.setForeground(Color.RED);
        formPanel.emailErrorLabel.setText("Invalid Email");
        updateSuccessState();
    }

    public void displayPasswordError(String message) {
        formPanel.passwordErrorLabel.setForeground(Color.RED);
        formPanel.passwordErrorLabel.setText("Invalid Password");
        updateSuccessState();
    }

    public void clearFirstNameError() {
        formPanel.firstNameErrorLabel.setText("");
        updateSuccessState();
    }

    public void clearLastNameError() {
        formPanel.lastNameErrorLabel.setText("");
        updateSuccessState();
    }

    public void clearEmailError() {
        formPanel.emailErrorLabel.setText("");
        updateSuccessState();
    }

    public void clearPasswordError() {
        formPanel.passwordErrorLabel.setText("");
        updateSuccessState();
    }

    public void clearPhoneError() {
        formPanel.phoneErrorLabel.setText("");
        updateSuccessState();
    }

    public void updateSuccessState() {
        // Check if any error label has text
        boolean hasError = !formPanel.firstNameErrorLabel.getText().isEmpty()
                || !formPanel.lastNameErrorLabel.getText().isEmpty()
                || !formPanel.emailErrorLabel.getText().isEmpty()
                || !formPanel.passwordErrorLabel.getText().isEmpty()
                || !formPanel.phoneErrorLabel.getText().isEmpty();

        // Disable or enable the success label based on whether there are errors
        formPanel.successLabel.setVisible(!hasError);
    }

}
