package view.loginsignup.register;

import view.loginsignup.AgeCategoryUtility;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import model.loginsignup.NewUser;
import model.loginsignup.User;
import model.loginsignup.UserFactory;
import model.loginsignup.RegisterFormEvent;
import view.loginsignup.RegisterFormListenerIF;
import view.loginsignup.RoundedBorder;
import view.loginsignup.RoundedTextField;
import view.loginsignup.UpdateSignupListener;

/**
 *
 * @author Ana
 */
public class RegisterFormPanel extends JPanel {

    protected JLabel emailLabel;
    protected JLabel passwordLabel;
    protected JLabel ageLabel;
    protected JLabel phoneLabel;
    protected JLabel firstNameLabel;
    protected JLabel lastNameLabel;
    protected JLabel successLabel;
    protected JLabel emailErrorLabel;
    protected JLabel passwordErrorLabel;
    protected JLabel passwordExplanationLabel;
    protected JLabel firstNameErrorLabel;
    protected JLabel lastNameErrorLabel;
    protected JLabel phoneErrorLabel;
    protected RoundedTextField emailField;
    protected RoundedTextField passwordField;
    protected RoundedTextField firstNameField;
    protected RoundedTextField lastNameField;
    protected RoundedTextField phoneField;
    protected JButton registerBtn;
    private JList ageList;
    private Icon emailIcon;
    private Icon passwordIcon;
    private Icon firstNameIcon;
    private Icon ageIcon;
    private Icon phoneIcon;
    private Icon successIcon;

    protected RegisterFormListenerIF formListener;
    private UpdateSignupListener listener;

    /**
     * Constructor
     */
    public RegisterFormPanel() {

        Dimension dim = getPreferredSize();
        dim.width = 250;
        setPreferredSize(dim);

        emailField = new RoundedTextField("Email", 14);
        passwordField = new RoundedTextField("Password", 14);
        firstNameField = new RoundedTextField("First Name", 14);
        lastNameField = new RoundedTextField("Last Name", 14);
        phoneField = new RoundedTextField("(215)123-4567", 14);
        ageList = new JList();
        emailIcon = createIcon("/images/icons8-email.png", 40, 40);
        passwordIcon = createIcon("/images/icons8-lock.png", 40, 40);
        firstNameIcon = createIcon("/images/icons8-name-tag.png", 50, 50);
        ageIcon = createIcon("/images/icons8-calendar.png", 60, 60);
        phoneIcon = createIcon("/images/icons8-phone.png", 40, 40);
        successIcon = createIcon("/images/icons8-success.png", 40, 40);

        emailField.setForeground(Color.LIGHT_GRAY);
        passwordField.setForeground(Color.LIGHT_GRAY);
        firstNameField.setForeground(Color.LIGHT_GRAY);
        lastNameField.setForeground(Color.LIGHT_GRAY);
        phoneField.setForeground(Color.LIGHT_GRAY);

        emailField.setBorder(BorderFactory.createCompoundBorder(new RoundedBorder(15), BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        passwordField.setBorder(BorderFactory.createCompoundBorder(new RoundedBorder(15), BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        firstNameField.setBorder(BorderFactory.createCompoundBorder(new RoundedBorder(15), BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        lastNameField.setBorder(BorderFactory.createCompoundBorder(new RoundedBorder(15), BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        phoneField.setBorder(BorderFactory.createCompoundBorder(new RoundedBorder(15), BorderFactory.createEmptyBorder(5, 10, 5, 10)));

        setTextField(emailField, "Email");
        setTextField(passwordField, "Password");
        setTextField(firstNameField, "First Name");
        setTextField(lastNameField, "Last Name");
        setTextField(phoneField, "(215)123-4567");

        emailLabel = new JLabel("Email", emailIcon, SwingConstants.RIGHT);
        passwordLabel = new JLabel("Password", passwordIcon, SwingConstants.LEFT);
        firstNameLabel = new JLabel("First Name", firstNameIcon, SwingConstants.RIGHT);
        lastNameLabel = new JLabel("Last Name");
        ageLabel = new JLabel("Age", ageIcon, SwingConstants.RIGHT);
        phoneLabel = new JLabel("Phone Number", phoneIcon, SwingConstants.LEFT);
        successLabel = new JLabel("Success", successIcon, SwingConstants.LEFT);
        phoneErrorLabel = new JLabel("");
        emailErrorLabel = new JLabel("");
        passwordErrorLabel = new JLabel("");
        passwordExplanationLabel = new JLabel("Password must be at least 8 haracters long, one capital letter and one digit");
        firstNameErrorLabel = new JLabel("");
        lastNameErrorLabel = new JLabel("");

        emailLabel.setForeground(Color.GRAY);
        passwordLabel.setForeground(Color.GRAY);
        firstNameLabel.setForeground(Color.GRAY);
        lastNameLabel.setForeground(Color.GRAY);
        ageLabel.setForeground(Color.GRAY);
        phoneLabel.setForeground(Color.GRAY);
        successLabel.setForeground(Color.GRAY);

        successLabel.setVisible(false);

        passwordExplanationLabel.setForeground(Color.GRAY);

        DefaultListModel ageModel = new DefaultListModel();

        ageModel.addElement(new AgeCategoryUtility(0, "Under 3"));
        ageModel.addElement(new AgeCategoryUtility(1, "3 to 18"));
        ageModel.addElement(new AgeCategoryUtility(2, "18 to 65"));
        ageModel.addElement(new AgeCategoryUtility(3, "65 or over"));
        ageList.setModel(ageModel);

        ageList.setPreferredSize(new Dimension(175, 85));
        ageList.setSelectedIndex(0);

        // Customize ageList appearance
        ageList.setFont(new Font("Arial", Font.PLAIN, 14));
        ageList.setForeground(Color.DARK_GRAY);
        ageList.setSelectionBackground(new Color(58, 115, 169)); // Same navy blue as register button
        ageList.setSelectionForeground(Color.WHITE);

        ageList.setBorder(BorderFactory.createCompoundBorder(
                new RoundedBorder(10),
                BorderFactory.createEmptyBorder(5, 5, 5, 5))
        );

// Adding ageList to a scroll pane
        JScrollPane ageListScrollPane = new JScrollPane(ageList);
        ageListScrollPane.setPreferredSize(new Dimension(165, 75));
        ageListScrollPane.setBorder(BorderFactory.createEmptyBorder()); // Remove default scroll pane border

        registerBtn = new JButton("Register");
        registerBtn.setForeground(Color.DARK_GRAY);
        registerBtn.setPreferredSize(new Dimension(140, 40)); // Width: 130, Height: 30

        Border innerBorder = BorderFactory.createTitledBorder("New User Sign Up");
        Border outerBorder = BorderFactory.createEmptyBorder(15, 15, 15, 15);

        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

        setControls();

        registerBtn.setBackground(new Color(58, 115, 169));  // Navy blue
        registerBtn.setForeground(Color.WHITE);
        registerBtn.setFocusPainted(false);  // Removes focus border on click
        registerBtn.setFont(new Font("Arial", Font.BOLD, 14));

        registerBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                registerBtn.setBackground(new Color(40, 95, 150));  // Darker blue on hover
            }

            @Override
            public void mouseExited(MouseEvent e) {
                registerBtn.setBackground(new Color(58, 115, 169)); // Original blue
            }

            @Override
            public void mousePressed(MouseEvent e) {
                registerBtn.setForeground(new Color(40, 95, 150));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                registerBtn.setForeground(Color.WHITE);
            }
        });

        registerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                String email = emailField.getText();
                String password = passwordField.getText();
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                String phoneNum = phoneField.getText();
                AgeCategoryUtility ageCategory = (AgeCategoryUtility) ageList.getSelectedValue();

                User user = (User) UserFactory.createUser(email, password, true);

                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setAgeID(ageCategory.getID());
                user.setPhoneNum(phoneNum);

                RegisterFormEvent userEvent = new RegisterFormEvent(this, (NewUser) user);
                if (formListener != null) {
                    formListener.formEventOccured(userEvent);
                }

                if (listener != null) {
                    listener.updateSuccessState();
                }
            }
        });

        registerBtn.setIcon(createIcon("/images/icons8-add-user.png", 20, 20));

        Font fieldFont = new Font("Arial", Font.PLAIN, 14);
        emailField.setFont(fieldFont);
        passwordField.setFont(fieldFont);
        firstNameField.setFont(fieldFont);
        lastNameField.setFont(fieldFont);
        phoneField.setFont(fieldFont);

        passwordExplanationLabel.setFont(new Font("Arial", Font.PLAIN, 12));

        setBackground(new Color(227, 236, 241));
    }

    public void setFormListener(RegisterFormListenerIF formListener) {
        this.formListener = formListener;
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

    public void setUpdateStateListener(UpdateSignupListener listener) {
        this.listener = listener;
    }

    private void setControls() {
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

        gc.gridx = 2;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, -130, 5, 0);
        add(emailErrorLabel, gc);

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

        gc.gridx = 2;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, -130, 5, 0);
        add(passwordErrorLabel, gc);

        // password instruction row
        gc.gridy++;
        gc.gridx = 1;
        gc.weighty = 0.05;
        gc.anchor = GridBagConstraints.CENTER;
        gc.insets = new Insets(0, -130, 20, 0);
        add(passwordExplanationLabel, gc);

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

        gc.gridx = 2;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, -130, 5, 0);
        add(firstNameErrorLabel, gc);

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

        gc.gridx = 2;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, -130, 5, 0);
        add(lastNameErrorLabel, gc);

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

        gc.gridx = 2;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, -130, 5, 0);
        add(phoneErrorLabel, gc);

        // Seventh row: Register button
        gc.gridy++;
        gc.weightx = 1;
        gc.weighty = 1;

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(10, 0, 5, 0);
        add(registerBtn, gc);

        gc.gridx = 2;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(10, -140, 5, 0);
        add(successLabel, gc);
    }
}
