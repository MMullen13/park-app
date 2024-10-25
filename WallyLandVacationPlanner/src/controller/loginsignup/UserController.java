package controller.loginsignup;

import model.loginsignup.ProfileUser;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import view.loginsignup.LoginFormPanel;
import view.loginsignup.TextPanel;
import view.loginsignup.FormListenerIF;

/**
 *
 * @author Ana
 */
public class UserController extends JFrame {
    
    private TextPanel textPanel;
    private LoginFormPanel formPanel;

    public UserController() {
        super("WallyLand Park Application");
        
        setLayout(new BorderLayout());
        textPanel = new TextPanel();
        formPanel = new LoginFormPanel();
        
        formPanel.setFormListener(new FormListenerIF(){
            public void formEventOccured(ProfileUser e){
                String email = e.getEmail();
                String password = e.getPassword();
                
                textPanel.appendText(email + "\n" + password + "\n");
            }
        });

        add(textPanel, BorderLayout.CENTER);
        add(formPanel, BorderLayout.WEST);
               
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

}
