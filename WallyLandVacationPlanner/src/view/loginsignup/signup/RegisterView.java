package view.loginsignup.signup;

import controller.loginsignup.LoginController;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import model.loginsignup.RegisterFormEvent;
import view.loginsignup.RegisterFormListenerIF;

/**
 *
 * @author Ana
 */
public class RegisterView extends JFrame {

    private RegisterFormPanel formPanel;
    private LoginController controller;

    public RegisterView() {
        super("WallyLand Park Application");
        controller = new LoginController();
        
        setLayout(new BorderLayout());

        formPanel = new RegisterFormPanel();
        
        formPanel.setFormListener((RegisterFormEvent e) -> {
//            String email = e.getEmail();
//            String password = e.getPassword();
//            String firstName = e.getFirstName();
//            String lastName = e.getLastName();
//            int age = e.getAge();
//            String phoneNum = e.getPhone();
//            
//            System.out.println(email + "\n" + password + "\n" + firstName + "\n" + lastName+ "\n" + age + "\n" + phoneNum);
            controller.handleNewUser(e);

        });

        //for testing purposses
        add(formPanel, BorderLayout.CENTER);
               
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
}
