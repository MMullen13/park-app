package view.loginsignup.login;

import controller.loginsignup.LoginController;
import view.MainPageView;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import model.loginsignup.LoginFormEvent;
import view.loginsignup.LoginFormListenerIF;

/**
 *
 * @author Ana
 */
public class LoginView extends JFrame {

    private LoginFormPanel formPanel;
    private MainPageView mainPage;
    private LoginController controller;

    public LoginView() {
        super("WallyLand Park Application");
        
        setLayout(new BorderLayout());

        formPanel = new LoginFormPanel();
        controller = new LoginController();
        controller.setLoginView(this);
        
        formPanel.setFormListener(new LoginFormListenerIF(){
            public void formEventOccured(LoginFormEvent e){
//                String email = e.getEmail();
//                String password = e.getPassword();
//                
//                System.out.println(email + "\n" + password + "\n");
//                
//                if(validateLogin(email, password)){
//                    dispose();
//                    mainPage = new MainPageView();
//                    mainPage.setVisible(true);
//                } else{
//                    System.out.println("Invalid Login Credentials\n");
//                }
                controller.handleProfileUser(e);
            }
        });

        //for testing purposses
        add(formPanel, BorderLayout.CENTER);
               
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
//    private boolean validateLogin(String email, String password){
//        //To Do
//        return "user@example.com".equals(email) && "password".equals(password);
//    }

}
