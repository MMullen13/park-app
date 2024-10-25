package controller;

import model.loginsignup.ProfileUser;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import view.loginsignup.LoginFormPanel;
import view.loginsignup.Toolbar;
import view.loginsignup.TextPanel;
import view.loginsignup.StringListenerIF;
import view.loginsignup.FormListenerIF;

/**
 * 
 * @author Ana
 */
public class MainPage extends JFrame {
    
//    private TextPanel textPanel;
//    private Toolbar toolbar; //top position toolbar
//    private LoginFormPanel formPanel;

    public MainPage() {
        super("WallyLand Park Application");
        
        setLayout(new BorderLayout());
//        toolbar = new Toolbar();
//        textPanel = new TextPanel();
//        formPanel = new LoginFormPanel();
        
        setJMenuBar(createMenuBar());
        
//        toolbar.setStringListener(new StringListenerIF(){
//            @Override
//            public void textEmmited(String text) {
//               textPanel.appendText(text);
//            }
//            
//        });
//        
//        formPanel.setFormListener(new FormListenerIF(){
//            public void formEventOccured(ProfileUser e){
//                String email = e.getEmail();
//                String password = e.getPassword();
//                
//                textPanel.appendText(email + "\n" + password + "\n");
//            }
//        });
//        
//        add(toolbar, BorderLayout.NORTH);
//        add(textPanel, BorderLayout.CENTER);
//        add(formPanel, BorderLayout.WEST);
        
        
        setSize(700, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    private JMenuBar createMenuBar(){
        JMenuBar menuBar = new JMenuBar();
      
        JMenu orderFood = new JMenu("Dining");
        JMenu viewMap = new JMenu("View Park Map");
        JMenu purchaseTickets = new JMenu("Purchase Passes");
        JMenu bday = new JMenu("Birthday Packages");      
        JMenu info = new JMenu("Information");
        JMenu signOut = new JMenu("Sign Out");
        
        JMenuItem dessertMenu = new JMenuItem("Desserts");
        JMenuItem lunchMenu = new JMenuItem("Lunch");
        JMenuItem dinnerMenu = new JMenuItem("Dinner");
        
        JMenuItem tickets = new JMenuItem("Day Pass");
        JMenuItem passes = new JMenuItem("Seasson Passes");
        JMenuItem groupTickets = new JMenuItem("Group Passes");
        JMenuItem promotions = new JMenuItem("Deals");
        
        orderFood.add(dessertMenu);
        orderFood.add(lunchMenu);
        orderFood.add(dinnerMenu);
        
        purchaseTickets.add(tickets);
        purchaseTickets.add(passes);
        purchaseTickets.add(groupTickets);
        purchaseTickets.add(promotions);
        
        menuBar.add(purchaseTickets);
        menuBar.add(viewMap);
        menuBar.add(orderFood);
        menuBar.add(bday);
        menuBar.add(info);
        menuBar.add(signOut);
        
        
        return menuBar;
    }

}
