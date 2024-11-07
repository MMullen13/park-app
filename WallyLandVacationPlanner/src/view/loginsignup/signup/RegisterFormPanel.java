package view.loginsignup.signup;

import view.loginsignup.AgeCategoryUtils;
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
import java.awt.event.FocusListener;
import java.net.URL;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import model.loginsignup.NewUser;
import model.loginsignup.User;
import model.loginsignup.UserFactory;
import model.loginsignup.RegisterFormEvent;
import view.loginsignup.RegisterFormListenerIF;

/**
 *
 * @author Ana
 */
public class RegisterFormPanel extends JPanel {

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
    private JTextField phoneField;
    private JButton registerBtn;
    private JList ageList;
    private Icon emailIcon;
    private Icon passwordIcon;
    private Icon firstNameIcon;
    private Icon ageIcon;
    private Icon phoneIcon;

    private RegisterFormListenerIF formListener;

    /**
     * Constructor
     */
    public RegisterFormPanel() {
        emailField = new JTextField("user@example.com", 18);
        passwordField = new JTextField("password", 18);
        firstNameField = new JTextField("John", 18);
        lastNameField = new JTextField("Doe", 18);
        phoneField = new JTextField("(215) 123-4567", 18);
        ageList = new JList();
        emailIcon = createIcon("/images/icons8-email.png", 40, 40);
        passwordIcon = createIcon("/images/icons8-lock.png", 40, 40);
        firstNameIcon = createIcon("/images/icons8-name-tag.png", 50, 50);
        ageIcon = createIcon("/images/icons8-calendar.png", 60, 60);
        phoneIcon = createIcon("/images/icons8-phone.png", 40, 40);

        emailField.setForeground(Color.LIGHT_GRAY);
        passwordField.setForeground(Color.LIGHT_GRAY);
        firstNameField.setForeground(Color.LIGHT_GRAY);
        lastNameField.setForeground(Color.LIGHT_GRAY);
        phoneField.setForeground(Color.LIGHT_GRAY);

        setTextField(emailField, "user@example.com");
        setTextField(passwordField, "password");
        setTextField(firstNameField, "John");
        setTextField(lastNameField, "Doe");
        setTextField(phoneField, "(215) 123-4567");

        emailLabel = new JLabel("Email", emailIcon, SwingConstants.RIGHT);
        passwordLabel = new JLabel("Password", passwordIcon, SwingConstants.LEFT);
        firstNameLabel = new JLabel("First Name", firstNameIcon, SwingConstants.RIGHT);
        lastNameLabel = new JLabel("Last Name");
        ageLabel = new JLabel("Age", ageIcon, SwingConstants.RIGHT);
        phoneLabel = new JLabel("Phone Number", phoneIcon, SwingConstants.LEFT);

        DefaultListModel ageModel = new DefaultListModel();

        ageModel.addElement(
                new AgeCategoryUtils(0, "Under 3"));
        ageModel.addElement(
                new AgeCategoryUtils(1, "3 to 18"));
        ageModel.addElement(
                new AgeCategoryUtils(2, "18 to 65"));
        ageModel.addElement(
                new AgeCategoryUtils(3, "65 or over"));
        ageList.setModel(ageModel);

        ageList.setPreferredSize(
                new Dimension(165, 75));
        ageList.setBorder(BorderFactory.createEtchedBorder());
        ageList.setSelectedIndex(1);

        registerBtn = new JButton("Register");
        registerBtn.setPreferredSize(new Dimension(140, 40)); // Width: 130, Height: 30

        Dimension dim = getPreferredSize();
        dim.width = 250;

        setPreferredSize(dim);

        Border innerBorder = BorderFactory.createTitledBorder("New User Sign Up");
        Border outerBorder = BorderFactory.createEmptyBorder(15, 15, 15, 15);

        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

        setControls();

        registerBtn.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                String email = emailField.getText();
                String password = passwordField.getText();
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                String phoneNum = phoneField.getText();
                AgeCategoryUtils ageCategory = (AgeCategoryUtils) ageList.getSelectedValue();

                User user = (User) UserFactory.createUser(email, password, true);

                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setAge(ageCategory.getID());
                user.setPhoneNum(phoneNum);

                RegisterFormEvent userEvent = new RegisterFormEvent(this, (NewUser) user);
                if (formListener != null) {
                    formListener.formEventOccured(userEvent);
                }

                boolean isCorrectPassword = checkPassword(password);

                if (!isCorrectPassword) {
//                    incorrectPassword.setText("Invalid Login Credentials!");
                }
            }
        }
        );

        registerBtn.setIcon(createIcon("/images/icons8-add-user.png", 20, 20));

        setBackground(
                new Color(227, 236, 241));
    }

    public void setFormListener(RegisterFormListenerIF formListener) {
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

    private void setControls() {

        JPanel controlsPanel = new JPanel();
        JPanel buttonsPanel = new JPanel();

        controlsPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        buttonsPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        controlsPanel.setLayout(new GridBagLayout());
        buttonsPanel.setLayout(new GridBagLayout());
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
        add(ageList, gc);

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
    }
}
