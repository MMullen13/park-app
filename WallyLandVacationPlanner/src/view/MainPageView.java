package view;

import controller.foodordering.FoodController;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import view.foodordering.OrderConfirmationViewForm;
import view.foodordering.OrderHistoryView;
import view.foodordering.OrderViewForm;

/**
 * 
 * @author Ana
 */
public class MainPageView extends JFrame {

    private ImageIcon wallylandIcon;
    private FoodController cntl;
    private OrderViewForm orderView;
    private OrderConfirmationViewForm confirmationView;
    
    
    /**
     * Constructor
     */
    public MainPageView() {
        super("WallyLand");
        
        wallylandIcon = createIcon("/images/theme-park.png", 200, 200);
        if (wallylandIcon != null) {
            setIconImage(wallylandIcon.getImage()); // This should work if class extends JFrame
        }
        setLayout(new BorderLayout());
       
        setJMenuBar(createMenuBar());
        
        addWindowListener(new WindowAdapter(){
           @Override
           public void windowClosing(WindowEvent e){
               System.out.println("Main Page closing");
               dispose(); //dispose this window
               System.gc(); //run the garbage collector
           } 
        });
        
        setSize(700, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setVisible(true);
        
        cntl = new FoodController();
        orderView = new OrderViewForm(cntl);
        confirmationView = new OrderConfirmationViewForm(cntl);
    }
    
    private JMenuBar createMenuBar(){
        JMenuBar menuBar = new JMenuBar();
      
        JMenu orderFood = new JMenu("Dining");
        JMenu viewMap = new JMenu("View Park Map");
        JMenu purchaseTickets = new JMenu("Purchase Passes");
        JMenu bday = new JMenu("Birthday Packages");      
        JMenu info = new JMenu("Information");
        JMenu exit = new JMenu("Exit Application");
        
        JMenuItem newOrder = new JMenuItem("New Order");
        JMenuItem viewConfirmation = new JMenuItem("View Order Confirmation");
        JMenuItem orderHistory = new JMenuItem("Order History");
        
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
        
        orderFood.add(newOrder);
        orderFood.add(viewConfirmation);
        orderFood.add(orderHistory);
        
        purchaseTickets.add(tickets);
        purchaseTickets.add(passes);
        purchaseTickets.add(groupTickets);
        purchaseTickets.add(promotions);
        
        
        
        
        newOrder.addActionListener(e -> {
            if (cntl.getOrderView() == null) {
                cntl.setOrderView(new OrderViewForm(cntl));
                cntl.setOrderView(orderView); // Set the order view in the controller
            }
            cntl.getOrderView().setVisible(true); // Show the order view
        });

        viewConfirmation.addActionListener(e -> {
            if (cntl.getConfirmationView() != null) {
                cntl.getConfirmationView().setVisible(true); // Show the confirmation view
            } else {
                System.out.println("Confirmation view is not initialized.");
             }
        });

        orderHistory.addActionListener(e -> {
            OrderHistoryView history = new OrderHistoryView();
            history.setVisible(true);
        });
        
        signOut.addActionListener((ActionEvent e) -> {
            int action = JOptionPane.showConfirmDialog(MainPageView.this, "Do you really want to Sign Out?", "Confirm Sign Out", JOptionPane.OK_CANCEL_OPTION);
            if(action == JOptionPane.OK_OPTION){
                WindowListener[] listeners = getWindowListeners();
                for(WindowListener listener: listeners){
                    listener.windowClosing(new WindowEvent(MainPageView.this, 0));
                }
            }
        });
        
        return menuBar;
    }

        /**
     * Creates an ImageIcon from the specified file path, resizes it to the
     * given width and height, and returns the resized icon.
     *
     * @param path The path to the image file.
     * @param w The desired width of the icon.
     * @param l The desired length (height) of the icon.
     * @return A resized ImageIcon.
     */
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
}
