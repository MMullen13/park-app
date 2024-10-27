package view.loginsignup.login;

import view.loginsignup.login.LoginFormPanel;
import view.MainPageView;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import model.loginsignup.UserIF;
import view.loginsignup.FormListenerIF;
//import view.loginsignup.TextPanel;

/**
 *
 * @author Ana
 */
public class LoginView extends JFrame {

    private LoginFormPanel formPanel;

    public LoginView() {
        super("WallyLand Park Application");
        
        setLayout(new BorderLayout());

        formPanel = new LoginFormPanel();
        
        formPanel.setFormListener(new FormListenerIF(){
            public void formEventOccured(UserIF e){
                String email = e.getEmail();
                String password = e.getPassword();
                
                System.out.println(email + "\n" + password + "\n");
                
                if(validateLogin(email, password)){
                    dispose();
                    MainPageView mainPage = new MainPageView();
                    mainPage.setVisible(true);
                } else{
                    System.out.println("Invalid Login Credentials\n");
                }
            }
        });

        //for testing purposses
        add(formPanel, BorderLayout.CENTER);
               
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    private boolean validateLogin(String email, String password){
        //To Do
        return "user@example.com".equals(email) && "password".equals(password);
    }

}
