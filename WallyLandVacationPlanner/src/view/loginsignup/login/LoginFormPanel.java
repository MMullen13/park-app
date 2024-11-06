package view.loginsignup.login;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
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
    private JLabel incorrectPassword;
    private JLabel incorrectPasswordIcon;
    private Icon emailIcon;
    private Icon passwordIcon;
    private Icon incorrPassIcon;

    public LoginFormPanel() {

        emailField = new JTextField(14);
        passField = new JPasswordField(14);
        loginBtn = new JButton("Sign In");
        signUpBtn = new JButton("Sign Up");
        incorrectPassword = new JLabel("");
        incorrectPasswordIcon = new JLabel("");
        signUpExplanationLabel = new JLabel("New User, Sign Up Here");
        emailIcon = createIcon("/images/icons8-email.png", 40, 40);
        passwordIcon = createIcon("/images/icons8-lock.png", 40, 40);
        incorrPassIcon = createIcon("/images/icons8-error.png", 50, 50);

        emailLabel = new JLabel("Email", emailIcon, SwingConstants.RIGHT);
        passwordLabel = new JLabel("Password", passwordIcon, SwingConstants.LEFT);

        passField.setEchoChar('*');
        loginBtn.setPreferredSize(new Dimension(130, 40)); // Width: 130, Height: 30
        signUpBtn.setPreferredSize(new Dimension(130, 40)); // Width: 130, Height: 30

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
        gc.insets = new Insets(10, 0, 5, 5);
        add(emailLabel, gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(10, 0, 5, 0);
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

        // Seventh row: Incorrect password icon label
        gc.gridy++; 
        gc.weightx = 0; 
        gc.weighty = 0; 

        gc.gridx = 0; // Set to 0 to make sure it starts from the left side of the panel
        gc.gridwidth = 3;  // Span across the width of the panel
        gc.anchor = GridBagConstraints.CENTER;  // Center the icon within the grid cell
        gc.insets = new Insets(10, 0, 0, 0);  // Adjust the bottom padding as needed
        add(incorrectPasswordIcon, gc);

        // Sixth row: Incorrect password label
        gc.gridy++;
        gc.weightx = 1;
        gc.weighty = 1;

        gc.gridx = 0;
        gc.gridwidth = 3;  
        gc.anchor = GridBagConstraints.CENTER;  // Center the label horizontally
        gc.insets = new Insets(0, 0, 35, 0); // Adding space at the bottom
        add(incorrectPassword, gc);
        loginBtn.setIcon(createIcon("/images/icons8-user.png", 20, 20));
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
                    incorrectPasswordIcon.setIcon(incorrPassIcon);
                    incorrectPasswordIcon.setHorizontalAlignment(SwingConstants.CENTER);
                    incorrectPasswordIcon.setVerticalAlignment(SwingConstants.TOP);
                }
            }
        });

        signUpBtn.setIcon(createIcon("/images/icons8-add-user.png", 20, 20));
        signUpBtn.addActionListener((ActionEvent e) -> {
            RegisterView signUpView = new RegisterView();
            signUpView.setVisible(true);
        });

        setBackground(new Color(227, 236, 241));

    }

    public void setFormListener(LoginFormListenerIF formListener) {
        this.formListener = formListener;
    }

    private boolean checkPassword(String password) {
        return "password".equals(password);
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
}
