package view.loginsignup.signup;

import model.loginsignup.ProfileUser;
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
import javax.swing.JTextField;
import javax.swing.border.Border;
import view.loginsignup.FormListenerIF;

/**
 *
 * @author Ana
 */
public class SignUpFormPanel extends JPanel {

    private JLabel emailLabel;
    private JLabel passwordLabel;
    private JLabel ageLabel;
    private JLabel phoneLabel;
    private JLabel firstNameLabel;
    private JLabel lastNameLabel;
    private JTextField emailField;
    private JTextField passwordField;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField ageField;
    private JTextField phoneField;    
    private JButton registerBtn;

    private FormListenerIF formListener;

    /**
     * Constructor
     */
    public SignUpFormPanel() {
        emailLabel = new JLabel("Email");
        passwordLabel = new JLabel("Password");
        firstNameLabel = new JLabel("First Name");
        lastNameLabel = new JLabel("Last Name");
        ageLabel = new JLabel("Age");
        phoneLabel = new JLabel("Phone Number");
        emailField = new JTextField(16);
        passwordField = new JTextField(16);
        firstNameField = new JTextField(16);
        lastNameField = new JTextField(16);
        ageField = new JTextField(16);
        phoneField = new JTextField(16);

        registerBtn = new JButton("Register");
        registerBtn.setPreferredSize(new Dimension(130, 40)); // Width: 130, Height: 30


        Dimension dim = getPreferredSize();
        dim.width = 250;
        setPreferredSize(dim);

        Border innerBorder = BorderFactory.createTitledBorder("New User Sign Up");
        Border outerBorder = BorderFactory.createEmptyBorder(15, 15, 15, 15);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

        setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();

        // First row: Email label and field
        gc.gridy = 0;
        gc.weightx = 1;
        gc.weighty = 0.15;

        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0, 0, 5, 5);
        add(emailLabel, gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, 0, 5, 0);
        add(emailField, gc);
        
        // Second row: Password label and field
        gc.gridy++;
        gc.weightx = 1;
        gc.weighty = 0.15;

        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0, 0, 5, 5);
        add(passwordLabel, gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, 0, 5, 0);
        add(passwordField, gc);
        
        // Third row: First Name label and field
        gc.gridy++;
        gc.weightx = 1;
        gc.weighty = 0.15;

        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0, 0, 5, 5);
        add(firstNameLabel, gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, 0, 5, 0);
        add(firstNameField, gc);

        // Fourth row: Last Name label and field
        gc.gridy++;
        gc.weightx = 1;
        gc.weighty = 0.15;

        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0, 0, 5, 5);
        add(lastNameLabel, gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, 0, 5, 0);
        add(lastNameField, gc);

        // Fifth row: Age label and field
        gc.gridy++;
        gc.weightx = 1;
        gc.weighty = 0.15;

        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0, 0, 5, 5);
        add(ageLabel, gc);
        
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, 0, 5, 0);
        add(ageField, gc);

        // Sixth row: Phone label and field
        gc.gridy++;
        gc.weightx = 1;
        gc.weighty = 0.15;

        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0, 0, 5, 5);
        add(phoneLabel, gc);
        
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, 0, 5, 0);
        add(phoneField, gc);
        
        // Seventh row: Register button
        gc.gridy++;
        gc.weightx = 1;
        gc.weighty = 1;

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(10, 0, 5, 0); // Adding some top padding for spacing
        add(registerBtn, gc);
        
        
        registerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String password = passwordField.getText();

                ProfileUser credentials = new ProfileUser(this, email, password);

                if (formListener != null) {
                    formListener.formEventOccured(credentials);
                }

                boolean isCorrectPassword = checkPassword(password);

                if (!isCorrectPassword) {
//                    incorrectPassword.setText("Invalid Login Credentials!");
                }
            }
        });

    }

    public void setFormListener(FormListenerIF formListener) {
        this.formListener = formListener;
    }

    private boolean checkPassword(String password) {
        return "password".equals(password);
    }
}
