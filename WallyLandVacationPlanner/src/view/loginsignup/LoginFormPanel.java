package view.loginsignup;

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

/**
 *
 * @author Ana
 */
public class LoginFormPanel extends JPanel{
    private JLabel emailLabel;
    private JLabel passwordLabel;
    private JTextField emailField;
    private JTextField passwordField;
    private JButton loginBtn;
    private JButton signUpBtn;
    private FormListenerIF formListener;
    private JLabel signUpExplanationLabel;
    
    public LoginFormPanel(){
        emailLabel = new JLabel("Email");
        passwordLabel = new JLabel("Password");
        emailField = new JTextField(10);
        passwordField = new JTextField(10);
        
        loginBtn = new JButton("Sign In");
        signUpBtn = new JButton("Sign Up");
        
        signUpExplanationLabel = new JLabel("Make an account!");
        
        Dimension dim = getPreferredSize();
        dim.width = 250;
        setPreferredSize(dim);
        
        Border innerBorder = BorderFactory.createTitledBorder("Log In");
        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
        
        setLayout(new GridBagLayout());
        
        GridBagConstraints gc = new GridBagConstraints();
        
        // First row: Email label and field
        gc.gridy = 0;
        gc.weightx = 1;
        gc.weighty = 0.1;

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
        gc.weighty = 0.1;

        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0, 0, 5, 5);
        add(passwordLabel, gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, 0, 5, 0);
        add(passwordField, gc);

        // Third row: Login button
        gc.gridy++;
        gc.weightx = 1;
        gc.weighty = 0.2;

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(10, 0, 5, 0); // Adding some top padding for spacing
        add(loginBtn, gc);

        // Fourth row: Sign up explanation label
        gc.gridy++;
        gc.weightx = 1;
        gc.weighty = 0.2;

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(15, 0, 5, 0); // Adding some top padding for spacing
        add(signUpExplanationLabel, gc);

        // Fifth row: Sign up button
        gc.gridy++;
        gc.weightx = 1;
        gc.weighty = 2.0;

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(5, 0, 0, 0);
        add(signUpBtn, gc);

        
        loginBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String password = passwordField.getText();
                
                ProfileUser credentials = new ProfileUser(this, email, password);
                
                if(formListener != null){
                    formListener.formEventOccured(credentials);
                }
            }           
        });
        
    }

    public void setFormListener(FormListenerIF formListener) {
        this.formListener = formListener;
    }
}
