package view.loginsignup.signup;

import view.MainPageView;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import model.loginsignup.User;
import view.loginsignup.FormListenerIF;

/**
 *
 * @author Ana
 */
public class SignUpView extends JFrame {

    private SignUpFormPanel formPanel;

    public SignUpView() {
        super("WallyLand Park Application");
        
        setLayout(new BorderLayout());

        formPanel = new SignUpFormPanel();
        
        formPanel.setFormListener(new FormListenerIF(){
            public void formEventOccured(User e){
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
               
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    private boolean validateLogin(String email, String password){
        //To Do
        return "user@example.com".equals(email) && "password".equals(password);
    }

}
