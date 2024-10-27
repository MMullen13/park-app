package view.loginsignup;

import view.MainPageView;
import model.loginsignup.ProfileUser;
import java.awt.BorderLayout;
import javax.swing.JFrame;
//import view.loginsignup.TextPanel;

/**
 *
 * @author Ana
 */
public class UserView extends JFrame {
    
//    private TextPanel textPanel;
    private LoginFormPanel formPanel;

    public UserView() {
        super("WallyLand Park Application");
        
        setLayout(new BorderLayout());
//        textPanel = new TextPanel();
        formPanel = new LoginFormPanel();
        
        formPanel.setFormListener(new FormListenerIF(){
            public void formEventOccured(ProfileUser e){
                String email = e.getEmail();
                String password = e.getPassword();
                
//                textPanel.appendText(email + "\n" + password + "\n");
                System.out.println(email + "\n" + password + "\n");
                
                if(validateLogin(email, password)){
                    dispose();
                    MainPageView mainPage = new MainPageView();
                    mainPage.setVisible(true);
                } else{
//                    textPanel.appendText("Invalid Login Credentials\n");
                    System.out.println("Invalid Login Credentials\n");
                }
            }
        });

//        add(textPanel, BorderLayout.EAST);
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
