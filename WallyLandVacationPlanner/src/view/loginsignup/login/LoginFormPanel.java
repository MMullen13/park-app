package view.loginsignup.login;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
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
    private JLabel eyeLabel;
    private Icon emailIcon;
    private Icon passwordIcon;
    private Icon incorrPassIcon;
    private Icon eyeIcon;
    private boolean passwordVisible = false;

    public LoginFormPanel() {

        emailField = new JTextField("user@example.com", 16);
        passField = new JPasswordField(16);
        loginBtn = new JButton("Sign In");
        signUpBtn = new JButton("Sign Up");
        incorrectPassword = new JLabel("");
        incorrectPasswordIcon = new JLabel("");
        signUpExplanationLabel = new JLabel("New User, Sign Up Here");
        emailIcon = createIcon("/images/icons8-email.png", 40, 40);
        passwordIcon = createIcon("/images/icons8-lock.png", 40, 40);
        incorrPassIcon = createIcon("/images/icons8-error.png", 50, 50);
        eyeIcon = createIcon("/images/icons8-eye.png", 35, 35);
        
        signUpExplanationLabel.setForeground(Color.GRAY);

        emailField.setForeground(Color.LIGHT_GRAY);
        passField.setForeground(Color.LIGHT_GRAY);
        passField.setText("Password");
        passField.setEchoChar((char) 0);

        setTextField(emailField, "user@example.com");

        // add a focus listener to enter the password into the password field
        passField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                // check if the placeholder text is present
                if (new String(passField.getPassword()).equals("Password")) {
                    passField.setText("");  // clear the placeholder text
                    passField.setForeground(Color.BLACK);  // set text color for black
                    passField.setEchoChar('*');  // hide characters with the echo char
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                // if the field is empty, reset to placeholder
                if (new String(passField.getPassword()).isEmpty()) {
                    passField.setForeground(Color.LIGHT_GRAY); 
                    passField.setText("Password");  // reset placeholder text
                    passField.setEchoChar((char) 0);  // show text as plain (no echo char)
                }
            }
        });

        emailLabel = new JLabel("Email", emailIcon, SwingConstants.RIGHT);
        passwordLabel = new JLabel("Password", passwordIcon, SwingConstants.LEFT);
        eyeLabel = new JLabel(eyeIcon);
        eyeLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        // add a mouse listener to toggle password visibility
        eyeLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                passwordVisible = !passwordVisible;  // toggle visibility state
                if (passwordVisible) {
                    passField.setEchoChar((char) 0);  // show password
                } else {
                    passField.setEchoChar('*');  // hide password
                }
            }
        });

        loginBtn.setPreferredSize(new Dimension(130, 40)); // Width: 130, Height: 30
        signUpBtn.setPreferredSize(new Dimension(130, 40)); // Width: 130, Height: 30

        Dimension dim = getPreferredSize();
        dim.width = 250;
        setPreferredSize(dim);

        Border innerBorder = BorderFactory.createTitledBorder("Log In");
        Border outerBorder = BorderFactory.createEmptyBorder(15, 15, 15, 15);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

        layoutControls(); //add the layout controllers

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

    private void setTextField(JTextField field, String message) {
        field.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (field.getText().equals(message)) {
                    field.setText("");
                    field.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (field.getText().isEmpty()) {
                    field.setForeground(Color.LIGHT_GRAY);
                    field.setText(message);
                }
            }
        });
    }

    private void layoutControls() {
        setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();
        // First row: Email label and field
        gc.gridy = 0;
        gc.weightx = 1;
        gc.weighty = 0.1;

        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(10, 0, 0, 5);
        add(emailLabel, gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(10, 0, 0, 0);
        add(emailField, gc);

        // Second row: Password label and field
        gc.gridy++;
        gc.weightx = 1;
        gc.weighty = 0.1;

        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(10, 0, 0, 5);
        add(passwordLabel, gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(10, 0, 0, 0);
        add(passField, gc);

        gc.gridx = 2;
        gc.anchor = GridBagConstraints.LINE_START; 
        gc.insets = new Insets(10, -90, 0, 0); // set negative left padding to bring eyeLabel closer
        add(eyeLabel, gc);

        // Third row: Login button
        gc.gridy++;
        gc.weightx = 0;
        gc.weighty = 0.5;

        gc.gridx = 0;
        gc.gridwidth = 3;
        gc.anchor = GridBagConstraints.CENTER;
        gc.insets = new Insets(0, 0, 5, 0); 
        add(loginBtn, gc);

        // Fourth row: Sign up explanation label
        gc.gridy++;
        gc.weightx = 1;
        gc.weighty = 0.1;

        gc.gridx = 0;
        gc.gridwidth = 3;
        gc.anchor = GridBagConstraints.CENTER;
        gc.insets = new Insets(25, 0, 0, 0);
        add(signUpExplanationLabel, gc);

        // Fifth row: Sign up button
        gc.gridy++;
        gc.weightx = 1;
        gc.weighty = 0.2;

        gc.gridx = 0;
        gc.gridwidth = 3;
        gc.anchor = GridBagConstraints.CENTER;
        gc.insets = new Insets(0, 0, 0, 0);
        add(signUpBtn, gc);

        // Seventh row: Incorrect password icon label
        gc.gridy++;
        gc.weightx = 0;
        gc.weighty = 0;

        gc.gridx = 0; // set to 0 to make sure it starts from the left side of the panel
        gc.gridwidth = 3;  // span across the width of the panel
        gc.anchor = GridBagConstraints.CENTER;  
        gc.insets = new Insets(30, 0, 0, 0);  
        add(incorrectPasswordIcon, gc);

        // Sixth row: Incorrect password label
        gc.gridy++;
        gc.weightx = 1;
        gc.weighty = 1;

        gc.gridx = 0;
        gc.gridwidth = 3;
        gc.anchor = GridBagConstraints.CENTER;  // Center the label horizontally
        gc.insets = new Insets(0, 0, 50, 0); 
        add(incorrectPassword, gc);
    }
}
