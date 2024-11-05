package view.loginsignup.login;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;
import model.loginsignup.User;
import model.loginsignup.UserFactory;
import model.loginsignup.LoginFormEvent;
import view.loginsignup.signup.RegisterView;
import view.loginsignup.LoginFormListenerIF;

/**
 *
 * @author Ana
 */
public class LoginFormPanel extends JPanel {

    private JLabel emailLabel;
    private JLabel passwordLabel;
    private JTextField emailField;
    private JPasswordField passField;
    private JButton loginBtn;
    private JButton signUpBtn;
    private LoginFormListenerIF formListener;
    private JLabel signUpExplanationLabel;
    protected JLabel incorrectPassword;
    

    public LoginFormPanel() {
        emailLabel = new JLabel("Email");
        passwordLabel = new JLabel("Password");
        emailField = new JTextField(14);
        passField = new JPasswordField(14);
        loginBtn = new JButton("Sign In");
        signUpBtn = new JButton("Sign Up");
        incorrectPassword = new JLabel("");
        loginBtn.setPreferredSize(new Dimension(130, 40)); // Width: 130, Height: 30
        signUpBtn.setPreferredSize(new Dimension(130, 40)); // Width: 130, Height: 30

        signUpExplanationLabel = new JLabel("New User, Sign Up Here");
        
        passField.setEchoChar('*');
        
        Dimension dim = getPreferredSize();
        dim.width = 250;
        setPreferredSize(dim);

        Border innerBorder = BorderFactory.createTitledBorder("Log In");
        Border outerBorder = BorderFactory.createEmptyBorder(15, 15, 15, 15);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

        setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();

        // First row: Email label and field
        gc.gridy = 0;
        gc.weightx = 1;
        gc.weighty = 0.3;

        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(30, 0, 5, 5);
        add(emailLabel, gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(30, 0, 5, 0);
        add(emailField, gc);

        // Second row: Password label and field
        gc.gridy++;
        gc.weightx = 1;
        gc.weighty = 0.3;

        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(10, 0, 5, 5);
        add(passwordLabel, gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(10, 0, 5, 0);
        add(passField, gc);

        // Third row: Login button
        gc.gridy++;
        gc.weightx = 1;
        gc.weighty = 0.4;

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(10, 0, 5, 0); // Adding some top padding for spacing
        add(loginBtn, gc);

        // Fourth row: Sign up explanation label
        gc.gridy++;
        gc.weightx = 1;
        gc.weighty = 0.1;

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(15, 0, 0, 0); // Adding some top padding for spacing
        add(signUpExplanationLabel, gc);

        // Fifth row: Sign up button
        gc.gridy++;
        gc.weightx = 1;
        gc.weighty = 2.0;

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(5, 0, 0, 0);
        add(signUpBtn, gc);

        // Sixth row: Incorrect password label
        gc.gridy++;
        gc.weightx = 1;
        gc.weighty = 1;

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(5, 0, 25, 0);
        add(incorrectPassword, gc);

        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                char[] pass = passField.getPassword();
                String password = new String(pass);

                User user = (User) UserFactory.createUser(email, password, false);
                LoginFormEvent userEvent = new LoginFormEvent(this, user);
                
                if (formListener != null) {
                    formListener.formEventOccured(userEvent);
                }

               boolean isCorrectPassword = checkPassword(password);

                if (!isCorrectPassword) {
                    incorrectPassword.setText("Invalid Login Credentials!");
                }
            }
        });

        signUpBtn.addActionListener((ActionEvent e) -> {
            RegisterView signUpView = new RegisterView();
            signUpView.setVisible(true);
        });

    }

    public void setFormListener(LoginFormListenerIF formListener) {
        this.formListener = formListener;
    }

    private boolean checkPassword(String password) {
        return "password".equals(password);
    }
}
