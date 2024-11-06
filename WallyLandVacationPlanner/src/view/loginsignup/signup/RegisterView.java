package view.loginsignup.signup;

import controller.loginsignup.LoginController;
import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import model.loginsignup.RegisterFormEvent;
import view.loginsignup.login.LoginView;

/**
 * The RegisterView class represents the main window of the WallyLand Park Application.
 * It sets up the user interface with a register form panel and connects it to a controller.
 * This class extends JFrame and manages the layout, size, and initial positioning
 * of the application's main frame.
 * 
 * @author Ana
 */
public class RegisterView extends JFrame {

    private RegisterFormPanel formPanel;
    private LoginController controller;

       /**
     * Constructs a RegisterView frame, sets the layout and initializes components.
     * The frame is centered on the screen when opened, and the register form panel
     * is added to the frame.
     */
    public RegisterView() {
        super("WallyLand Park Application");
        this.controller = new LoginController();

        setLayout(new BorderLayout());

        formPanel = new RegisterFormPanel();

        // Register a listener to handle login form events
        formPanel.setFormListener((RegisterFormEvent e) -> {
            controller.handleNewUser(e);
//            completeRegistration();
        });
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent ev){
                System.out.println("Registration window clossiing");
                dispose(); //dispose the registration window
                System.gc(); //run the garbage collector
            }
        });
        
        // Add the form panel to the frame's center
        add(formPanel, BorderLayout.CENTER);

        // Configure frame properties
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);

        setVisible(true);
    }

}
