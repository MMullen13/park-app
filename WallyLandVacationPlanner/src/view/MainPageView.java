package view;

import controller.Controller;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 * 
 * @author Ana
 */
public class MainPageView extends JFrame {
    Controller controller;
    
    /**
     * Constructor
     */
    public MainPageView() {
        super("WallyLand Park Application");
        
        controller = new Controller();
        setLayout(new BorderLayout());
       
        setJMenuBar(createMenuBar());
        
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
        JMenu exit = new JMenu("Exit Application");
        
        JMenuItem dessertMenu = new JMenuItem("Desserts");
        JMenuItem lunchMenu = new JMenuItem("Lunch");
        JMenuItem dinnerMenu = new JMenuItem("Dinner");
        
        JMenuItem tickets = new JMenuItem("Day Pass");
        JMenuItem passes = new JMenuItem("Seasson Passes");
        JMenuItem groupTickets = new JMenuItem("Group Passes");
        JMenuItem promotions = new JMenuItem("Deals");
        
        JMenuItem events = new JMenuItem("Events");
        JMenuItem attractions = new JMenuItem("Attractions");
        
        JMenuItem signOut = new JMenuItem("Sign Out");
        
        menuBar.add(purchaseTickets);
        menuBar.add(viewMap);
        menuBar.add(orderFood);
        menuBar.add(bday);
        menuBar.add(info);
        menuBar.add(exit);
        
        exit.add(signOut);
        
        info.add(events);
        info.add(attractions);
        
        orderFood.add(dessertMenu);
        orderFood.add(lunchMenu);
        orderFood.add(dinnerMenu);
        
        purchaseTickets.add(tickets);
        purchaseTickets.add(passes);
        purchaseTickets.add(groupTickets);
        purchaseTickets.add(promotions);
        
        signOut.addActionListener((ActionEvent e) -> {
            int action = JOptionPane.showConfirmDialog(MainPageView.this, "Do you really want to Sign Out?", "Confirm Sign Out", JOptionPane.OK_CANCEL_OPTION);
            if(action == JOptionPane.OK_OPTION){
                System.exit(0);
            }
        });
        
        return menuBar;
    }

}
