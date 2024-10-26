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
public class SignUpFormPanel extends JPanel{
    private JLabel emailLabel;
    private JLabel passwordLabel;
    private JTextField emailField;
    private JTextField passwordField;
    private JButton loginBtn;
    private JButton signIn;
    private FormListenerIF formListener;
    
    public SignUpFormPanel(){
        emailLabel = new JLabel("Email");
        passwordLabel = new JLabel("Password");
        emailField = new JTextField(10);
        passwordField = new JTextField(10);
        
        loginBtn = new JButton("Sign In");
        
        Dimension dim = getPreferredSize();
        dim.width = 250;
        setPreferredSize(dim);
        
        Border innerBorder = BorderFactory.createTitledBorder("Log In");
        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
        
        setLayout(new GridBagLayout());
        
        GridBagConstraints gc = new GridBagConstraints();
        
        //first row ----------------------------------------------------
        gc.weightx = 1;
        gc.weighty = 0.1;
        
        gc.gridx = 0;
        gc.gridy = 0;
        gc.fill = GridBagConstraints.NONE;
        gc.insets = new Insets(0, 0, 0, 5);
        gc.anchor = GridBagConstraints.LINE_END;       
        add(emailLabel, gc); //add the email label
        
        gc.gridx = 1;
        gc.gridy = 0;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        add(emailField, gc); //add the email field 
        
        //second row -------------------------------------------------------
        gc.weightx = 1;
        gc.weighty = 0.1;
        
        gc.gridx = 0;
        gc.gridy = 1;
        gc.insets = new Insets(0, 0, 0, 5);
        gc.anchor = GridBagConstraints.LINE_END;
        add(passwordLabel, gc); //add the password label
        
        gc.gridx = 1;
        gc.gridy = 1;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        add(passwordField, gc); //add password field
        
        //third row ---------------------------------------------------
        gc.weightx = 1;
        gc.weighty = 2;
        
        gc.gridx = 1;
        gc.gridy = 2;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(loginBtn, gc);
        
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

